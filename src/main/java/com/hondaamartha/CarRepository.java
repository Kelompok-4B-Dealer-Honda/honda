package com.hondaamartha;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hondaamartha.model.Mobil;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CarRepository {
    // misah per model
    public List<Mobil> getUniqueCarModels() {
        List<Mobil> carList = new ArrayList<>();
        String sql = "SELECT nama_model, jenis_mobil, MIN(harga) AS harga, type, fitur FROM mobil GROUP BY nama_model ORDER BY harga ASC";

        try (Connection conn = Database.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Mobil car = new Mobil(
                    // yang null gak kepake di list model
                    null,
                    rs.getString("nama_model"),
                    rs.getString("jenis_mobil"), 
                    null, 
                    rs.getDouble("harga"),
                    null, 
                    rs.getString("type"),
                    rs.getString("fitur")
                );
                carList.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carList;
    }

    // misah per tipe dari model
    public List<Mobil> getCarTypesByModel(String modelName) {
        List<Mobil> carList = new ArrayList<>();
        String sql = "SELECT * FROM mobil WHERE nama_model = '" + modelName + "' ORDER BY harga ASC";
    
        try (Connection conn = Database.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
    
            while (rs.next()) {
                Mobil car = new Mobil(
                    rs.getString("id_mobil"),
                    rs.getString("nama_model"),
                    rs.getString("jenis_mobil"),
                    rs.getString("transmisi"),
                    rs.getDouble("harga"),
                    rs.getString("id_user"),
                    rs.getString("type"),
                    rs.getString("fitur")
                );
                carList.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carList;
    }

    public boolean isModelNameExists(String modelName) {
        String sql = "SELECT COUNT(*) AS count FROM mobil WHERE nama_model = ?";
        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, modelName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next() && rs.getInt("count") > 0) {
                    return true; 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateCarModelName(String oldModelName, String newModelName) {
        if (isModelNameExists(newModelName)) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Model Name Conflict");
            alert.setContentText("Nama model udah dipakai. Pilih yang lain");
            alert.showAndWait();
            return;
        }

        String sql = "UPDATE mobil SET nama_model = ? WHERE nama_model = ?";
        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newModelName);
            stmt.setString(2, oldModelName);

            int rowsUpdated = stmt.executeUpdate();
            System.out.println(rowsUpdated + " rows updated with the new model name.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getCarColors(String modelName) {
        List<String> colors = new ArrayList<>();
        String sql = "SELECT DISTINCT warna FROM mobil WHERE nama_model = ?";
    
        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, modelName);
    
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    colors.add(rs.getString("warna"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return colors;
    }
    

    public void deleteCarModel(String modelName) {
        String sql = "DELETE FROM mobil WHERE nama_model = ?";
        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, modelName);
    
            int rowsDeleted = stmt.executeUpdate();
            System.out.println(rowsDeleted + " rows deleted for model " + modelName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean updateCarType(String carId, String carType, String transmission, String category, String color, double price, String features) {
        String sql = "UPDATE mobil SET jenis_mobil = ?, transmisi = ?, type = ?, warna = ?, harga = ?, fitur = ? WHERE id_mobil = ?";
    
        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setString(1, category);
            stmt.setString(2, transmission);
            stmt.setString(3, carType);
            stmt.setString(4, color);
            stmt.setDouble(5, price);
            stmt.setString(6, features);
            stmt.setString(7, carId);
            
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCarType(String carId) {
        String sql = "DELETE FROM mobil WHERE id_mobil = ?";
    
        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setString(1, carId);
    
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0; 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
