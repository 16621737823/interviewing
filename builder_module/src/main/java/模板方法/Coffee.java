package ģ�巽��;

import java.util.Scanner;

public class Coffee extends BeverageTemplate {
    @Override
    protected void brew() {
        System.out.println("���ݿ��ȷ�");
    }

    @Override
    protected void addCondiments() {
        System.out.println("�����Ǻ�ţ��");
    }

    // ���ǹ��ӷ������Զ����Ƿ���Ҫ����
    @Override
    protected boolean customerWantsCondiments() {
        String answer = getUserInput();
        return answer.toLowerCase().startsWith("y");
    }

    private String getUserInput() {
        System.out.print("����Ҫ�ڿ����м����Ǻ�ţ���� (y/n)? ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
