package review;

/**
 * Created by MAMIAN on 16/6/19.
 */
public class Father{
    int a=100;
    public void miner(){
        a--;
    }
    public static void main(String[] args){
        Father x = new Father();
        Son y = new Son();
        System.out.println(y.a);
        System.out.println( y.getA());
        y.miner();
        System.out.println(y.a);
        System.out.println(y.getA());
    }
}
class Son extends Father{
    int a = 0;
    public void plus(){
        a++;
    }
    public int getA() {
        return super.a;
    } }

