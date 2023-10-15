package Homework3;
import java.io.*;
import java.util.*;

public class StrategyPattern {
    public static void main(String[] args){
        try{
            MilkTea milkTea = new MilkTea();
            Context context = new Context(new SelectMilkTeaSize());
            context.executeStrategy(milkTea);
            context = new Context(new SelectMilkTeaBase());
            context.executeStrategy(milkTea);
            context = new Context(new SelectMilkTeaCharge());
            context.executeStrategy(milkTea);
            System.out.println("奶茶大小为：" + milkTea.getSize() + "，茶底为：" + milkTea.getBase() +
                    "，加料为：" + milkTea.getCharge());
            System.out.println("总价为：" +milkTea.getPrice());
        }
        catch(IllegalArgumentException | IOException e){
            System.out.println(e.getMessage());
        }
    }
}

interface Strategy{
    void totalPrice(MilkTea milkTea) throws IOException;
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
    public void totalPrice(MilkTea milkTea) throws IOException {
        System.out.print("请选择奶茶的大小：");
        GetThePrice.getThePrice(1, sizeToPrice, milkTea);
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
    public void totalPrice(MilkTea milkTea) throws IOException {
        System.out.print("请选择奶茶的茶底：");
        GetThePrice.getThePrice(2, baseToPrice, milkTea);
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
    public void totalPrice(MilkTea milkTea) throws IOException {
        String[] charge_input = new String[1];
        do{
            System.out.print("请选择奶茶的加料：");
            GetThePrice.getThePrice(chargeToPrice, milkTea, charge_input);
        }while(!charge_input[0].equals("Not add"));
    }
}

class Context{
    private final Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }
    public void executeStrategy(MilkTea milkTea) throws IOException{
        strategy.totalPrice(milkTea);
    }
}
