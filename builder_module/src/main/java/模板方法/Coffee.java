package 模板方法;

import java.util.Scanner;

public class Coffee extends BeverageTemplate {
    @Override
    protected void brew() {
        System.out.println("冲泡咖啡粉");
    }

    @Override
    protected void addCondiments() {
        System.out.println("加入糖和牛奶");
    }

    // 覆盖钩子方法，自定义是否需要调料
    @Override
    protected boolean customerWantsCondiments() {
        String answer = getUserInput();
        return answer.toLowerCase().startsWith("y");
    }

    private String getUserInput() {
        System.out.print("您想要在咖啡中加入糖和牛奶吗 (y/n)? ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
