public class Main {
    public static void main(String[] args) throws InterruptedException {
        LRUCache cache = new LRUCache(2, 5000); // 容量 2，过期时间 5 秒

        cache.put(1, 10);
        cache.put(2, 20);

        System.out.println(cache.cache.size());

        System.out.println(cache.get(1)); // 10（未过期）

        Thread.sleep(11000); // 等待 6 秒

//        System.out.println(cache.get(1)); // -1（已过期）
//        System.out.println(cache.get(2)); // -1（已过期）

        System.out.println(cache.cache.size());
    }
}
