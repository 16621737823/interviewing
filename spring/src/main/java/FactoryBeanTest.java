import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * ����FactoryBean
 * 1.BeanFactory �� Spring �����Ļ����ӿڣ��������ͻ�ȡ Spring �����е� Bean
 * 2.FactoryBean��һ�������Bean��ͨ��FactoryBean������spring���Զ������Ĵ����߼������һ������ʵ����
 * FactoryBean��ע�ᵽIOC֮�󣬵���getBean�õ��ľ���FactoryBean��getObject�������صĽ��
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
        User user = context.getBean("userFactoryBean", User.class); // ��ȡ User ����
        System.out.println(user);
    }
}
