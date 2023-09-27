package Homework2.part1;

import java.util.Scanner;

public class E_mall {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("请选择购买的电子产品（computer，phone，camera）：");
        String equipmentType = sc.next();
        ElectronicEquipmentFactory factory = new ElectronicEquipmentFactory();
        ElectronicEquipment equipment = factory.getElectronicEquipment(equipmentType);
        if(equipment != null){
            equipment.Run();
        }
        else{
            System.out.println("请输入正确的电子产品名称。");
        }
        sc.close();
    }
}

interface ElectronicEquipment{
    void Run();
}

class Computer implements ElectronicEquipment{

    @Override
    public void Run() {
        System.out.println("Computer Running.");
    }
}

class Phone implements ElectronicEquipment{

    @Override
    public void Run() {
        System.out.println("Phone Running.");
    }
}

class Camera implements ElectronicEquipment{

    @Override
    public void Run() {
        System.out.println("Camera Running.");
    }
}

class ElectronicEquipmentFactory{
    public ElectronicEquipment getElectronicEquipment(String equipmentType){
        ElectronicEquipment equipment = null;
        if(equipmentType.equalsIgnoreCase("computer")){
            equipment = new Computer();
        }
        else if(equipmentType.equalsIgnoreCase(("phone"))){
            equipment = new Phone();
        }
        else if(equipmentType.equalsIgnoreCase(("camera"))){
            equipment = new Camera();
        }
        return equipment;
    }
}