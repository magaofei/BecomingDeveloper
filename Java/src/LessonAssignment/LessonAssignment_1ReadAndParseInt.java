package LessonAssignment; /**
 * Created by MAMIAN on 16/3/26.
 */
import java.util.Scanner;            //从命令行读入九个数放进int 数组里
public class LessonAssignment_1ReadAndParseInt {
public static void main(String[]args){
    int stb[]= new int [8];
    System.out.print("请输入九个数");
    for (int i = 0; i <stb.length ; i++) {    //运行九次  循环输入   多于九次会溢出
        Scanner input=new Scanner(System.in);
        stb[i]=Integer.parseInt(input.next());   //字符串转换Int
    }

}
}