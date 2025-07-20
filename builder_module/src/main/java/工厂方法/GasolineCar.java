package 工厂方法;

// 具体产品 - 燃油汽车
public class GasolineCar implements Car {
    @Override
    public void drive() {
        System.out.println("驾驶燃油汽车...");
    }
}
