package com.hondaamartha.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import com.hondaamartha.Database;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TestDrive {
    private final StringProperty idTestDrive;
    private final StringProperty namaCustomer;
    private final StringProperty email;
    private final StringProperty alamat;
    private final StringProperty noTelp;
    private final ObjectProperty<LocalDateTime> tanggalTestDrive;
    private final StringProperty modelMobil;
    private final StringProperty typeMobil;
    private final StringProperty keterangan;
    private final StringProperty lokasi;
    private final StringProperty userIdUser;
    private final StringProperty idMobil;

    public TestDrive(String idTestDrive, String namaCustomer, String email, String alamat, String noTelp,
                     LocalDateTime tanggalTestDrive, String modelMobil, String typeMobil, String keterangan,
                     String lokasi, String userIdUser, String idMobil) {
        this.idTestDrive = new SimpleStringProperty(idTestDrive);
        this.namaCustomer = new SimpleStringProperty(namaCustomer);
        this.email = new SimpleStringProperty(email);
        this.alamat = new SimpleStringProperty(alamat);
        this.noTelp = new SimpleStringProperty(noTelp);
        this.tanggalTestDrive = new SimpleObjectProperty<>(tanggalTestDrive);
        this.modelMobil = new SimpleStringProperty(modelMobil);
        this.typeMobil = new SimpleStringProperty(typeMobil);
        this.keterangan = new SimpleStringProperty(keterangan);
        this.lokasi = new SimpleStringProperty(lokasi);
        this.userIdUser = new SimpleStringProperty(userIdUser);
        this.idMobil = new SimpleStringProperty(idMobil);
    }

    public StringProperty getIdTestDriveProperty() { return idTestDrive; }
    public StringProperty getNamaCustomerProperty() { return namaCustomer; }
    public StringProperty getEmailProperty() { return email; }
    public StringProperty getAlamatProperty() { return alamat; }
    public StringProperty getNoTelpProperty() { return noTelp; }
    public ObjectProperty<LocalDateTime> getTanggalTestDriveProperty() { return tanggalTestDrive; }
    public StringProperty getModelMobilProperty() { return modelMobil; }
    public StringProperty getTypeMobilProperty() { return typeMobil; }
    public StringProperty getKeteranganProperty() { return keterangan; }
    public StringProperty getLokasiProperty() { return lokasi; }
    public StringProperty getUserIdUserProperty() { return userIdUser; }
    public StringProperty getIdMobilProperty() { return idMobil; }

    public String getIdTestDrive() { return idTestDrive.get(); }
    public void setIdTestDrive(String idTestDrive) { this.idTestDrive.set(idTestDrive); }

    public String getNamaCustomer() { return namaCustomer.get(); }
    public void setNamaCustomer(String namaCustomer) { this.namaCustomer.set(namaCustomer); }

    public String getEmail() { return email.get(); }
    public void setEmail(String email) { this.email.set(email); }

    public String getAlamat() { return alamat.get(); }
    public void setAlamat(String alamat) { this.alamat.set(alamat); }

    public String getNoTelp() { return noTelp.get(); }
    public void setNoTelp(String noTelp) { this.noTelp.set(noTelp); }

    public LocalDateTime getTanggalTestDrive() { return tanggalTestDrive.get(); }
    public void setTanggalTestDrive(LocalDateTime tanggalTestDrive) { this.tanggalTestDrive.set(tanggalTestDrive); }

    public String getModelMobil() { return modelMobil.get(); }
    public void setModelMobil(String modelMobil) { this.modelMobil.set(modelMobil); }

    public String getTypeMobil() { return typeMobil.get(); }
    public void setTypeMobil(String typeMobil) { this.typeMobil.set(typeMobil); }

    public String getKeterangan() { return keterangan.get(); }
    public void setKeterangan(String keterangan) { this.keterangan.set(keterangan); }

    public String getLokasi() { return lokasi.get(); }
    public void setLokasi(String lokasi) { this.lokasi.set(lokasi); }

    public String getUserIdUser() { return userIdUser.get(); }
    public void setUserIdUser(String userIdUser) { this.userIdUser.set(userIdUser); }

    public String getIdMobil() { return idMobil.get(); }
    public void setIdMobil(String idMobil) { this.idMobil.set(idMobil); }

    public void insert() {
        String sql = "INSERT INTO test_drive (id_test_drive, nama_customer, email, alamat, no_telp, tanggal_test_drive, model_mobil, type_mobil, keterangan, lokasi, user_id_user, id_mobil) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, this.idTestDrive.get());
            pstmt.setString(2, this.namaCustomer.get());
            pstmt.setString(3, this.email.get());
            pstmt.setString(4, this.alamat.get());
            pstmt.setString(5, this.noTelp.get());
            pstmt.setObject(6, this.tanggalTestDrive.get());
            pstmt.setString(7, this.modelMobil.get());
            pstmt.setString(8, this.typeMobil.get());
            pstmt.setString(9, this.keterangan.get());
            pstmt.setString(10, this.lokasi.get());
            pstmt.setString(11, this.userIdUser.get());
            pstmt.setString(12, this.idMobil.get());

            pstmt.executeUpdate();
            System.out.println("Test drive added successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to insert test drive: " + e.getMessage());
        }
    }

    public void update() {
        String sql = "UPDATE test_drive SET nama_customer = ?, email = ?, alamat = ?, no_telp = ?, tanggal_test_drive = ?, model_mobil = ?, type_mobil = ?, keterangan = ?, lokasi = ?, user_id_user = ?, id_mobil = ? WHERE id_test_drive = ?";
        
        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, this.namaCustomer.get());
            pstmt.setString(2, this.email.get());
            pstmt.setString(3, this.alamat.get());
            pstmt.setString(4, this.noTelp.get());
            pstmt.setObject(5, this.tanggalTestDrive.get());
            pstmt.setString(6, this.modelMobil.get());
            pstmt.setString(7, this.typeMobil.get());
            pstmt.setString(8, this.keterangan.get());
            pstmt.setString(9, this.lokasi.get());
            pstmt.setString(10, this.userIdUser.get());
            pstmt.setString(11, this.idMobil.get());
            pstmt.setString(12, this.idTestDrive.get());

            pstmt.executeUpdate();
            System.out.println("Test drive updated successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to update test drive: " + e.getMessage());
        }
    }

    public void delete() {
        String sql = "DELETE FROM test_drive WHERE id_test_drive = ?";
        
        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, this.idTestDrive.get());

            pstmt.executeUpdate();
            System.out.println("Test drive deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to delete test drive: " + e.getMessage());
        }
    }

    public static TestDrive getById(String idTestDrive) {
        String sql = "SELECT * FROM test_drive WHERE id_test_drive = ?";

        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, idTestDrive);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new TestDrive(
                    rs.getString("id_test_drive"),
                    rs.getString("nama_customer"),
                    rs.getString("email"),
                    rs.getString("alamat"),
                    rs.getString("no_telp"),
                    rs.getTimestamp("tanggal_test_drive").toLocalDateTime(),
                    rs.getString("model_mobil"),
                    rs.getString("type_mobil"),
                    rs.getString("keterangan"),
                    rs.getString("lokasi"),
                    rs.getString("user_id_user"),
                    rs.getString("id_mobil")
                );
            }
        } catch (SQLException e) {
            System.out.println("Failed to fetch test drive: " + e.getMessage());
        }
        return null;
    }
    
    public static ObservableList<TestDrive> getAll() {
        ObservableList<TestDrive> list = FXCollections.observableArrayList();
        String sql = "SELECT * FROM test_drive";

        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new TestDrive(
                    rs.getString("id_test_drive"),
                    rs.getString("nama_customer"),
                    rs.getString("email"),
                    rs.getString("alamat"),
                    rs.getString("no_telp"),
                    rs.getTimestamp("tanggal_test_drive").toLocalDateTime(),
                    rs.getString("model_mobil"),
                    rs.getString("type_mobil"),
                    rs.getString("keterangan"),
                    rs.getString("lokasi"),
                    rs.getString("user_id_user"),
                    rs.getString("id_mobil")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Failed to fetch test drives: " + e.getMessage());
        }
        return list;
    }
}
