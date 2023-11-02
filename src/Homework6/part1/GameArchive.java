package Homework6.part1;

import java.util.*;
import java.io.*;

public class GameArchive {
    public static void main(String[] args){
        Hero hero = new Hero();
        CareTaker careTaker = new CareTaker();
        careTaker.load();
        if(careTaker.getMementoListNum() != 0){
            hero.restore(careTaker.get(careTaker.getMementoListNum() - 1));
        }
        hero.setLocation(new int[]{16, 110, 113});
        careTaker.add(hero.save());
        hero.setExperiment(999);
        careTaker.add(hero.save());
        hero.setLife(99.9);
        careTaker.add(hero.save());
        hero.addItem(789);
        hero.addItem(123);
        hero.addItem(234);
        hero.deleteItem(1);
        hero.deleteItem(1);
        hero.deleteItem(1);
        careTaker.add(hero.save());
        careTaker.save();
    }
}

class Hero{
    private int[] location;
    private int experiment;
    private double life;
    private List<Integer> bag;

    public Hero() {
        this.location = new int[]{0, 0, 0};
        this.experiment = 0;
        this.life = 0.0;
        this.bag = new ArrayList<>();
    }

    public void setLocation(int[] location) {
        this.location = location;
    }

    public void setExperiment(int experiment) {
        this.experiment = experiment;
    }

    public void setLife(double life) {
        this.life = life;
    }

    public void deleteItem(int index){
        this.bag.remove(index);
    }

    public void addItem(int item){
        this.bag.add(item);
    }
    // 存储状态
    public HeroMemento save(){
        return new HeroMemento(this.location, this.experiment, this.life, new ArrayList<>(this.bag));
    }
    // 恢复状态
    public void restore(HeroMemento memento){
        this.location = memento.getLocation();
        this.experiment = memento.getExperiment();
        this.life = memento.getLife();
        this.bag = new ArrayList<>(memento.getBag());
    }
    // 打印状态
    public String toString(){
        return ("Location : x " + this.location[0] + " y " + this.location[1] + " z " + this.location[2] + "\n" +
                "Experiment : " + this.experiment + "\n" +
                "Life : " + this.life + "\n" +
                "Bag : " + this.bag.toString());
    }
}

class HeroMemento{
    private final int[] location;
    private final int experiment;
    private final double life;
    private final List<Integer> bag;

    public HeroMemento(int[] location, int experiment, double life, List<Integer> bag) {
        this.location = location;
        this.experiment = experiment;
        this.life = life;
        this.bag = bag;
    }

    public int[] getLocation() {
        return location;
    }

    public int getExperiment() {
        return experiment;
    }

    public double getLife() {
        return life;
    }

    public List<Integer> getBag() {
        return bag;
    }
}

class CareTaker{
    private final List<HeroMemento> mementoList;
    private int mementoListNum;

    public CareTaker(){
        mementoList =  new ArrayList<>();
        mementoListNum = 0;
    }

    public int getMementoListNum() {
        return mementoListNum;
    }

    public void add(HeroMemento state){
        mementoList.add(state);
        mementoListNum++;
    }

    public HeroMemento get(int index){
        return mementoList.get(index);
    }

    public void save(){
        File file = new File("./src/Homework6/part1/data.txt");
        try{
            if(!file.exists() && file.createNewFile()){
                System.out.println("文件创建成功");
            }
        }
        catch (IOException exception){
            System.out.println("创建文件过程中出现错误。");
        }
        try(FileOutputStream fileOutputStream = new FileOutputStream(file)){
            for(HeroMemento memento : this.mementoList){
                fileOutputStream.write((memento.getLocation()[0] + " " + memento.getLocation()[1] +
                        " " + memento.getLocation()[2] + " " + memento.getExperiment() + " " +
                        memento.getLife() + " ").getBytes());
                StringBuilder items = new StringBuilder();
                for(int item : memento.getBag()){
                    items.append(item).append(",");
                }
                if(items.length() != 0){
                    items.deleteCharAt(items.length() - 1);
                }
                fileOutputStream.write((items + "\n").getBytes());
            }
            fileOutputStream.flush();
        }
        catch(IOException exception){
            System.out.println("输出过程发生错误。");
        }
    }

    public void load(){
        File file = new File("./src/Homework6/part1/data.txt");
        try{
            if(file.isFile() && file.exists()){
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String input;
                while((input = bufferedReader.readLine()) != null && !input.isEmpty()){
                    String[] data = input.split(" ");
                    int[] location = new int[]{Integer.parseInt(data[0]), Integer.parseInt(data[1]),
                            Integer.parseInt(data[2])};
                    int experiment = Integer.parseInt(data[3]);
                    double life = Double.parseDouble(data[4]);
                    List<Integer> bag = new ArrayList<>();
                    if(data.length == 6){
                        String all_item = data[5];
                        String[] items = all_item.split(",");
                        for (String item : items) {
                            bag.add(Integer.parseInt(item));
                        }
                    }

                    HeroMemento memento = new HeroMemento(location, experiment, life, bag);
                    mementoList.add(memento);
                }
                mementoListNum = mementoList.size();
                bufferedReader.close();
                inputStreamReader.close();
                fileInputStream.close();
            }
        }
        catch(IOException exception){
            System.out.println("输入过程发生错误。");
        }
    }
}