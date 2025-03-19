import org.springframework.beans.factory.FactoryBean;

public class UserFactoryBean implements FactoryBean<User> {

    private String name;
    private int age;

    // 设置属性
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // 创建 User 对象
    @Override
    public User getObject() throws Exception {
        System.out.println("getObject被调用");
        return new User(name, age);
    }

    // 返回 User 类型
    @Override
    public Class<?> getObjectType() {
        return User.class;
    }

    // 是否是单例
    @Override
    public boolean isSingleton() {
        return true; // 单例模式
    }
}