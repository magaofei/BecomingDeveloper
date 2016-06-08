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
        System.out.println("叫声");
    }
}
class Cat extends Animal{
    private String eyescolor;
    Cat3(String n, String c){
        super(n);
        eyescolor = c;
    }
    public void enjoy(){
        System.out.println("猫叫声");
    }
}
class Dog extends Animal{
    private String furcolor;
    Dog3(String n, String c){
        super(n);
        furcolor = c;
    }
    public void enjoy(){
        System.out.println("狗叫声");
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
public class TestBinding {
    public static void main(String[] args) {
        Cat c = new Cat ("catname","blue");
        Dog d = new Dog ("dogname","black");
        Lady l1 = new Lady("l1",c);
        Lady l2 = new Lady("l2",d);
        l1.myPetEnjoy();
        l2.myPetEnjoy();
    }
}
