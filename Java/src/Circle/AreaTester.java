package Circle;

public class AreaTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Circle c =new Circle();
		c.radius=50;    //԰�İ뾶 
		Rectangle r = new Rectangle();  //rΪԲ�ľ������
		r.width=20;   //���εĿ�
		r.height=30;//���εĸ�
		System.out.print("Circle  has area"+c.area());    //Բ����ε�area��ͬ������
		System.out.print("Rectangle has area"+ r.area());

	}

}
