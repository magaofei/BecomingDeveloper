
public class EnlargeTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Circle c1= new Circle();
		c1.radius=50;
		Circle c2 = new Circle();
		c2.radius= 10;
		System.out.print("Circle1的周长"+c1.circumference());
		System.out.print("Circle2的周长"+c2.circumference());
		c2.enlarge(4);  //改变圆的周长
		System.out.print("Circle2扩大后的周长："+c2.circumference());
	}

}
