package ����;

// ����ʽ
public class EagerSingleton {
    // �������ʱ�ʹ���ʵ��
    private static final EagerSingleton instance = new EagerSingleton();

    // ˽�й��캯����ֹ�ⲿʵ����
    private EagerSingleton() {}

    // �ṩȫ�ַ��ʵ�
    public static EagerSingleton getInstance() {
        return instance;
    }

    // ��������
    public void doSomething() {
        System.out.println("EagerSingleton is doing something.");
    }
}
