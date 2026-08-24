package roadguard.dao;

import roadguard.user;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private List<user> users = new ArrayList<>();

    public void addUser(user u) {
        users.add(u);
    }

    public user findByEmail(String email) {
        for (user u : users) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return u;
            }
        }
        return null;
    }

    public user findById(int id) {
        for (user u : users) {
            if (u.getUserId() == id) {
                return u;
            }
        }
        return null;
    }

    public List<user> getAllUsers() {
        return users;
    }

    public boolean deleteUser(int id) {
        user u = findById(id);
        if (u != null) {
            users.remove(u);
            return true;
        }
        return false;
    }
}
