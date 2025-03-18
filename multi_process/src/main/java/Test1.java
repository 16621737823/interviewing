/**
 * 两个线程轮流打印1-100
 */
public class Test1 {
    private static int curNum=1;
    private static Object lock=new Object();
    public static void main(String[] args) {
        Thread t1=new Thread(()->printNumbers(true));
        t1.start();

        Thread t2=new Thread(()->printNumbers(false));
        t2.start();
    }

    private static void printNumbers(boolean isOdd) {
        while(curNum<=100){
            synchronized (lock){
                if ((isOdd && curNum%2==0) || (!isOdd && curNum%2!=0)){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                if (curNum<=100){
                    System.out.println("Thread"+(isOdd?" odd ":" even ")+curNum);
                    curNum++;
                    lock.notify();
                }
            }
        }
    }
}
