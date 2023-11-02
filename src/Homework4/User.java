package Homework4;

import java.io.*;
import java.util.*;

public interface User{
    void query();
    void charge();
    void exit();
}
class RealUser implements User{
    private int account_number;
    private int password;
    private int ID_num;
    private int phone_number;
    private double money;
    private int query_cnt;

    public RealUser(int account_number, int password, int ID_num, int phone_number, double money, int query_cnt) {
        this.account_number = account_number;
        this.password = password;
        this.ID_num = ID_num;
        this.phone_number = phone_number;
        this.money = money;
        this.query_cnt = query_cnt;
    }

    public int getAccount_number() {
        return account_number;
    }

    public void setAccount_number(int account_number) {
        this.account_number = account_number;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public int getID_num() {
        return ID_num;
    }

    public void setID_num(int ID_num) {
        this.ID_num = ID_num;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getQuery_cnt() {
        return query_cnt;
    }

    public void setQuery_cnt(int query_cnt) {
        this.query_cnt = query_cnt;
    }

    @Override
    public void query() {
        System.out.println("商务信息查询成功。");
    }

    @Override
    public void charge() {
        System.out.println("充值成功。");
    }

    @Override
    public void exit() {
        System.out.println("系统结束。");
    }
}

class ProxyUser implements User{
    private RealUser realUser;
    private List<RealUser> user_list;
    private Scanner sc;
    public ProxyUser(){
        this.user_list = new ArrayList<>();
        this.sc = new Scanner(System.in);
        try{
            File file = new File("./src/Homework4/data.txt");
            if(file.isFile() && file.exists()){
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String input;
                while((input = bufferedReader.readLine()) != null){
                    String[] data = input.split(" ");
                    RealUser temp_user = new RealUser(Integer.parseInt(data[0]),Integer.parseInt(data[1]),
                            Integer.parseInt(data[2]), Integer.parseInt(data[3]), Double.parseDouble(data[4]),
                            Integer.parseInt(data[5]));
                    user_list.add(temp_user);
                }
                bufferedReader.close();
                inputStreamReader.close();
                fileInputStream.close();
            }
        }
        catch(IOException exception){
            System.out.println("输入过程发生错误。");
        }

        this.realUser = identityAuthentication();
    }

    private RealUser identityAuthentication(){
        int account_number;
        int password;
        int ID_num;
        int phone_number;
        RealUser user = null;
        System.out.println("请选择：登录、注册");
        String input = sc.next();
        while(!input.equals("登录") && !input.equals("注册")){
            System.out.println("输入错误。");
            System.out.println("请选择：登录或注册");
            input = sc.next();
        }
        System.out.println("输入账号：");
        account_number = sc.nextInt();
        System.out.println("输入密码：");
        password = sc.nextInt();
        System.out.println("输入身份证号：");
        ID_num = sc.nextInt();
        System.out.println("输入手机号码：");
        phone_number = sc.nextInt();
        if(input.equals("登录")){
            boolean find_user = false;
            for(RealUser cur_user: user_list){
                if(cur_user.getAccount_number() == account_number && cur_user.getPassword() == password &&
                        cur_user.getID_num() == ID_num && cur_user.getPhone_number() == phone_number){
                    find_user = true;
                    user = cur_user;
                }
            }
            if(find_user){
                System.out.printf("登录成功。账号为%d。\n", account_number);
            }
            else{
                System.out.println("没有该用户，自动给您创建了新用户。");
                user = new RealUser(account_number,password,ID_num,phone_number,0,0);
                user_list.add(user);
            }
        }
        else {
            user = new RealUser(account_number,password,ID_num,phone_number,0,0);
            user_list.add(user);
        }
        return user;
    }

    @Override
    public void query() {
        double price;
        if(realUser.getQuery_cnt() <= 200){
            price = 0.1d;
        }
        else if(realUser.getQuery_cnt() <= 500){
            price = Math.ceil(realUser.getQuery_cnt() / 100.0f) * 0.1;
        }
        else{
            price = 0.5d;
        }
        System.out.printf("此次查询花费%.2f元。\n", price);
        if(realUser.getMoney() < price){
            System.out.println("金额不够请充值。");
        }
        else{
            realUser.setMoney(realUser.getMoney() - price);
            realUser.setQuery_cnt(realUser.getQuery_cnt() + 1);
            this.realUser.query();
        }
    }

    @Override
    public void charge() {
        double money = 0;
        System.out.println("请输入充值金额：");
        money = sc.nextDouble();
        realUser.setMoney((realUser.getMoney()) + money);
        realUser.charge();
    }

    public void exit(){
        File file = new File("./src/Homework4/data.txt");
        try{
            if(!file.exists() && file.createNewFile()){
                System.out.println("文件创建成功");
            }
        }
        catch (IOException exception){
            System.out.println("创建文件过程中出现错误。");
        }
        try(FileOutputStream fileOutputStream = new FileOutputStream(file)){
            for(RealUser user : this.user_list){
                fileOutputStream.write((user.getAccount_number() + " " + user.getPassword() +
                        " " + user.getID_num() + " " + user.getPhone_number() + " " +
                        user.getMoney() + " " + user.getQuery_cnt() + "\n").getBytes());
            }
            fileOutputStream.flush();
        }
        catch(IOException exception){
            System.out.println("输出过程发生错误。");
        }
        this.realUser.exit();
        sc.close();
    }
}


