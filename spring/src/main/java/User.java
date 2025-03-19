public class User {
    private String name;
    private int age;

    // 构造方法、getter 和 setter
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{name='" + name + "', age=" + age + "}";
    }

    // getter 和 setter 省略
}