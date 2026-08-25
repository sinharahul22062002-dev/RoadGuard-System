package roadguard;

import java.time.LocalDateTime;

public class user {
    private int userId;
    private String name;
    private String email;
    private String passwordHash;
    private String role;
    private LocalDateTime createdAt;

    
    public user(String name, String email, String passwordHash, String role) {
        setName(name);
        setEmail(email);
        setPasswordHash(passwordHash);
        setRole(role);
    }

  
    public user(int userId, String name, String email,
                String passwordHash, String role,
                LocalDateTime createdAt) {
        this.userId = userId;
        setName(name);
        setEmail(email);
        setPasswordHash(passwordHash);
        setRole(role);
        this.createdAt = createdAt;
    }

   
    public int getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public String getRole() { return role; }
    public LocalDateTime getCreatedAt() { return createdAt; }

  
    public void setUserId(int userId) { this.userId = userId; }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid Email address");
        }
        this.email = email;
    }

    public void setPasswordHash(String passwordHash) {
        if (passwordHash == null || passwordHash.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        this.passwordHash = passwordHash;
    }

    public void setRole(String role) {
        if (role.equals("CITIZEN") || role.equals("AUTHORITY") || role.equals("ADMIN")) {
            this.role = role;
        } else {
            throw new IllegalArgumentException("Invalid role: " + role);
        }
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

  
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
