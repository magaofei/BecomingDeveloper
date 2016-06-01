package Assignment4;

/**
 * Created by MAMIAN on 16/6/1.
 */
public class TestStudentPerson {
    public static void main(String args[]){
        Student a = new Student();
        a.classno="100";a.sno="101";a.name="zhanghan";
        System.out.print(a.classno);

    }

}
class Person{   //构造函数Person
    public  String name; public char sex; public int age;//公共域,姓名性别年龄
    public void Person(String name,char sex,int age){   //构造方法

    }
}

class Student extends Person{
    public void Student(String name,char sex, int age,String sno,String classno){
        //super(name,sex,age);语句错误
        super.Person(name, sex, age);
    }
    public String classno;public String sno;   //班号,学号
    public void updateAge(int age){             //构造方法,更新年龄

    }

    @Override
    public String toString() {
        return super.toString();
    }
}