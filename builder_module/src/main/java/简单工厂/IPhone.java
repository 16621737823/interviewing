package 简单工厂;

public class IPhone implements Phone {
    @Override
    public void make() {
        System.out.println("生产苹果手机");
    }
}
