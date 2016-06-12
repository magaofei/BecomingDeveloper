package Assignment4;

/**
 * Created by MAMIAN on 16/6/10.
 */
class Horse {
    public void running(){
        System.out.println("Horse running()");
    }
    public static void walk(){
        System.out.println("Horse walk()");
    }
}
class WhiteHorse extends Horse {
    public void running(){
        System.out.println("WhiteHorse running()");
    }
    public static void walk(){
        System.out.println("WhiteHorse walk()");
    }
}
public class MethodHidden {
    public static void main(String[] args) {
        Horse horse = new WhiteHorse();
        horse.running();
        horse.walk();
        WhiteHorse whiteHorse = (WhiteHorse)horse;
        whiteHorse.running();
        whiteHorse.walk();
    }
}
