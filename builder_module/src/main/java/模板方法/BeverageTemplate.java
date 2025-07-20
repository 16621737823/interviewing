package ģ�巽��;

public abstract class BeverageTemplate {

    // ģ�巽�����������Ʊ����ϵ��㷨�Ǽܣ�final��ֹ���า�ǣ�����������������ʵ��һϵ��ģ�岽��
    public final void prepareBeverage() {
        boilWater();
        brew();
        pourInCup();
        if (customerWantsCondiments()) {
            addCondiments();
        }
    }

    // �������� - ���ˮ��ͨ�ò��裩
    private void boilWater() {
        System.out.println("���ˮ");
    }

    // �������� - ���뱭�ӣ�ͨ�ò��裩
    private void pourInCup() {
        System.out.println("�����ϵ��뱭��");
    }

    // ���󷽷� - ���ݣ�������ʵ�֣�
    protected abstract void brew();

    // ���󷽷� - ��ӵ��ϣ�������ʵ�֣�
    protected abstract void addCondiments();

    // ���ӷ��� - �Ƿ���ӵ��ϣ�Ĭ��ʵ�֣�����ɸ��ǣ�
    protected boolean customerWantsCondiments() {
        return true;
    }
}
