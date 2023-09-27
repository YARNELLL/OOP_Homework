package Homework2.part1;

/**
 * @author 吴禹 2023214309
 */

public class E_mall {
    public static void main(String[] args){
        System.out.println("购买电脑：");
        ElectronicEquipmentFactory computerFactory = new ComputerFactory();
        ElectronicEquipment computer = computerFactory.produce();
        computer.run();
        System.out.println("-------------");
        System.out.println("购买手机：");
        ElectronicEquipmentFactory phoneFactory = new PhoneFactory();
        ElectronicEquipment phone = phoneFactory.produce();
        phone.run();
        System.out.println("-------------");
        System.out.println("购买相机：");
        ElectronicEquipmentFactory cameraFactory = new CameraFactory();
        ElectronicEquipment camera = cameraFactory.produce();
        camera.run();
    }
}

interface ElectronicEquipment{
    void run();
}

class Computer implements ElectronicEquipment{

    @Override
    public void run() {
        System.out.println("Computer Running.");
    }
}

class Phone implements ElectronicEquipment{

    @Override
    public void run() {
        System.out.println("Phone Running.");
    }
}

class Camera implements ElectronicEquipment{

    @Override
    public void run() {
        System.out.println("Camera Running.");
    }
}

interface ElectronicEquipmentFactory{
    ElectronicEquipment produce();
}

class ComputerFactory implements ElectronicEquipmentFactory{

    @Override
    public ElectronicEquipment produce() {
        System.out.println("Producing a computer");
        return new Computer();
    }
}
class PhoneFactory implements ElectronicEquipmentFactory{

    @Override
    public ElectronicEquipment produce() {
        System.out.println("Producing a phone");
        return new Phone();
    }
}

class CameraFactory implements ElectronicEquipmentFactory{

    @Override
    public ElectronicEquipment produce() {
        System.out.println("Producing a camera");
        return new Camera();
    }
}