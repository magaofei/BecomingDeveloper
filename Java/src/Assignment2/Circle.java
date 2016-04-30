package Assignment2;

/**
 * Created by MAMIAN on 16/4/30.
 */
public class Circle {
    Point o;//  一个叫做o的点
    double radius;//半径

    public Circle(Point p,double r){   //构造函数
        o=p;radius=r;
    }

    public void contains(Point p){      //之前放错类了
        if((p.x-o.x)*(p.x-o.x)+(p.y+o.y)*(p.x+o.y)<=radius*radius){   //点到圆心的距离小于
            System.out.print("在圆中");
        }else {
            System.out.print("不在圆中");
        }

    }


}
