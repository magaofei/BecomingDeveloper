package Assignment1;


 /* Created by MAMIAN on 16/3/30.
*/
public class LessonAssignment_3ArrayCalculate {
    public static void main(){   //计算0+1+...+100
        int num=0;
        int arrayinput[]=new int[100];
        for (int i = 0; i <=100 ; i++) {
            arrayinput[i]=i;
            num = arrayinput[i]+i;
        }
        System.out.print("0+1+...+100的值为"+num);
    }
}
