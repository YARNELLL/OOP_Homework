package Homework7.part2;

import java.util.*;
import java.io.*;

public class StatisticalWords {
    public static void main(String[] args) throws FileNotFoundException {
        Method1 method1 = new Method1();
        Method2 method2 = new Method2();
        Method3 method3 = new Method3();
        Method4 method4 = new Method4();
        method1.work();
        method2.work();
        method3.work();
        method4.work();
    }
}

abstract class CountWords{
    HashMap<String, Integer> word2cnt;
    List<String> sortedWord;

    public CountWords() {
        this.word2cnt = new HashMap<>();
    }

    abstract void sort();

    void count() throws FileNotFoundException {
        File file = new File("./src/Homework7/part2/text.txt");
        if(!file.exists()){
            System.out.println("文件不存在");
            return;
        }
        Scanner sc = new Scanner(file);
        while(sc.hasNextLine()){
            String line = sc.nextLine().trim();
            String[] words = line.split("\\W+");
            for(String word : words){
                if(!word.isEmpty()){
                    word2cnt.put(word, word2cnt.getOrDefault(word, 0) + 1);
                }
            }
        }
        sortedWord = new ArrayList<>(word2cnt.keySet());
    }

    void display(){
        for(String word : sortedWord){
            System.out.printf("单词：%-12s 出现次数：%d\n", word, word2cnt.get(word));
        }
    }

    public final void work() throws FileNotFoundException {
        count();
        sort();
        display();
    }
}

class Method1 extends CountWords{
    // 不排序
    @Override
    void sort() {
        System.out.println("不排序");
    }
}

class Method2 extends CountWords{
    // 按照字典序排序
    @Override
    void sort() {
        Collections.sort(this.sortedWord);
        System.out.println("按照字典序排序");
    }
}

class Method3 extends CountWords{
    // 按照单词长度排序
    @Override
    void sort() {
        this.sortedWord.sort(Comparator.comparingInt(String::length));
        System.out.println("按照单词长度排序");
    }
}

class Method4 extends CountWords{
    // 按照单词出现频率排序
    @Override
    void sort() {
        this.sortedWord.sort((o1, o2) -> word2cnt.get(o2) - word2cnt.get(o1));
        System.out.println("按照单词出现频率排序");
    }
}
