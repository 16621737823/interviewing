package 用户日志维护在线人数;


import java.util.ArrayList;
import java.util.List;

//最大在线人数计算
public class OnlineUserAnalysis {
    public static int findMaxOnlineUsers(List<Log> logs) {
        // 创建事件列表：登录+1，登出-1
        List<int[]> events = new ArrayList<>();
        for (Log log : logs) {
            events.add(new int[]{log.loginTime, 1});   // 登录事件
            events.add(new int[]{log.logoutTime, -1}); // 登出事件
        }

        // 按时间排序，时间相同则登出排在前面
        events.sort((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        int maxUsers = 0;
        int currentUsers = 0;

        for (int[] event : events) {
            currentUsers += event[1];
            if (currentUsers > maxUsers) {
                maxUsers = currentUsers;
            }
        }

        return maxUsers;
    }

}
