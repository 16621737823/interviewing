package 模板方法;

public abstract class BeverageTemplate {

    // 模板方法，定义了制备饮料的算法骨架（final防止子类覆盖），子类调用这个方法实现一系列模板步骤
    public final void prepareBeverage() {
        boilWater();
        brew();
        pourInCup();
        if (customerWantsCondiments()) {
            addCondiments();
        }
    }

    // 基本方法 - 煮沸水（通用步骤）
    private void boilWater() {
        System.out.println("煮沸水");
    }

    // 基本方法 - 倒入杯子（通用步骤）
    private void pourInCup() {
        System.out.println("将饮料倒入杯子");
    }

    // 抽象方法 - 冲泡（由子类实现）
    protected abstract void brew();

    // 抽象方法 - 添加调料（由子类实现）
    protected abstract void addCondiments();

    // 钩子方法 - 是否添加调料（默认实现，子类可覆盖）
    protected boolean customerWantsCondiments() {
        return true;
    }
}
