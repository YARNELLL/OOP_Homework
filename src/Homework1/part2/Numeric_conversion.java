package Homework1.part2;

import java.util.Scanner;

public class Numeric_conversion {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        double[] numbers = {1.1, 2.12, 3.123, 4.1234, 5.12345, 6.123456, 7.1234567, 8.12345678, 9.123456789, 10.1234567891};
        System.out.println("第一种情况：");
        print(3.1415926);
        System.out.println("第二种情况1：");
        print(3.1415926, 3);
        System.out.println("第二种情况2：");
        print(3.1415926, 10);
        System.out.println("第三种情况：");
        print(numbers);
        System.out.println("第四种情况：");
        print(numbers, 5);
        sc.close();
    }

    public static void print(double number){
        System.out.println(number);
    }

    public static void print(double number, int decimal){
        format(number, decimal);
    }

    public static void print(double[] numbers){
        for (double number : numbers) {
            System.out.print(number + " ");
        }
        System.out.println();
    }

    public static void print(double[] numbers, int decimal){
        for (double number : numbers) {
            format(number, decimal);
        }
    }

    public static void format(double number, int decimal){
        char[] number2chars = String.valueOf(number).toCharArray();
        int index = 0;
        for(; number2chars[index] != '.'; index++){
            System.out.print(number2chars[index]);
        }
        System.out.print(number2chars[index++]);
        //用于计数输出了小数点后几位
        int cnt = 0;
        for(; index < number2chars.length && cnt < decimal; index++, cnt++){
            System.out.print(number2chars[index]);
        }
        if(cnt < decimal){
            for(; cnt < decimal; cnt++){
                System.out.print("0");
            }
        }
        System.out.println();
    }
}
