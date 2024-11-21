package com.hondaamartha.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.hondaamartha.Database;
import com.hondaamartha.car.Category;
import com.hondaamartha.car.Hatchback;
import com.hondaamartha.car.SUV;
import com.hondaamartha.car.Sedan;

// jenismobil = category string. Agak bikin bingung soalnya type itu artinya ya jenis
// (malas ganti namanya di db soalnya sudah jauh bah)
public class Mobil {
    private String idMobil;
    private String namaModel;
    private String jenisMobil;
    private String transmisi;
    private double harga;
    private String userId;
    private String type;
    private String fitur;

    private Category category;

    public Mobil(String idMobil, String namaModel, String jenisMobil, String transmisi,
                 double harga, String userId, String type, String fitur) {
        this.idMobil = idMobil;
        this.namaModel = namaModel;
        this.jenisMobil = jenisMobil;
        this.transmisi = transmisi;
        this.harga = harga;
        this.userId = userId;
        this.type = type;
        this.fitur = fitur;

        // map jenismobil (kategori) ke implementasi category
        switch (jenisMobil.toLowerCase()) {
            case "suv":
                this.category = new SUV();
                break;
            case "sedan":
                this.category = new Sedan();
                break;
            case "hatchback":
                this.category = new Hatchback();
                break;
            default:
                throw new IllegalArgumentException("Unknown category: " + jenisMobil);
        }
    }

    public String getIdMobil() { return idMobil; }
    public void setIdMobil(String idMobil) { this.idMobil = idMobil; }

    public String getNamaModel() { return namaModel; }
    public void setNamaModel(String namaModel) { this.namaModel = namaModel; }

    public String getJenisMobil() { return jenisMobil; }
    public void setJenisMobil(String jenisMobil) {
        this.jenisMobil = jenisMobil;
        // Update category
        switch (jenisMobil.toLowerCase()) {
            case "suv":
                this.category = new SUV();
                break;
            case "sedan":
                this.category = new Sedan();
                break;
            case "hatchback":
                this.category = new Hatchback();
                break;
            default:
                throw new IllegalArgumentException("Unknown category: " + jenisMobil);
        }
    }

    public String getTransmisi() { return transmisi; }
    public void setTransmisi(String transmisi) { this.transmisi = transmisi; }

    public double getHarga() { return harga; }
    public void setHarga(double harga) { this.harga = harga; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getType() { return type; }
    public void setType(String type){ this.type = type; }

    public String getFitur() { return fitur; }
    public void setFitur(String fitur) { this.fitur = fitur; }

    public double calculateFee(double kilometers) {
        return category.calculateFee(kilometers);
    }

    public Category getCategory() {
        return category;
    }
    

    public void save() {
        String sql = "INSERT INTO mobil (id_mobil, nama_model, jenis_mobil, transmisi, harga, id_user, type, fitur) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, this.idMobil);
            pstmt.setString(2, this.namaModel);
            pstmt.setString(3, this.jenisMobil);
            pstmt.setString(4, this.transmisi);
            pstmt.setDouble(5, this.harga);
            pstmt.setString(6, this.userId);
            pstmt.setString(7, this.type);
            pstmt.setString(8, this.fitur);

            pstmt.executeUpdate();
            System.out.println("Mobil added successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to insert mobil: " + e.getMessage());
        }
    }

    public void update() {
        String sql = "UPDATE mobil SET nama_model = ?, jenis_mobil = ?, transmisi = ?, harga = ?, " +
                     "id_user = ?, type = ?, fitur = ? WHERE id_mobil = ?";

        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, this.namaModel);
            pstmt.setString(2, this.jenisMobil);
            pstmt.setString(3, this.transmisi);
            pstmt.setDouble(4, this.harga);
            pstmt.setString(5, this.userId);
            pstmt.setString(6, this.type);
            pstmt.setString(7, this.fitur);
            pstmt.setString(8, this.idMobil);

            pstmt.executeUpdate();
            System.out.println("Mobil updated successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to update mobil: " + e.getMessage());
        }
    }

    public void delete() {
        String sql = "DELETE FROM mobil WHERE id_mobil = ?";

        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, this.idMobil);

            pstmt.executeUpdate();
            System.out.println("Mobil deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to delete mobil: " + e.getMessage());
        }
    }
}
