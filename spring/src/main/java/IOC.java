import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 依赖注入流程：
 * 1.读取注解或者配置文件，将其转化为BeanDefinition放到BeanFactory中
 * 2.调用指定的构造方法实例化bean对象
 * 3.如果实现了Aware接口，执行aware接口中的方法
 * 4.执行BeanPostProcessor的BeforeInitialization方法
 * 5.如果实现了InitializingBean接口或者bean中有自定义的init方法，执行这个初始化的方法
 * 6.执行BeanPostProcessor的AfterInitialization方法
 */
@Configuration
public class IOC {
    @Bean
    public MyBean myBean() {
        return new MyBean();
    }

    @Bean
    public MyBeanPostProcessor myBeanPostProcessor() {
        return new MyBeanPostProcessor();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(IOC.class);
        MyBean bean = context.getBean(MyBean.class);
        bean.doSomething();
        context.close();
    }
}

class MyBean implements InitializingBean, DisposableBean, BeanNameAware, BeanFactoryAware {

    private String beanName;
    private BeanFactory beanFactory;

    public MyBean() {
        System.out.println("1. 实例化Bean");
    }

    public void setBeanName(String name) {
        this.beanName = name;
        System.out.println("4. 调用Aware接口: BeanNameAware");
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        System.out.println("4. 调用Aware接口: BeanFactoryAware");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("6. 初始化阶段: InitializingBean的afterPropertiesSet方法");
    }

    public void doSomething() {
        System.out.println("8. Bean初始化完成，可以使用");
    }

    public void destroy() throws Exception {
        System.out.println("销毁Bean: DisposableBean的destroy方法");
    }
}

class MyBeanPostProcessor implements BeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof MyBean) {
            System.out.println("5. BeanPostProcessor前置处理: postProcessBeforeInitialization");
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof MyBean) {
            System.out.println("7. BeanPostProcessor后置处理: postProcessAfterInitialization");
        }
        return bean;
    }
}
