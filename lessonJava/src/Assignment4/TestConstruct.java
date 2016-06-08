package Assignment4;

/**
 * Created by MAMIAN on 16/6/8.
 */
 class Person{
    private String name;
    private String location;
    //必须在这里增加职称信息
    Person(String name){
        this.name = name;
        location = "beijing";
    }
    Person(String name,String location){
        this.name = name;
        this.location = location;
    }
    public String info(){
        return "name:" + name + "location:" + location;
    }
}
class Student extends Person {
    private String school;
    Student (String name,String school){   //构造函数
        this(name,school,"beijing");
    }
    Student (String n,String school, String l){ //构造函数输出父类
        super(n,l);
        this.school = school;
    }
    public String info(){  //Student的info 输出school
        return super.info() + "school:" + school;
    }
}
class Teacher extends Person{
    private String zhicheng;
    Teacher(String name,String zhicheng){
        this(name,zhicheng,"zhicheng");
    }
    Teacher(String n,String S,String zhicheng){
        super(n,S);
        this.zhicheng=zhicheng;
    }
    public String info(){
        return super.info()+"zhicheng"+zhicheng;
    }
}

public class TestConstruct {
    public static void main(String[] args) {
        Person p1 = new Person("A");
        Person p2 = new Person("B","shanghai");
        Student s1 = new Student("C","s1");
        Student s2 = new Student("D","s2","shanghai");
        Teacher t1= new Teacher("b","gaoji");
        Teacher t2=new Teacher("c","中山","高级");
        System.out.println(p1.info());
        System.out.println(p2.info());
        System.out.println(s1.info());
        System.out.println(s2.info());

    }}

