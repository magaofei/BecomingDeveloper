package Circle;

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
		System.out.print("Circle1���ܳ�"+c1.circumference());
		System.out.print("Circle2���ܳ�"+c2.circumference());
		c2.enlarge(4);  //�ı�Բ���ܳ�
		System.out.print("Circle2�������ܳ���"+c2.circumference());
	}

}
