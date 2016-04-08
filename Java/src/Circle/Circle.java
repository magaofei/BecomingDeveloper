package Circle;

public class Circle {

	/**
	 * @param args
	 */
	
	
	static double PI=3.14159265;  //�е�ֵ
	int radius;
	public double circumference(){   //�����ܳ�
		return 2*PI*this.radius;
	}
	public double area(){    //��ͬ������  
		return PI*radius*radius;
	}
	public void enlarge(int factor){   //�������ķ���   �ı�Բ�İ뾶   �õ�ʱ��ֻ��Ҫ�������ֵ
		radius= radius * factor;    //
	}
	public boolean fitsInside(Rectangle r){   //�ж�һ��Բ�Ƿ���һ���������� ��ҪRectangle��Ķ�����Ϊ����
		return (2*radius<r.width)&&(2*radius<r.height);   //�ж�ֱ���Ƿ�С�ھ��εĿ�͸�
	}

}
