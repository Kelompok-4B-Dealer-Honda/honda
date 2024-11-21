package com.hondaamartha.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.hondaamartha.Database;

public class Member extends User {

    public Member(String idUser, String username, String password) {
        super(idUser, username, password);
    }

    public void insertMember() {
        super.insert(); 

        String sql = "INSERT INTO member (id_user) VALUES (?)";

        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, this.idUser);

            pstmt.executeUpdate();
            System.out.println("Member added successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to insert member: " + e.getMessage());
        }
    }
}
