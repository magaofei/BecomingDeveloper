package LessonAssignment;

import java.util.Scanner;

/**
 * Created by MAMIAN on 16/3/29.
 */
public class LessonAssignment_2InputArrayStatistics {
    public static void main(){
        int inputdata[] = new int[19];
        int z = 0;//正数
        int l = 0; // 零的个数
        int f = 0; // 负数的个数
        for(int i=0;i<inputdata.length;i++ ){
            Scanner str = new Scanner(System.in);
            inputdata[i] = Integer.parseInt(str.next());    //分别赋值并且转换成INT
            if (inputdata[i]>0){
                    z++;
            }else if(inputdata[i]==0){
                    l++;
            }else {
                    f++;
            }

    }
        System.out.print("正数的个数为"+z+"零的个数为"+l+"负数的个数为"+f);

    }
}
