package ��������;

// ���幤�� - ��϶�����������
public class HybridCarFactory implements CarFactory {
    @Override
    public Car createCar() {
        return new HybridCar();
    }
}