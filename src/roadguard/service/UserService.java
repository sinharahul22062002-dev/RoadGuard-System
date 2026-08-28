package roadguard.service;

import roadguard.dao.UserDAO;
import roadguard.user;

public class UserService {

    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    // Find user by ID
    public user findUserById(int userId) {

        try {

            if (userId <= 0) {
                throw new IllegalArgumentException(
                        "User ID must be greater than 0."
                );
            }

            return userDAO.findById(userId);

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Invalid user ID: " + e.getMessage()
            );

            return null;
        }
    }


    // Find user by Email
    public user findUserByEmail(String email) {

        try {

            if (email == null || email.trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "Email cannot be empty."
                );
            }

            email = email.trim();

            if (!email.contains("@")) {
                throw new IllegalArgumentException(
                        "Invalid email address."
                );
            }

            return userDAO.findByEmail(email);

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Invalid email: " + e.getMessage()
            );

            return null;
        }
    }


    // Delete user
    public boolean deleteUser(int userId) {

        try {

            if (userId <= 0) {
                throw new IllegalArgumentException(
                        "User ID must be greater than 0."
                );
            }

            user existingUser =
                    userDAO.findById(userId);

            if (existingUser == null) {
                return false;
            }

            return userDAO.deleteUser(userId);

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Invalid user ID: " + e.getMessage()
            );

            return false;
        }
    }


    // Update user's name
    public boolean updateUserName(
            int userId,
            String newName) {

        try {

            if (userId <= 0) {
                throw new IllegalArgumentException(
                        "User ID must be greater than 0."
                );
            }

            if (newName == null ||
                    newName.trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "Name cannot be empty."
                );
            }

            newName = newName.trim();

            user existingUser =
                    userDAO.findById(userId);

            if (existingUser == null) {
                return false;
            }

            return userDAO.updateName(
                    userId,
                    newName
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Invalid name: " + e.getMessage()
            );

            return false;
        }
    }


    // Update user's email
    public boolean updateUserEmail(
            int userId,
            String newEmail) {

        try {

            if (userId <= 0) {
                throw new IllegalArgumentException(
                        "User ID must be greater than 0."
                );
            }

            if (newEmail == null ||
                    newEmail.trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "Email cannot be empty."
                );
            }

            newEmail = newEmail.trim();

            if (!newEmail.contains("@")) {

                throw new IllegalArgumentException(
                        "Invalid email address."
                );
            }

            user existingUser =
                    userDAO.findById(userId);

            if (existingUser == null) {
                return false;
            }

            user emailUser =
                    userDAO.findByEmail(newEmail);

            if (emailUser != null &&
                    emailUser.getUserId() != userId) {

                System.out.println(
                        "This email is already registered."
                );

                return false;
            }

            return userDAO.updateEmail(
                    userId,
                    newEmail
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Invalid input: " + e.getMessage()
            );

            return false;
        }
    }
}
