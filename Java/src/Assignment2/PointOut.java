package Assignment2;

/**
 * Created by MAMIAN on 16/4/30.
 */
public class PointOut {
    public static void main(String args[]){
        Point p = new Point(3,4,5);  //
        Point origin= new Point(0,0,0);//原点
        System.out.print(p.getDistance(origin));  //首先调用p,再调用getDistance
    }

}
