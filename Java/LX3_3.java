public class LX3_3 {
	public static void main(String[] args) {
	Paratran p=new Paratran();
	ChangePara c= new ChangePara();
	c.change1(p.x,p.y); //��ֵ���ã����ݵ��ǻ�������int
	c.change2(p);  //����ַ���ã����ݵ��Ƕ���
	System.out.println("�������ú� x="+p.x+", y="+p.y);
}
}