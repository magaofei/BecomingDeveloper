package ObjectAndMethod3;

public class ScoreTester {
	public static void main(String[] args){
		//giveScore(Score.EXCELLENT);//利用switch调用枚举值        例2-17
		for(Score s:Score. values()){  //增强for循环，要求jdk5以上版本   循环输出所有枚举值
			System.out.println(s);
		}
		Score s= Score.valueOf("EXCELLENT");   //从字符串得到枚举类型
		System.out.println(s.toString());      //输出s的字符串描述
		System.out.println("ordinal of EXCELLENT is "+s.ordinal()); //输出s的位置索引
	}
	/*  例2-17
	public static void giveScore(Score s){   //形参
		
		switch(s){      
		case EXCELLENT:
			System.out.println("Excellent");
			break;
		
		case QUALIFIED:
			System.out.println("Qualified");
			break;
		case FAILED:
			System.out.println("Failed");
			break;
			
	}*/
		
		
		
	}
	
//}
