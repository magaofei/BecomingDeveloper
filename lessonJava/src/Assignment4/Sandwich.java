package Assignment4;

/**
 * Created by MAMIAN on 16/6/8.
 */
class Meal {
     Meal(){
        System.out.println("Meal");
    }
}
class Bread {
     Bread(){
        System.out.println("Bread");
    }
}
class Cheese {
     Cheese(){
        System.out.println("Cheese");
    }
}
class Lettuse {
     Lettuse(){
        System.out.println("Lettuse");
    }
}
class Lunch extends Meal {
    Lunch(){
        System.out.println("Lunch");
    }
}
class PortableLunch extends Lunch {
    PortableLunch(){
        System.out.println("PortableLunch");
    }
}
class Sandwich extends PortableLunch {
    Bread b = new Bread();
    Cheese c = new Cheese();
    Lettuse l = new Lettuse();
    Sandwich(){
        System.out.print("Sandwich");
    }
    public static void main(String [] args){
        new Sandwich();

    }
}

