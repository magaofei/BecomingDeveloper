package review;

/**
 * Created by MAMIAN on 16/6/19.
 */
public class Demo1 {
    String name; // ��������name
    String sex; // ��������sex
    static int age;// ������̬����age
    // ���췽��
    public  Demo1() {
        System.out.println("ͨ�����췽����ʼ��name");
        name = "tom";
    }
    // ��ʼ����
    {
        System.out.println("ͨ����ʼ�����ʼ��sex");
        sex = "��";
    }

    // ��̬��ʼ����
     static{
        System.out.println("ͨ����̬��ʼ�����ʼ��age");
        age = 20;
    }

    public void show() {
        System.out.println("������" + name + "���Ա�" + sex + "�����䣺" + age);
    }

    public static void main(String[] args) {

        // ��������
        Demo1 demo = new Demo1();
        // ���ö����show����
        demo .show();

    }
}

