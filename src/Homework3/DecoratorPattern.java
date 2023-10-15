package Homework3;
import java.io.*;
import java.util.*;

public class DecoratorPattern {
    public static void main(String[] args){
        try{
            MilkTea milkTea = new MilkTea();
            MilkTeaDecorator milkTeaDecorator = new SizeDecorator(milkTea);
            milkTeaDecorator.decorator();
            milkTeaDecorator = new BaseDecorator(milkTea);
            milkTeaDecorator.decorator();
            milkTeaDecorator = new ChargeDecorator(milkTea);
            milkTeaDecorator.decorator();
            System.out.println("奶茶大小为：" + milkTea.getSize() + "，茶底为：" + milkTea.getBase() +
                    "，加料为：" + milkTea.getCharge());
            System.out.println("总价为：" +milkTea.getPrice());
        }
        catch(IllegalArgumentException | IOException e){
            System.out.println(e.getMessage());
        }
    }
}

abstract class MilkTeaDecorator extends MilkTea{
    protected MilkTea milkTea;

    public MilkTeaDecorator(MilkTea milkTea){
        this.milkTea = milkTea;
    }

    public void decorator() throws IOException {}
}

class SizeDecorator extends MilkTeaDecorator{

    private final Map<String, Integer> sizeToPrice;

    public SizeDecorator(MilkTea milkTea) {
        super(milkTea);
        sizeToPrice = new HashMap<>();
        sizeToPrice.put("Medium", 6);
        sizeToPrice.put("Large", 8);
        sizeToPrice.put("Super", 10);
    }

    public void decorator() throws IOException {
        System.out.print("请选择奶茶的大小：");
        GetThePrice.getThePrice(1, sizeToPrice, milkTea);
    }
}

class BaseDecorator extends MilkTeaDecorator{

    private final Map<String, Integer> baseToPrice;
    public BaseDecorator(MilkTea milkTea) {
        super(milkTea);
        baseToPrice = new HashMap<>();
        baseToPrice.put("Oolong tea", 2);
        baseToPrice.put("Green tea", 3);
        baseToPrice.put("Black tea", 4);
    }

    public void decorator() throws IOException {
        System.out.print("请选择奶茶的茶底：");
        GetThePrice.getThePrice(2, baseToPrice, milkTea);
    }
}

class ChargeDecorator extends MilkTeaDecorator{
    private final Map<String, Integer> chargeToPrice;
    public ChargeDecorator(MilkTea milkTea) {
        super(milkTea);
        chargeToPrice = new HashMap<>();
        chargeToPrice.put("Not add", 0);
        chargeToPrice.put("Pearl", 1);
        chargeToPrice.put("Coconut", 2);
        chargeToPrice.put("Milk cover", 3);
    }

    public void decorator() throws IOException {
        String[] charge_input = new String[1];
        do{
            System.out.print("请选择奶茶的加料：");
            GetThePrice.getThePrice(chargeToPrice, milkTea, charge_input);
        }while(!charge_input[0].equals("Not add"));
    }
}