/**
 * Created by MAMIAN on 16/5/30.
 */
public class sushu3 {
    public static void main(String args[]){
        int count=1;
        for (int i = 2; i <= 100; i++) {
            boolean boo=true;
            for (int j = 2; j<=(int)Math.sqrt(i); j++) {
                if(i%j==0){
                    boo=false;
                    break;
                }
            }
            if(boo){
                System.out.print(i+" ");// 打印素数
                if (count%5==0)
                    System.out.println();
                count++;
            }

        }
    }
}
