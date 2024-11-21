package com.hondaamartha;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hondaamartha.model.Admin;
import com.hondaamartha.model.Member;
import com.hondaamartha.model.User;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    private Button loginButton;

    @FXML
    private Button manageTestDriveButton;

    private static User currentUser;

    @FXML
    private void loginButton() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (authenticate(username, password)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Login berhasil keknya");
            alert.show();
            App.setRoot("home");
        } else {
            messageLabel.setText("Username atau password salah");
        }
    }

    private boolean authenticate(String username, String password) {
        String userQuery = "SELECT * FROM user WHERE username = ? AND password = ?";
        String adminQuery = "SELECT * FROM admin WHERE id_user = ?";
        String memberQuery = "SELECT * FROM member WHERE id_user = ?";

        try (Connection conn = Database.connect();
             PreparedStatement userStmt = conn.prepareStatement(userQuery)) {

            // ngecek usernya ada di tabel user atau nggak ada
            userStmt.setString(1, username);
            userStmt.setString(2, password);
            ResultSet userRs = userStmt.executeQuery();

            if (userRs.next()) {
                String idUser = userRs.getString("id_user");
                String role = "member";

                // admin kah?
                try (PreparedStatement adminStmt = conn.prepareStatement(adminQuery)) {
                    adminStmt.setString(1, idUser);
                    ResultSet adminRs = adminStmt.executeQuery();
                    if (adminRs.next()) {
                        role = "admin";
                        currentUser = new Admin(idUser, username, password, adminRs.getString("email"), adminRs.getString("role"));
                    }
                }

                // member kah?
                if (role.equals("member")) {
                    try (PreparedStatement memberStmt = conn.prepareStatement(memberQuery)) {
                        memberStmt.setString(1, idUser);
                        ResultSet memberRs = memberStmt.executeQuery();
                        if (memberRs.next()) {
                            currentUser = new Member(idUser, username, password);
                        }
                    }
                }

                return true;
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        return false;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static String getUserRole() {
        return getCurrentUser().getRole();
    }

    public static void logout() throws IOException{
        App.setRoot("login");
        currentUser = null;
    }
}
