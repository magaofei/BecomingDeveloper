
public class ShapeTester1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Circle x;   //  自定义Circle类型 
		Rectangle y,z;
		x = new Circle();
		y= new Rectangle();
		z= new Rectangle();
		x.radius=50;    //x的半径    radius为Circle中的属性     
		z.width=68.94;    
		z.height=47.54;
		System.out.print(x.radius+"   "+y.width+"    "+z.width);
		
	}

}
