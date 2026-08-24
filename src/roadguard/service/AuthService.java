package roadguard.service;

import roadguard.dao.UserDAO;
import roadguard.user;

public class AuthService {
    private UserDAO userDAO;

    public AuthService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean registerUser(String name, String email, String password, String role) {
        if (name == null || name.trim().isEmpty()) return false;
        if (email == null || email.trim().isEmpty()) return false;
        if (password == null || password.trim().isEmpty()) return false;
        if (!role.equals("CITIZEN") && !role.equals("AUTHORITY") && !role.equals("ADMIN")) return false;
        if (userDAO.findByEmail(email) != null) return false;

        user newUser = new user(name, email, password, role);
        userDAO.addUser(newUser);
        return true;
    }

    public user login(String email, String password) {
        user existingUser = userDAO.findByEmail(email);
        if (existingUser == null) return null;
        if (existingUser.getPasswordHash().equals(password)) return existingUser;
        return null;
    }
}
