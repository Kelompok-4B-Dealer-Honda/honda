package com.hondaamartha.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.hondaamartha.Database;

public class Admin extends User {
    private String email;
    private String role;

    public Admin(String idUser, String username, String password, String email, String role) {
        super(idUser, username, password);
        this.email = email;
        this.role = role;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public void insertAdmin() {
        super.insert();

        String sql = "INSERT INTO admin (id_user, email, role) VALUES (?, ?, ?)";

        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, this.idUser);
            pstmt.setString(2, this.email);
            pstmt.setString(3, this.role);

            pstmt.executeUpdate();
            System.out.println("Admin added successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to insert admin: " + e.getMessage());
        }
    }

    public void updateAdmin() {
        String sql = "UPDATE admin SET email = ?, role = ? WHERE id_user = ?";

        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, this.email);
            pstmt.setString(2, this.role);
            pstmt.setString(3, this.idUser);

            pstmt.executeUpdate();
            System.out.println("Admin updated successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to update admin: " + e.getMessage());
        }
    }

    public void deleteAdmin() {
        String sql = "DELETE FROM admin WHERE id_user = ?";

        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, this.idUser);

            pstmt.executeUpdate();
            System.out.println("Admin deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to delete admin: " + e.getMessage());
        }
    }
}
