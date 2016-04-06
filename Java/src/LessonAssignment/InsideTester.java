
public class InsideTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Circle c1= new Circle();
		c1.radius=8;
		Circle c2= new Circle();
		c2.radius=15;
		Rectangle r = new Rectangle();
		r.width=20;
		r.height=30;
		System.out.print("Circle 1 fits inside Rectangle:"+c1.fitsInside(r));
		System.out.print("Circle 2 fits inside Rectangle:"+c2.fitsInside(r));
		

	}

}
