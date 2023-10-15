package Homework3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class GetThePrice {
    public static void getThePrice(int pattern, Map<String, Integer> toPrice, MilkTea milkTea) throws IOException {
        for (Map.Entry<String, Integer> entry : toPrice.entrySet()){
            System.out.print(entry.getKey() + "：" + entry.getValue() + "```");
        }
        System.out.println();
        String input = (new BufferedReader(new InputStreamReader(System.in))).readLine();
        if(toPrice.containsKey(input)){
            milkTea.addPrice(toPrice.get(input));
            switch(pattern){
                case 1 :
                    milkTea.setSize(input);
                    break;
                case 2 :
                    milkTea.setBase(input);
                    break;
                default:
                    break;
            }
        }
        else{
            throw new IllegalArgumentException("错误: 请输入正确的名称。");
        }
    }

    public static void getThePrice(Map<String, Integer> toPrice, MilkTea milkTea, String[] charge_input)
            throws IOException {
        for (Map.Entry<String, Integer> entry : toPrice.entrySet()){
            System.out.print(entry.getKey() + "：" + entry.getValue() + "```");
        }
        System.out.println();
        String input = (new BufferedReader(new InputStreamReader(System.in))).readLine();
        if(toPrice.containsKey(input)){
            charge_input[0] = input;
            milkTea.addPrice(toPrice.get(input));
            if(!input.equals("Not add")){
                milkTea.setCharge(input);
            }
        }
        else{
            throw new IllegalArgumentException("错误: 请输入正确的名称。");
        }
    }
}
