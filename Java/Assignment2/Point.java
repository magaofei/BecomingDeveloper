package Assignment2;


/**
 * Created by MAMIAN on 16/4/27.
 */

public class Point {    //生成具有特定坐标的点对象,提供可以设置三个坐标的方法,提供可以计算该"点"距另外点距离平方的方法.
    double x,y,z;   //实参

    public Point(double _x,double _y,double _z){   //构造函数   //参数为三个坐标点
        x=_x;y=_y;z=_z;

    }
    public Point(double _x,double _y){  //二维空间点  构造函数 重载
        x=_x;y=_y;
    }
    public double getDistance(Point p){
        return Math.sqrt((x-p.x)*(x-p.x)+(y-p.y)*(y-p.y)+(z-p.z)*(z-p.z));  //参数减去原点的值
    }

}



