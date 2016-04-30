package Assignment2;

/**
 * Created by MAMIAN on 16/4/30.
 */
public class PointOut {
    public static void main(String args[]){
        Point p = new Point(3,4,5);  //   三维坐标点
        Point origin= new Point(0,0,0);//原点
        System.out.print(p.getDistance(origin));  //首先调用p,再调用getDistance

        Point p2=new Point(2,3);   //二维坐标点
        Circle c=new Circle(new Point(3,5),8);    //点的坐标为3,5   园的半径为8
        c.contains(p2);
    }




}
