package AOP;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

// 这是切面
@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* AOP.UserService.addUser(..))")
    public void beforeAddUser() {
        System.out.println("准备添加用户...");
    }
}