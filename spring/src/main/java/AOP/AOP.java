package AOP;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Proxy;

public class AOP {
    public static void main(String[] args) {
        /**
         * classLoader:�����������ص�jvm�����
         * interfaces��Ҫ����Ľӿڼ���
         * invocationHandler�������������ö�̬����Ķ���ʱ���ߵ����handler�е�invoke��
         */
        UserService o = (UserService) Proxy.newProxyInstance(AOP.class.getClassLoader(),
                new Class[]{UserService.class},
                new MyInvocationHandler());

        o.addUser("12");

//        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        UserService userService = context.getBean(UserService.class);
//        userService.addUser("Alice");
    }
}
