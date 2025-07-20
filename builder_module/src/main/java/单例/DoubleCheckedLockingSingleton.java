package ����;

//˫������
public class DoubleCheckedLockingSingleton {
    // ʹ��volatile��֤�ɼ��Ժͽ�ָֹ��������
    private volatile static DoubleCheckedLockingSingleton instance;

    private DoubleCheckedLockingSingleton() {}

    public static DoubleCheckedLockingSingleton getInstance() {
        if (instance == null) { // ��һ�μ��
            synchronized (DoubleCheckedLockingSingleton.class) {
                if (instance == null) { // �ڶ��μ��
                    instance = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return instance;
    }
}
