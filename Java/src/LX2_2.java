public class LX2_2 {
	public static void main(String[] args) {
	AccessDemo a= new AccessDemo();
	a.x=1;
	a.y=2;
	a.z=3;
	public int getA(){
		return a.y;
	}
	public int setB(){
		return a.y;
	}
	System.out.println("public成员变量a.x="+a.x);
	System.out.println("private成员变量a.y="+a.y);
	System.out.println("无修饰符成员变量a.z="+a.z);
	}
	}