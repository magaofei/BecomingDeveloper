package review;

/**
 * Created by MAMIAN on 16/6/20.
 */
public class Point {

     double x;
     double y;
     double z;

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getDistance(Point p){
        double distance = Math.sqrt(Math.pow(x-p.x, 2)+
                Math.pow(y-p.y, 2)+Math.pow(z-p.z, 2));
        return distance;
    }
    public double Distance(Point p){
        double distance2=Math.sqrt((p.x-x)*(p.x-x)+(p.y-y)*(p.y-y)+(p.z-z)*(p.z-z));
        return distance2;
    }

    public static void main(String[] args) {

        Point p1 = new Point(5,4,7);
        Point p2 = new Point(0,0,0);
        System.out.println("p1点与p2点的空间距离是："+p1.getDistance(p2));
        System.out.println("p1点与p2点的空间距离是："+p1.Distance(p2));
    }  }


