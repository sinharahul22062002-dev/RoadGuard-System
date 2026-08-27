package roadguard.service;

import roadguard.dao.UserDAO;
import roadguard.user;

public class UserService {

    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    public user findUserById(int userId) {
        return userDAO.findById(userId);
    }


    public user findUserByEmail(String email) {
        return userDAO.findByEmail(email);
    }


    public boolean deleteUser(int userId) {
        return userDAO.deleteUser(userId);
    }


    public boolean updateUserName(int userId, String newName) {

        if (newName == null || newName.trim().isEmpty()) {
            return false;
        }

        user existingUser = userDAO.findById(userId);

        if (existingUser == null) {
            return false;
        }

        return userDAO.updateName(
                userId,
                newName.trim()
        );
    }


    public boolean updateUserEmail(int userId, String newEmail) {

        if (newEmail == null || newEmail.trim().isEmpty()) {
            return false;
        }

        user existingUser = userDAO.findById(userId);

        if (existingUser == null) {
            return false;
        }

        user emailUser =
                userDAO.findByEmail(newEmail.trim());

        if (emailUser != null &&
                emailUser.getUserId() != userId) {

            return false;
        }

        return userDAO.updateEmail(
                userId,
                newEmail.trim()
        );
    }
}