package ��������;

// ���幤�� - �綯��������
public class ElectricCarFactory implements CarFactory {
    @Override
    public Car createCar() {
        return new ElectricCar();
    }
}