package exam;

/**
 * Created by MAMIAN on 16/6/16.
 */
class A
{
    int x = 2;
    public void setx(int i){
        x = i;
    }
    void printa()
    {
        System.out.println(x);
    }
}
class C extends A
{
    int x=100;
    void printb()
    {
        super.x = super.x +10 ;
        System.out.println
                ("super.x= " + super.x +
                        "  x= " + x);
    }
}

public class Exam4_4Test
{
    public static void main(String[] args)
    {
        C c = new C();
        c.printb();
//运行结果为：______super.x= 12  x= 100
        c.setx(6);  // 将继承来的x值设置为6
        c.printb();
//运行结果为：_____super.x= 22  x=6____
    }
}
