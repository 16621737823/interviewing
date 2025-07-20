package 用户日志维护在线人数;

import java.util.*;

// 找到最大在线人数的持续时长
public class OnlineUserAnalysis2 {
    public static int findMaxDurationAtPeak(List<Log> logs) {
        // 创建事件列表
        List<int[]> events = new ArrayList<>();
        for (Log log : logs) {
            events.add(new int[]{log.loginTime, 1});
            events.add(new int[]{log.logoutTime, -1});
        }

        // 排序
        events.sort((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        // 第一次遍历找出最大在线人数
        int maxUsers = 0;
        int currentUsers = 0;
        for (int[] event : events) {
            currentUsers += event[1];
            if (currentUsers > maxUsers) {
                maxUsers = currentUsers;
            }
        }

        // 第二次遍历找出最长持续时间
        int maxDuration = 0;
        int currentStart = -1;
        currentUsers = 0;

        for (int[] event : events) {
            int prevUsers = currentUsers;
            currentUsers += event[1];

            if (prevUsers == maxUsers && currentUsers < maxUsers) {
                // 从峰值下降，计算持续时间
                int duration = event[0] - currentStart;
                if (duration > maxDuration) {
                    maxDuration = duration;
                }
                currentStart = -1;
            }

            if (currentUsers == maxUsers && prevUsers < maxUsers) {
                // 上升到峰值，记录开始时间
                currentStart = event[0];
            }
        }

        return maxDuration;
    }
}
