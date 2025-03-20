package AOP;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

// ��������
@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* AOP.UserService.addUser(..))")
    public void beforeAddUser() {
        System.out.println("׼������û�...");
    }
}