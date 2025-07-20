package 工厂方法;

// 具体工厂 - 混合动力汽车工厂
public class HybridCarFactory implements CarFactory {
    @Override
    public Car createCar() {
        return new HybridCar();
    }
}