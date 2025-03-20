package AOP;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public void addUser(String username) {
        System.out.println("ÃÌº””√ªß: " + username);
    }
}