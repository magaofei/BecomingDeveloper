
public class AreaTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Circle c =new Circle();
		c.radius=50;    //园的半径 
		Rectangle r = new Rectangle();  //r为圆的矩形面积
		r.width=20;   //矩形的宽
		r.height=30;//矩形的高
		System.out.print("Circle  has area"+c.area());    //圆与矩形的area相同方法名
		System.out.print("Rectangle has area"+ r.area());

	}

}
