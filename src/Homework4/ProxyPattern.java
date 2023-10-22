package Homework4;

public class ProxyPattern {
    public static void main(String[] args){
        User QuerySystem = new ProxyUser();
        QuerySystem.charge();
        QuerySystem.query();
        QuerySystem.exit();
    }
}
