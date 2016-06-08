package Assignment4;

/**
 * Created by MAMIAN on 16/6/8.
 */
class Shape{
    void draw(){
    }
    void erase(){
    }
}
class Circle extends Shape{
    @Override
    void draw() {
        System.out.println("Circle.draw()");
    }
    @Override
    void erase() {
        System.out.println("Circle..erase()");
    }
}
class Square extends Shape{
    @Override
    void draw() {
        System.out.println("Square.draw()");
    }
    @Override
    void erase() {
        System.out.println("Square.erase()");
    }
}
class Triangle extends Shape{

    @Override
    void draw() {
        System.out.println("Triangle.draw()");
    }

    @Override
    void erase() {
        System.out.println("Triangle.erase()");
    }
}
public class BindingTester {
    public static void main(String[] args) {
        Shape[] shape = new Shape[9];
        int n;
        for(int i = 0; i<shape.length;i++){
            n = (int)(Math.random()*3);
            switch(n){
                case 0:
                    shape[i] = new Circle();
                    break;
                case 1:
                    shape[i] = new Square();
                    break;
                case 2:
                    shape[i] = new Triangle();
                    break;
            }
        }
        for (Shape oneS:shape){
            oneS.draw();
        }
    }
}

