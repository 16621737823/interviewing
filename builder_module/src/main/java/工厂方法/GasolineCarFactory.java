package ��������;

// ���幤�� - ȼ����������
public class GasolineCarFactory implements CarFactory {
    @Override
    public Car createCar() {
        return new GasolineCar();
    }
}
