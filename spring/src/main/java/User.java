public class User {
    private String name;
    private int age;

    // ���췽����getter �� setter
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{name='" + name + "', age=" + age + "}";
    }

    // getter �� setter ʡ��
}