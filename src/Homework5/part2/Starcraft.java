package Homework5.part2;

public class Starcraft {
    public static void main(String[] args){
        Kerrigan kerrigan = Kerrigan.getInstance();
        Zerg zerg1 = new Zerg(kerrigan, "zerg1");
        Zerg zerg2 = new Zerg(kerrigan, "zerg2");
        Zerg zerg3 = new Zerg(kerrigan, "zerg3");
        new Thread(zerg1).start();
        new Thread(zerg2).start();
        new Thread(zerg3).start();
    }
}

class Zerg implements Runnable{
    private Kerrigan kerrigan;
    private String zerg_name;

    public Zerg(Kerrigan kerrigan, String zerg_name) {
        this.kerrigan = kerrigan;
        this.zerg_name = zerg_name;
    }

    @Override
    public void run(){
        for(int time =0;time < 10;time++){
            kerrigan.work(this.zerg_name);
            try {
                Thread.sleep((int)(Math.random() * 100));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Kerrigan{
    private static Kerrigan kerrigan;

    private Kerrigan(){
        kerrigan = null;
    }

    synchronized public static Kerrigan getInstance(){
        if (kerrigan == null){
            kerrigan = new Kerrigan();
        }
        return kerrigan;
    }

    synchronized public void work(String name){
        System.out.println("Kerrigan is working for " + name + ". Kerrigan is " + this.hashCode());
    }
}
