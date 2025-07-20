package 单例;

//双重锁版
public class DoubleCheckedLockingSingleton {
    // 使用volatile保证可见性和禁止指令重排序
    private volatile static DoubleCheckedLockingSingleton instance;

    private DoubleCheckedLockingSingleton() {}

    public static DoubleCheckedLockingSingleton getInstance() {
        if (instance == null) { // 第一次检查
            synchronized (DoubleCheckedLockingSingleton.class) {
                if (instance == null) { // 第二次检查
                    instance = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return instance;
    }
}
