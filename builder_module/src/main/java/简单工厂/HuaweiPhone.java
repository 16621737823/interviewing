package 简单工厂;

public class HuaweiPhone implements Phone {
    @Override
    public void make() {
        System.out.println("生产华为手机");
    }
}
