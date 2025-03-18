import java.util.*;

/**
 * 1.定义资源和锁对象
 * 2.构建等待图key为某个线程正在等待的资源，value为等待这个资源的所有线程持有的资源
 * 3.dfs遍历
 */

public class DeadlockDetector {

    // 锁对象
    static class Resource {
        String name;
        public Resource(String name) {
            this.name = name;
        }
    }

    // 线程信息
    static class ThreadInfo {
        String name;
        Resource holding; // 当前持有的锁
        Resource waiting; // 正在等待的锁

        public ThreadInfo(String name) {
            this.name = name;
        }
    }

    // 全局线程列表
    static List<ThreadInfo> threads = new ArrayList<>();

    // 检测死锁
    public static boolean detectDeadlock() {
        // 构建等待图(正在等待某个资源的线程，持有的资源列表)
        Map<Resource, Set<Resource>> waitForGraph = new HashMap<>();

        for (ThreadInfo thread : threads) {
            if (thread.waiting != null) {
                // 如果线程正在等待某个锁，则将该锁的持有者加入等待图
                if (!waitForGraph.containsKey(thread.waiting)){
                    waitForGraph.put(thread.waiting,new HashSet<>());
                }
                waitForGraph.get(thread.waiting).add(thread.holding);
            }
        }

        // 检测图中是否存在环
        for (Resource resource : waitForGraph.keySet()) {
            Set<Resource> visited = new HashSet<>();
            if (hasCycle(waitForGraph, resource, visited)) {
                return true; // 存在环，说明有死锁
            }
        }

        return false; // 没有环，说明没有死锁
    }

    // 深度优先搜索（DFS）检测环
    private static boolean hasCycle(Map<Resource, Set<Resource>> graph, Resource current, Set<Resource> visited) {
        if (visited.contains(current)) {
            return true; // 发现环
        }

        visited.add(current);

        if (graph.containsKey(current)) {
            for (Resource neighbor : graph.get(current)) {
                if (hasCycle(graph, neighbor, visited)) {
                    return true;
                }
            }
        }

        visited.remove(current);
        return false;
    }

    public static void main(String[] args) {
        // 创建两个锁
        Resource lock1 = new Resource("Lock1");
        Resource lock2 = new Resource("Lock2");

        // 创建两个线程
        ThreadInfo thread1 = new ThreadInfo("Thread1");
        ThreadInfo thread2 = new ThreadInfo("Thread2");

        // 模拟死锁场景
        thread1.holding = lock1;
        thread1.waiting = lock2;

        thread2.holding = lock2;
        thread2.waiting = lock1;

        // 将线程加入全局列表
        threads.add(thread1);
        threads.add(thread2);

        // 检测死锁
        if (detectDeadlock()) {
            System.out.println("Deadlock detected!");
        } else {
            System.out.println("No deadlock detected.");
        }
    }
}