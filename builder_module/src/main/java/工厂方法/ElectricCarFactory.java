package 工厂方法;

// 具体工厂 - 电动汽车工厂
public class ElectricCarFactory implements CarFactory {
    @Override
    public Car createCar() {
        return new ElectricCar();
    }
}