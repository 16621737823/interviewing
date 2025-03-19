/**
 * һ����threadLocal�洢һ������
 * ÿ���߳��ж�������һ��threadLocalMap��ThreadLocal.ThreadLocalMap threadLocals = null;
 * get��Դ��
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
 * set��Դ��
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
 *     ThreadLocal��ԭ��
 *     1.ÿ���߳��ڲ�����һ��ThreadLocalMap������һ��entry�����飬entry��key����ThreadLocal����
 *     2.ͨ��ThreadLocal��ȡ����ʱ����ʵ���Ǵ����map��ͨ����ǰ��ThreadLocal��Ϊkey����������
 *
 *     ThreadLocal���ڴ�й©���⣺
 *     1.ÿ���߳��ڲ�����һ��ThreadLocalMap��һ��ǿ���ã����Thread���������ڳ�����ômap�е�value�ͻ���й¶�Ŀ���
 *     2.ThreadLocalMap��key�����������õķ�ʽ�����һֱ������set��remove������value���޷������յ����ڴ�й©
 *     3.���������remove����set��������ôһ����⵽keyΪnull���ͻᴥ��value�Ļ��գ���Ҳ��Ϊʲôkey�����Ϊ�����ã���Ȼkey��value�ͻ�ͬʱ�����ڴ�й©
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


