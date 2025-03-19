import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 测试FactoryBean
 * 1.BeanFactory 是 Spring 容器的基础接口，负责管理和获取 Spring 容器中的 Bean
 * 2.FactoryBean是一个特殊的Bean，通过FactoryBean可以在spring中自定义对象的创建逻辑，如果一个对象实现了
 * FactoryBean，注册到IOC之后，调用getBean得到的就是FactoryBean的getObject方法返回的结果
 */
public class FactoryBeanTest {
    @Bean
    public UserFactoryBean userFactoryBean(){
        UserFactoryBean userFactoryBean=new UserFactoryBean();
        userFactoryBean.setName("John Doe");
        userFactoryBean.setAge(30);
        return userFactoryBean;
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(FactoryBeanTest.class);
        User user = context.getBean("userFactoryBean", User.class); // 获取 User 对象
        System.out.println(user);
    }
}
