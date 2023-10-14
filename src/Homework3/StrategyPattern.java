package Homework3;
import java.io.*;
import java.util.*;

public class StrategyPattern {
    public static void main(String[] args){
        int totalPrice = 0;
        try{
            Context context = new Context(new SelectMilkTeaSize());
            totalPrice += context.executeStrategy();
            context = new Context(new SelectMilkTeaBase());
            totalPrice += context.executeStrategy();
            context = new Context(new SelectMilkTeaCharge());
            totalPrice += context.executeStrategy();
            System.out.println("总价为：" + totalPrice);
        }
        catch(IllegalArgumentException | IOException e){
            System.out.println(e.getMessage());
        }
    }
}

interface Strategy{
    int totalPrice() throws IOException;
}

class GetThePrice{
    public static int getThePrice(int price, Map<String, Integer> toPrice) throws IOException {
        for (Map.Entry<String, Integer> entry : toPrice.entrySet()){
            System.out.print(entry.getKey() + "：" + entry.getValue() + "```");
        }
        System.out.println();
        String input = (new BufferedReader(new InputStreamReader(System.in))).readLine();
        if(toPrice.containsKey(input)){
            price += toPrice.get(input);
        }
        else{
            throw new IllegalArgumentException("错误: 请输入正确的名称。");
        }
        return price;
    }
}

class SelectMilkTeaSize implements Strategy{

    private final Map<String, Integer> sizeToPrice;

    public SelectMilkTeaSize(){
        sizeToPrice = new HashMap<>();
        sizeToPrice.put("Medium", 6);
        sizeToPrice.put("Large", 8);
        sizeToPrice.put("Super", 10);
    }
    @Override
    public int totalPrice() throws IOException {
        int price = 0;
        System.out.print("请选择奶茶的大小：");
        price = GetThePrice.getThePrice(price, sizeToPrice);
        return price;
    }
}

class SelectMilkTeaBase implements Strategy{
    private final Map<String, Integer> baseToPrice;

    public SelectMilkTeaBase(){
        baseToPrice = new HashMap<>();
        baseToPrice.put("Oolong tea", 2);
        baseToPrice.put("Green tea", 3);
        baseToPrice.put("Black tea", 4);
    }
    @Override
    public int totalPrice() throws IOException {
        int price = 0;
        System.out.print("请选择奶茶的茶底：");
        price = GetThePrice.getThePrice(price, baseToPrice);
        return price;
    }
}

class SelectMilkTeaCharge implements Strategy{
    private final Map<String, Integer> chargeToPrice;

    public SelectMilkTeaCharge(){
        chargeToPrice = new HashMap<>();
        chargeToPrice.put("Not add", 0);
        chargeToPrice.put("Pearl", 1);
        chargeToPrice.put("Coconut", 2);
        chargeToPrice.put("Milk cover", 3);
    }
    @Override
    public int totalPrice() throws IOException {
        int price;
        int t_price = 0;
        do{
            price = t_price;
            System.out.print("请选择奶茶的加料：");
            t_price = GetThePrice.getThePrice(price, chargeToPrice);
        }while(t_price != price);

        return price;
    }
}

class Context{
    private final Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }
    public int executeStrategy() throws IOException{
        return strategy.totalPrice();
    }
}
