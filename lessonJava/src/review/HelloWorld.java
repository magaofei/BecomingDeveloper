package review;

/**
 * Created by MAMIAN on 16/6/20.
 */
public class HelloWorld {

    // ���徲̬����score1
    static int score1 = 86;
    // ���徲̬����score2
    static int score2 = 92;
    // ���徲̬����sum������ɼ��ܷ֣��������ܷ�
    public  static   int sum() {
        return score1 + score2;

    }
    public static void main(String[] args) {

        // ���þ�̬����sum�����շ���ֵ
        int allScore = sum();

        System.out.println("�ܷ֣�" + allScore);
    }
}

