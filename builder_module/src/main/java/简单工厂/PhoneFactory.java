package �򵥹���;

public class PhoneFactory {
    // ��̬�������������ݴ������������ͬ��Ʒ
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
                throw new IllegalArgumentException("δ֪�ֻ�Ʒ��: " + brand);
        }
    }
}
