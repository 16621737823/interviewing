package 简单工厂;

public class PhoneFactory {
    // 静态工厂方法，根据传入参数创建不同产品
    public static Phone createPhone(String brand) {
        if (brand == null) {
            return null;
        }

        switch (brand.toLowerCase()) {
            case "huawei":
                return new HuaweiPhone();
            case "xiaomi":
                return new XiaomiPhone();
            case "iphone":
                return new IPhone();
            default:
                throw new IllegalArgumentException("未知手机品牌: " + brand);
        }
    }
}
