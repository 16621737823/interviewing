package AOP;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Proxy;

public class AOP {
    public static void main(String[] args) {
        /**
         * classLoader:将代理对象加载到jvm虚拟机
         * interfaces：要代理的接口集和
         * invocationHandler：拦截器，调用动态代理的对象时，走到这个handler中的invoke中
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
