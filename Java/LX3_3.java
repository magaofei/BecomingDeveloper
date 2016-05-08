public class LX3_3 {
	public static void main(String[] args) {
	Paratran p=new Paratran();
	ChangePara c= new ChangePara();
	c.change1(p.x,p.y); //传值调用，传递的是基本类型int
	c.change2(p);  //传地址调用，传递的是对象
	System.out.println("方法调用后 x="+p.x+", y="+p.y);
}
}