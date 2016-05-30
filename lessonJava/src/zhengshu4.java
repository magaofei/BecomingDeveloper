/**
 * Created by MAMIAN on 16/5/30.
 */
import java.lang.Math;
public class zhengshu4{
    public static void main(String[] args) {
        int MinNum,MaxNum,n=0;
        int[] array=new int[100];
        array[0]=(int)(Math.random()*100);
        MinNum=array[0];
        MaxNum=array[0];
        System.out.println("数列为：");
        System.out.print(array[0]+" ");
        for(int i=0;i<100;i++)     {
            array[i]=(int)(Math.random()*100);
            if(array[i]>50)
                n++;
            if(array[i]>=MaxNum)
                MaxNum=array[i];
            if(array[i]<=MinNum)
                MinNum=array[i];
            System.out.print(array[i]+" ");
        }
            System.out.println();
            System.out.println("MinNum="+MinNum);
            System.out.println("MaxNum="+MaxNum);
            System.out.println("大于50的整数个数有:"+n);
    }
}