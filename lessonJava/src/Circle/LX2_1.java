
public class LX2_1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("��̬����x="+StaticDemo.getX());
		System.out.println("ʵ������y="+StaticDemo.getY()); // �Ƿ������뽫����
		StaticDemo a= new StaticDemo();
		StaticDemo b= new StaticDemo();
		a.setX(1);
		a.setY(2);
		b.setX(3);
		b.setY(4);
		System.out.println("��̬����a.x="+a.getX());
		System.out.println("ʵ������a.y="+a.getY());
		System.out.println("��̬����b.x="+b.getX());
		System.out.println("ʵ������b.y="+b.getY());
		}


}
