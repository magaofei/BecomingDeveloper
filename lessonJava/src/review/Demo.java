package review;

/**
 * Created by MAMIAN on 16/6/20.
 */
public class Demo {

    public static void main(String[] args) {

        //System.out.println("舍罕王一共要赏"+sum(64)+"粒麦子");
        double count=1;   //double
        for (int i=2;i<65;i++)
            count*=2;
        System.out.println("舍罕王一共要赏"+count+"粒麦子");

    }

    /*
    public static double sum(int n){
        double sum = 1;
        for(int i = 2; i<=n; ++i)
            sum*=2;
        return sum;
    }
    */



}
