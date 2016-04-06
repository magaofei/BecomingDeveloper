
public class Circle {

	/**
	 * @param args
	 */
	
	
	static double PI=3.14159265;  //π的值
	int radius;
	public double circumference(){   //计算周长
		return 2*PI*this.radius;
	}
	public double area(){    //相同方法名  
		return PI*radius*radius;
	}
	public void enlarge(int factor){   //带参数的方法   改变圆的半径   用的时候只需要输入参数值
		radius= radius * factor;    //
	}
	public boolean fitsInside(Rectangle r){   //判断一个圆是否在一个正方形内 需要Rectangle类的对象作为参数
		return (2*radius<r.width)&&(2*radius<r.height);   //判断直径是否小于矩形的宽和高
	}

}
