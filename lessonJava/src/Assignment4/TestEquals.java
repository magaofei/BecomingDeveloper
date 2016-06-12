package Assignment4;

/**
 * Created by MAMIAN on 16/6/10.
 */
public class TestEquals {

    public static void main(String[] args) {
        Cat2 c1 = new Cat2(1,2,3);
        Cat2 c2 = new Cat2(1,2,3);
        Cat2 c3 = c1;
        System.out.println(c1==c2);
        System.out.println(c1.equals(c2));
        System.out.println(c3==c1);

    }


}

class Cat2{
    int color;
    int height,weight;
    public Cat2(int color, int height, int weight) {
        super();
        this.color = color;
        this.height = height;
        this.weight = weight;


    }
    public boolean equals(Object o){
//        if (o==this)
//            return true;
//        if(!(o instanceof Cat2))
//            return false;
        Cat2 c=(Cat2)o;
        return (c.color==color) && (c.height==height)&&(c.weight==weight);  //匹配


    }


}
