package review;
import com.sun.tools.javac.file.SymbolArchive;
import com.sun.xml.internal.fastinfoset.util.StringArray;
import com.sun.xml.internal.xsom.impl.Ref;

/**
 * Created by MAMIAN on 16/6/15.
 */
public class Test {
    String x="1";
    int y;
    public static void main(String args[]){
        String s1="abc"+"def";
        String s2=new String(s1);
        if(s1==s2)
            System.out.println(s2+"==successed");
        if (s1.equals(s2))
            System.out.println(".equals");
        /*
        int num=1;
        while (num<6){
            System.out.print(num);
            if (num%2==0){
                continue;
            }else {
                num++;
            }
        }
           */

        int z=2;
        //System.out.println(x+y+z);
        String friends[]={"lisa","bily","kessy"};
        for (int i = 0; i < 5; i++) {
            System.out.println(friends[i]);
        }
    }

}


