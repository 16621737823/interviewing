package 工厂方法;

// 具体产品 - 电动汽车
public class ElectricCar implements Car {
    @Override
    public void drive() {
        System.out.println("驾驶电动汽车...");
    }
}
