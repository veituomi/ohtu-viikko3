package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.UserDao;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (!valid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean valid(String username, String password) {
        return validUsername(username) && validPassword(password);
    }
    
    private boolean validUsername(String username) {
        return username.matches("[a-z]{3,}");
    }
    
    private boolean validPassword(String password) {
        return password.matches("^(?=.*[0-9@#$%^&+=]).{8,}$");
    }
}
