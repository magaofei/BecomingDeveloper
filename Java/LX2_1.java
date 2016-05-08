
public class LX2_1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("静态变量x="+StaticDemo.getX());
		System.out.println("实例变量y="+StaticDemo.getY()); // 非法，编译将出错
		StaticDemo a= new StaticDemo();
		StaticDemo b= new StaticDemo();
		a.setX(1);
		a.setY(2);
		b.setX(3);
		b.setY(4);
		System.out.println("静态变量a.x="+a.getX());
		System.out.println("实例变量a.y="+a.getY());
		System.out.println("静态变量b.x="+b.getX());
		System.out.println("实例变量b.y="+b.getY());
		}


}
