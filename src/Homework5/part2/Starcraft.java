package Homework5.part2;

public class Starcraft {
    public static void main(String[] args){

    }
}

class Kerrigan{
    private static Kerrigan kerrigan;

    private Kerrigan(){
        kerrigan = null;
    }

    synchronized public static Kerrigan getKerrigan(){
        if (kerrigan == null){
            kerrigan = new Kerrigan();
        }
        return kerrigan;
    }
}
