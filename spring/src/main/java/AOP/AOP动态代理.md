# JDK proxy

前提条件：目标类实现了接口，就会使用JDK proxy进行动态代理，proxy是基于接口的动态代理

1.需要定一个实现了InvocationHandler接口的代理类，通过Proxy类的静态方法得到代理对象

![](https://gitee.com/believeLight/picture-bed/raw/master/img/image-20250321045543165.png)

MyInvocationHandler的实现

![image-20250321050152036](https://gitee.com/believeLight/picture-bed/raw/master/img/image-20250321050152036.png)

2.此时调用代理对象o的addUser方法，执行的就是我们传进去的代理类的invoke函数

![image-20250321050233187](https://gitee.com/believeLight/picture-bed/raw/master/img/image-20250321050233187.png)

# cglib

cglib是一个基于类的动态代理，可以代理任何没有实现接口的类

通过cglib库，可以直接在运行时创建目标代理对象的子类，通过重写父类的方法从而实现对于类的动态代理

cglib的原理是在运行时动态生成字节码，所以相比proxy虽然更加灵活但是会有一定的性能开销
