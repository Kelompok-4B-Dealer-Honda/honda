package com.hondaamartha.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.hondaamartha.Database;

public class User {
    protected String idUser;
    protected String username;
    protected String password;

    public User(String idUser, String username, String password) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
    }

    public String getIdUser() { return idUser; }
    public void setIdUser(String idUser) { this.idUser = idUser; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() {
        return "member";
    }

    public void insert() {
        String sql = "INSERT INTO user (id_user, username, password) VALUES (?, ?, ?)";

        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, this.idUser);
            pstmt.setString(2, this.username);
            pstmt.setString(3, this.password);

            pstmt.executeUpdate();
            System.out.println("User added successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to insert user: " + e.getMessage());
        }
    }
}
