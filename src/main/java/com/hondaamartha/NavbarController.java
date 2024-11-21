package com.hondaamartha;

import static com.hondaamartha.LoginController.logout;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;



public class NavbarController {
    @FXML
    private Button manageTestDriveButton;
    
    @FXML
    private void homeButton() {
        try {
            App.setRoot("home");
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to load the home page");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void productButton(){
        try {
            App.setRoot("cars");
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to load the products page");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void logoutButton(){
        try {
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Konfirmasi");
            alert.setHeaderText("Apakah anda yakin untuk keluar?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                logout();
            }    
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to load login page");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}