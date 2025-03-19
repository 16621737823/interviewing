import org.springframework.beans.factory.FactoryBean;

public class UserFactoryBean implements FactoryBean<User> {

    private String name;
    private int age;

    // ��������
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // ���� User ����
    @Override
    public User getObject() throws Exception {
        System.out.println("getObject������");
        return new User(name, age);
    }

    // ���� User ����
    @Override
    public Class<?> getObjectType() {
        return User.class;
    }

    // �Ƿ��ǵ���
    @Override
    public boolean isSingleton() {
        return true; // ����ģʽ
    }
}