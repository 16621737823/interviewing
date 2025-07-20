package �û���־ά����������;


import java.util.ArrayList;
import java.util.List;

//���������������
public class OnlineUserAnalysis {
    public static int findMaxOnlineUsers(List<Log> logs) {
        // �����¼��б���¼+1���ǳ�-1
        List<int[]> events = new ArrayList<>();
        for (Log log : logs) {
            events.add(new int[]{log.loginTime, 1});   // ��¼�¼�
            events.add(new int[]{log.logoutTime, -1}); // �ǳ��¼�
        }

        // ��ʱ������ʱ����ͬ��ǳ�����ǰ��
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
