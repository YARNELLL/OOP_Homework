package Homework2.part2;

public class FactoryProduction {
    public static void main(String[] args){
        AbstractFactory clothFactory = new ClothFactory();
        System.out.println("生产服装-外套：");
        Cloth coat = clothFactory.getCloth("coat");
        coat.tailor();
        coat.dye();
        System.out.println("-------------");
        System.out.println("生产服装-裤子：");
        Cloth trouser = clothFactory.getCloth("trouser");
        trouser.tailor();
        trouser.dye();
        System.out.println("-------------");
        AbstractFactory unitFactory = new UnitFactory();
        System.out.println("生产器件-螺丝：");
        Unit screw = unitFactory.getUnit("screw");
        screw.addMaterial();
        screw.process();
        screw.cooling();
        System.out.println("-------------");
        System.out.println("生产器件-螺帽：");
        Unit nut = unitFactory.getUnit("nut");
        nut.addMaterial();
        nut.process();
        nut.cooling();
    }
}

//服装抽象接口
interface Cloth{
    //裁剪
    void tailor();
    //染色
    void dye();
}
//外套
class Coat implements Cloth{

    @Override
    public void tailor() {
        System.out.println("A coat tailoring.");
    }

    @Override
    public void dye() {
        System.out.println("A coat dying.");
    }
}
//裤子
class Trouser implements Cloth{

    @Override
    public void tailor() {
        System.out.println("A trouser tailoring.");
    }

    @Override
    public void dye() {
        System.out.println("A trouser dying.");
    }
}

interface Unit{
    //加装原料
    void addMaterial();
    //加工
    void process();
    //冷却定型
    void cooling();
}
//螺丝
class Screw implements Unit{

    @Override
    public void addMaterial() {
        System.out.println("Adding material of screw.");
    }

    @Override
    public void process() {
        System.out.println("Processing a screw.");
    }

    @Override
    public void cooling() {
        System.out.println("Cooling a screw.");
    }
}
//螺帽
class Nut implements Unit{

    @Override
    public void addMaterial() {
        System.out.println("Adding material of nut.");
    }

    @Override
    public void process() {
        System.out.println("Processing a nut.");
    }

    @Override
    public void cooling() {
        System.out.println("Cooling a nut.");
    }
}
interface AbstractFactory{
    Cloth getCloth(String clothType);

    Unit getUnit(String unitType);
}

class ClothFactory implements AbstractFactory{

    @Override
    public Cloth getCloth(String clothType) {
        Cloth cloth = null;
        if(clothType.equalsIgnoreCase("coat")){
            cloth = new Coat();
        }
        else if(clothType.equalsIgnoreCase("trouser")){
            cloth = new Trouser();
        }
        return cloth;
    }

    @Override
    public Unit getUnit(String unitType) {
        return null;
    }
}

class UnitFactory implements AbstractFactory{

    @Override
    public Cloth getCloth(String clothType) {
        return null;
    }

    @Override
    public Unit getUnit(String unitType) {
        Unit unit = null;
        if(unitType.equalsIgnoreCase("screw")){
            unit = new Screw();
        }
        else if(unitType.equalsIgnoreCase("nut")){
            unit = new Nut();
        }
        return unit;
    }
}

