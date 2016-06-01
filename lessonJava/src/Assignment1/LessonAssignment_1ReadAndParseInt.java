package Assignment1;
/*
 * Created by MAMIAN on 16/3/26.
 */

/*     一.从命令行把2,4,6,8,9,1,3,7,5,九个数读进来,并且将这些数构成一个INT数组
*/
import java.util.Scanner;            //从命令行读入九个数放进int 数组里
public class LessonAssignment_1ReadAndParseInt {
    public static void main(String[]args){
        int stb[]= new int [9];
        System.out.print("请输入九个数");
        for (int i = 0; i <stb.length; i++) {    //运行九次  循环输入
            Scanner input=new Scanner(System.in);
            stb[i]=Integer.parseInt(input.next());   //字符串转换Int
            if (i==stb.length-1){
                System.out.print("输入完毕");
            }
        }

    }
}



