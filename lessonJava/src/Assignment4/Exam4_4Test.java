package Assignment4;

/**
 * Created by MAMIAN on 16/6/1.
 */
class A
{
    static int x = 2;
    public void setx(int i){
        x = i;    //把i的值传给x
    }
    void printa()
    {
        System.out.println(x);   //输出x
    }
}

class B extends A    //A继承B
{
    int x=100;      //B的x=100
    void printb()
    {
        super.x = super.x +10 ;    //A的x=4+10   //此时A的x是4,而不是2
        System.out.println
                ("super.x= " + super.x +"  x= " + x);  //先输出A的x,再输出B的x
    }  }

public class Exam4_4Test
{
    public static void main(String[] args)
    {
        A a = new A ();
        a.setx(4);
        a.printa();
        B b = new B();
        b.printb();
        b.printa();
        b.setx(6);  // 将继承来的x值设置为6
        b.printb();
        b.printa();
        a.printa();
    }
}
