package 工厂方法;

// 具体工厂 - 燃油汽车工厂
public class GasolineCarFactory implements CarFactory {
    @Override
    public Car createCar() {
        return new GasolineCar();
    }
}
