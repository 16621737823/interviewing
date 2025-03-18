//实现一个带过期时间的LRU缓存

import java.util.*;

class LRUCache {
    class LinkedNode {
        int key, value;
        long expireAt; // 过期时间（毫秒级时间戳）
        LinkedNode prev, next;

        public LinkedNode(int key, int value, long expireAt) {
            this.key = key;
            this.value = value;
            this.expireAt = expireAt;
        }
    }

    private final int capacity;
    private final long ttl; // 过期时间（毫秒）
    private int size;
    final Map<Integer, LinkedNode> cache;
    private final LinkedNode head, tail;

    public LRUCache(int capacity, long ttl) {
        this.capacity = capacity;
        this.ttl = ttl;
        this.size = 0;
        this.cache = new HashMap<>();
        this.head = new LinkedNode(-1, -1, 0);
        this.tail = new LinkedNode(-1, -1, 0);
        head.next = tail;
        tail.prev = head;

        Thread thread=new Thread(()->{
            while (true){
                try {
                    removeExpiredNodes();
                    Thread.sleep(2*ttl);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread.start();
    }

    public int get(int key) {
        if (!cache.containsKey(key)) return -1;

        LinkedNode node = cache.get(key);
        if (System.currentTimeMillis() > node.expireAt) {
            removeNode(node);
            cache.remove(key);
            size--;
            return -1;
        }

        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        long expireAt = System.currentTimeMillis() + ttl;

        if (cache.containsKey(key)) {
            LinkedNode node = cache.get(key);
            node.value = value;
            node.expireAt = expireAt;
            moveToHead(node);
        } else {
            LinkedNode newNode = new LinkedNode(key, value, expireAt);
            cache.put(key, newNode);
            addToHead(newNode);
            size++;

            if (size > capacity) {
                removeFromTail();
                size--;
            }
        }
    }

    private void moveToHead(LinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    private void removeNode(LinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToHead(LinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeFromTail() {
        LinkedNode target = tail.prev;
        removeNode(target);
        cache.remove(target.key);
    }

    private void removeExpiredNodes() {
        long curTime=System.currentTimeMillis();
        List<Integer> keySet=new ArrayList<>();
        //不能在这里直接删除for-each 循环的 Map.entrySet() 其实是用 Iterator 遍历的，HashMap内部维护了一个modCount
        //每次增删改都会使modCount++，在遍历时Iterator会检查modCount，如果modCount被修改了，但是Iterator本身没有调用增删改，就会报错
        for(Map.Entry<Integer, LinkedNode> entry : cache.entrySet()){
            if(entry.getValue().expireAt<=curTime){
                size--;
                keySet.add(entry.getKey());
            }
        }

        for(int key:keySet){
            cache.remove(key);
        }
    }
}
