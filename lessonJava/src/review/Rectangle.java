package review;

/**
 * Created by MAMIAN on 16/6/20.
 */
public class Rectangle {
     int  width;
    int height;
    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int Perimeter(){
        return 2*(width+height);
    }

    public int Area(){
        return width*height;
    }

    public static void main(String[] args) {
        Rectangle r1 = new Rectangle(2,1);
        System.out.println("����r1������ǣ�"+r1.Area());
        System.out.println("����r1���ܳ��ǣ�"+r1.Perimeter());

    }
}

