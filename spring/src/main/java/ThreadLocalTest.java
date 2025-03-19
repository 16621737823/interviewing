/**
 * 一个人threadLocal存储一份数据
 * 每个线程中都定义了一个threadLocalMap：ThreadLocal.ThreadLocalMap threadLocals = null;
 * get的源码
 *     public T get() {
 *         Thread t = Thread.currentThread();
 *         ThreadLocalMap map = getMap(t);
 *         if (map != null) {
 *             ThreadLocalMap.Entry e = map.getEntry(this);
 *             if (e != null) {
 *                 @SuppressWarnings("unchecked")
 *                 T result = (T)e.value;
 *                 return result;
 *             }
 *         }
 *         return setInitialValue();
 *     }
 * set的源码
 *     public void set(T value) {
 *         Thread t = Thread.currentThread();
 *         ThreadLocalMap map = getMap(t);
 *         if (map != null) {
 *             map.set(this, value);
 *         } else {
 *             createMap(t, value);
 *         }
 *     }
 *
 *
 *     ThreadLocal的原理：
 *     1.每个线程内部都有一个ThreadLocalMap，他是一个entry的数组，entry的key就是ThreadLocal对象
 *     2.通过ThreadLocal存取数据时，其实就是从这个map中通过当前的ThreadLocal作为key来操作数据
 *
 *     ThreadLocal的内存泄漏问题：
 *     1.每个线程内部都有一个ThreadLocalMap是一个强引用，如果Thread的生命周期长，那么map中的value就会有泄露的可能
 *     2.ThreadLocalMap的key采用了弱引用的方式，如果一直不调用set，remove方法，value就无法被回收导致内存泄漏
 *     3.如果调用了remove或者set方法，那么一旦检测到key为null，就会触发value的回收，这也是为什么key被设计为弱引用，不然key和value就会同时发生内存泄漏
 */

public class ThreadLocalTest {
    public static final ThreadLocal<Integer> threadLocal1=new ThreadLocal<>();
    public static final ThreadLocal<Integer> threadLocal2=new ThreadLocal<>();

    public static void main(String[] args) {
        threadLocal1.set(1);
        threadLocal2.set(2);

        System.out.println(threadLocal1.get());
        System.out.println(threadLocal2.get());
    }
}


