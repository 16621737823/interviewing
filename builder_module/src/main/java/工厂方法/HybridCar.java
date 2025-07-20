package 工厂方法;

// 具体产品 - 混合动力汽车
public class HybridCar implements Car {
    @Override
    public void drive() {
        System.out.println("驾驶混合动力汽车...");
    }
}
