package roadguard.service;

import roadguard.dao.UserDAO;
import roadguard.user;

public class AuthService {

    private UserDAO userDAO;

    public AuthService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    // Register User
    public boolean registerUser(
            String name,
            String email,
            String password,
            String role) {

        try {

            // Name validation
            if (name == null || name.trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "Name cannot be empty."
                );
            }


            // Email validation
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


            // Password validation
            if (password == null || password.isEmpty()) {

                throw new IllegalArgumentException(
                        "Password cannot be empty."
                );
            }


            // Role validation
            if (role == null || role.trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "Role cannot be empty."
                );
            }

            role = role.trim().toUpperCase();

            if (!role.equals("CITIZEN")
                    && !role.equals("AUTHORITY")
                    && !role.equals("ADMIN")) {

                throw new IllegalArgumentException(
                        "Invalid role."
                );
            }


            // Check duplicate email
            if (userDAO.findByEmail(email) != null) {

                System.out.println(
                        "Email is already registered."
                );

                return false;
            }


            // Create user object
            user newUser =
                    new user(
                            name.trim(),
                            email,
                            password,
                            role
                    );


            // Save user in database
            return userDAO.addUser(newUser);


        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Registration failed: "
                    + e.getMessage()
            );

            return false;
        }
    }


    // Login User
    public user login(
            String email,
            String password) {

        try {

            // Email validation
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


            // Password validation
            if (password == null || password.isEmpty()) {

                throw new IllegalArgumentException(
                        "Password cannot be empty."
                );
            }


            // Find user
            user existingUser =
                    userDAO.findByEmail(email);


            if (existingUser == null) {

                return null;
            }


            // Check password
            if (existingUser.getPasswordHash()
                    .equals(password)) {

                return existingUser;
            }


            return null;


        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Login failed: "
                    + e.getMessage()
            );

            return null;
        }
    }
}