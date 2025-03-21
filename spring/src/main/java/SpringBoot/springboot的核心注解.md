1.@SpringBootConfiguration：这是第一个核心注解，用来标记一个springboot的应用，点进去之后可以发现下面的注解

![image-20250321085515455](https://gitee.com/believeLight/picture-bed/raw/master/img/image-20250321085515455.png)

2.首先SpringBootConfiguration点进去发现包含了Configuration注解，作用是标记为配置类

3.接下来是EnableAutoConfiguration注解是用来启动自动配置类的，他会加载spring.factories中注册的各种AutoConfiguration，接下来关心配置类

![image-20250321085916805](https://gitee.com/believeLight/picture-bed/raw/master/img/image-20250321085916805.png)

随便点进配置类，发现了很多Condititional注解，这个就是用来标识这个自动配置的加载条件





这些就是springboot的核心注解，至于其他的@Bean,@Configuration之类的，都是spring3中就已经有的