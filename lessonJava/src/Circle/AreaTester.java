package Circle;

public class AreaTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Circle c =new Circle();
		c.radius=50;    //???? 
		Rectangle r = new Rectangle();  //r??????????
		r.width=20;   //???¦Å??
		r.height=30;//???¦Å??
		System.out.print("Circle  has area"+c.area());    //?????¦Å?area?????????
		System.out.print("Rectangle has area"+ r.area());

	}

}
