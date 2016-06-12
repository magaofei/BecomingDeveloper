package Assignment4;

/**
 * Created by MAMIAN on 16/6/8.
 */
class Animal{
    private String name;
    Animal (String name){
        this.name = name;
    }
    public void enjoy(){
        System.out.println("請汒");
    }
}
class Cat extends Animal{
    private String eyescolor;
    Cat (String n, String c){
        super(n);
        eyescolor = c;
    }
    public void enjoy(){
        System.out.println("癡請汒");
    }
}
class Dog extends Animal{
    private String furcolor;
    Dog (String n, String c){
        super(n);
        furcolor = c;
    }
    public void enjoy(){
        System.out.println("僩請汒");
    }
}
class Lady {
    private String name;
    private Animal pet;
    Lady (String name, Animal pet){
        this.name = name;
        this.pet = pet;
    }
    public void myPetEnjoy(){
        pet.enjoy();
    }
}
class Bird extends Animal{
    private String birdcolor;
    Bird(String n,String birdcolor){
        super(n);
        birdcolor=birdcolor;
    }
    public void enjoy(){System.out.print("纏請汒");}
}
public class TestBinding {
    public static void main(String[] args) {
        Cat c = new Cat ("catname","blue");
        Dog d = new Dog ("dogname","black");
        Bird b=new Bird("Birdname","yellow");
        Lady l1 = new Lady("l1",c);
        Lady l2 = new Lady("l2",d);
        Lady l3=new Lady("l3",b);
        l1.myPetEnjoy();
        l2.myPetEnjoy();
        l3.myPetEnjoy();
    }
}
