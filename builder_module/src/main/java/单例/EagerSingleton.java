package 单例;

// 饿汉式
public class EagerSingleton {
    // 在类加载时就创建实例
    private static final EagerSingleton instance = new EagerSingleton();

    // 私有构造函数防止外部实例化
    private EagerSingleton() {}

    // 提供全局访问点
    public static EagerSingleton getInstance() {
        return instance;
    }

    // 其他方法
    public void doSomething() {
        System.out.println("EagerSingleton is doing something.");
    }
}
