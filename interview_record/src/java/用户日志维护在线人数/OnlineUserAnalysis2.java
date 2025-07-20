package �û���־ά����������;

import java.util.*;

// �ҵ�������������ĳ���ʱ��
public class OnlineUserAnalysis2 {
    public static int findMaxDurationAtPeak(List<Log> logs) {
        // �����¼��б�
        List<int[]> events = new ArrayList<>();
        for (Log log : logs) {
            events.add(new int[]{log.loginTime, 1});
            events.add(new int[]{log.logoutTime, -1});
        }

        // ����
        events.sort((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        // ��һ�α����ҳ������������
        int maxUsers = 0;
        int currentUsers = 0;
        for (int[] event : events) {
            currentUsers += event[1];
            if (currentUsers > maxUsers) {
                maxUsers = currentUsers;
            }
        }

        // �ڶ��α����ҳ������ʱ��
        int maxDuration = 0;
        int currentStart = -1;
        currentUsers = 0;

        for (int[] event : events) {
            int prevUsers = currentUsers;
            currentUsers += event[1];

            if (prevUsers == maxUsers && currentUsers < maxUsers) {
                // �ӷ�ֵ�½����������ʱ��
                int duration = event[0] - currentStart;
                if (duration > maxDuration) {
                    maxDuration = duration;
                }
                currentStart = -1;
            }

            if (currentUsers == maxUsers && prevUsers < maxUsers) {
                // ��������ֵ����¼��ʼʱ��
                currentStart = event[0];
            }
        }

        return maxDuration;
    }
}
