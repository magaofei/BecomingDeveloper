package review;

//import com.sun.org.apache.xpath.internal.operations.String;
import com.sun.tools.javac.file.SymbolArchive;
import com.sun.xml.internal.fastinfoset.util.StringArray;
import com.sun.xml.internal.xsom.impl.Ref;

/**
 * Created by MAMIAN on 16/6/15.
 */
public class Test {
//        String x="1";
//    int y;
//String s1="java";
    public static void main(String args[]) {
//        int z=2;
//        System.out.println(x+y+z);


//        String s1="abc"+"def";
//        String s2=new String(s1);
//        if(s1==s2)
//            System.out.println(s2+"==successed");
//        if (s1.equals(s2))
//            System.out.println(".equals");

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

//        int z=2;
        //System.out.println(x+y+z);
//        String friends[]={"lisa","bily","kessy"};
//        for (int i = 0; i < 5; i++) {
//            System.out.println(friends[i]);
//        }
//        int  b[][]={{1}, {2,2}, {2,2,2}};
//        int sum=0;
//        for(int i=0;i<b.length;i++) {
//            for(int j=0;j<b[i].length;j++) {
//                sum*=b[i][j];               //如果*改为+，就选B
//            }
//        }
//        System.out.println("sum="+sum);

//        String  s12=new String("abc");
//        String  s22=new String("abc");
//        boolean b1=s12.equals(s22);
//        boolean b2=(s12==s22);
//        System.out.print(b1+"   "+b2);    true   false

//        int  x=4;
//        System.out.println("value  is  "+ ((x>4) ? 99.9:9));


//                int z=2;
//                Test t=new Test();
//                System.out.println(t.s1+z);
        //int x=1;int y;
        //System.out.print(((y=1)==0)&((x=6)==6));    false

        // System.out.print(((y=1)==0)&&((x=6)==6));   //false
//        int x = 10;
//        int i = x++;
//        System.out.print("i=" + i);
//        System.out.println("x=" + x);
//
//       int  x=13;int y=4;
//        System.out.print(x%y != 0);

//        int x;
//        int a[] = {0, 0, 0, 0, 0, 0};
//        calculate(a, a[5]);
//        System.out.println("the value of a[0] is " + a[0]);
//        System.out.println("the value is a[5] is " + a[5]);
//    }
//    static int calculate(int x[], int y) {
//        for (int i = 1; i < x.length; i++)
//            if (y < x.length)
//                x[i] = x[i - 1] + 1;
//        return x[0];
//    }

//        int i, s = 0 ;
//        int a[ ] = { 10 , 20 , 30 , 40 , 50 , 60 , 70 , 80 , 90 };
//        for ( i = 0 ; i < a.length ; i ++ )
//            if ( a[i]%3 == 0 )  s += a[i] ;
//        System.out.println("s="+s);

        class Person {
            String name, department;
            int age;

            public Person(String n) {
                name = n;
            }

            public Person(String n, int a) {
                name = n;
                age = a;
            }

            public Person(String n, String d, int a) {

                // 完成Person(String n, int a)的逻辑
                //this(n,a);
                department = d;
            }
        }

//            int a = 0;
//            int b = 0;
//            int c = 0;
            int count = 0;

        for (int i=100;i<=999;i++){
            if (i/100!=i/10%10 && i/100!=i%10 && i/10%10!=i%10) {
                System.out.print(i+" ");
                count++;
                if (count%10==0)
                    System.out.println();
            }




//            a = i/100;
//             b = i/10%10;
//             c = i%10;
//            if (a!=b&&a!=c&&b!=c){
//                System.out.print(i+" ");
//                m++;
//                if (m%10 ==0){
//                    System.out.println();
//                }

//        double x = 1;
//        double y = 2;
//        double z = 3;
//        System.out.print(y += z-- / ++x);
//        System.out.print("y"+y+"z"+z+"x"+x);


    }}}







