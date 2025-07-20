package 简单工厂;

public class XiaomiPhone implements Phone {
    @Override
    public void make() {
        System.out.println("生产小米手机");
    }
}