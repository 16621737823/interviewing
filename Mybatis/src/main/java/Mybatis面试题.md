# 1.Mybatis的工作原理

1.读取mybatis-config.xml配置文件，配置运行环境连接信息等

2.加载Sql映射文件

3.构建会话工厂：SqlSessionFactory（解析 XML 配置和 Mapper 接口，构建 `SqlSession`）

4.通过会话工厂创建SqlSession对象（提供 `getMapper()` 方法获取代理对象，用于执行sql）

5.Mybatis底层定义了一个执行器executor接口来操作数据库，他会根据传递的参数动态生成sql语句并维护缓存

6.executor接口中有一个MappedStatement参数，可以处理输入或者输出的映射关系

![image-20250326003602206](https://gitee.com/believeLight/picture-bed/raw/master/img/image-20250326003602206.png)

# 2.#{}和${}

#{}使用预编译的方式会生成?占位符，参数值在执行时才被传入，可以有效防止sql注入，而${}就是单纯的字符串拼接，#{}也会自动处理特殊符号，就拿用户输入admin' OR '1'='1为例

使用${}

```sql
-- 拼接后成为有效SQL代码
SELECT * FROM users WHERE username = 'admin' OR '1'='1'
-- 这将返回所有用户数据
```

使用#{}

```sql
-- 预编译会将整个输入视为一个字符串值
SELECT * FROM users WHERE username = 'admin'' OR ''1''=''1'
-- 只是查找用户名为"admin' OR '1'='1"的用户，不会改变SQL语义
```

# 3.为什么mybatis的接口没有写实现类但是能跑下去

mybatis使用了JDK的动态代理或者cglib在运行时动态生成mapper接口的代理对象，调用sqlSession的getMapper方法时，mybatis就会返回一个代理对象，这个代理对象执行的真正的sql逻辑	
