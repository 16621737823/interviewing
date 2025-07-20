package 用户日志维护在线人数;

public class Log {
    int userId;
    int loginTime;
    int logoutTime;

    public Log(int userId, int loginTime, int logoutTime) {
        this.userId = userId;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
    }
}
