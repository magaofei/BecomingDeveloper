package Assignment4;

/**
 * Created by MAMIAN on 16/6/1.
 */
public class Sub extends Base {
    public void methodOfSub(){
        publicVarOfBase = 2;
        defaultVarofBase = 2;
        //privateVarOfBase = 2;
        methodOfBase();
    }
    public static void main(String args[]){
        Sub sub = new Sub();
        sub.publicVarOfBase = 3;
        //sub.privateVarOfBase = 3;
        sub.defaultVarofBase = 3;
        sub.methodOfBase();
        sub.methodOfSub();
    }
}
