package com.hondaamartha;

import java.time.LocalDateTime;

import com.hondaamartha.model.TestDrive;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class ManageTestDriveController {

    @FXML
    private TableView<TestDrive> testDriveTable;

    @FXML
    private TableColumn<TestDrive, String> colId, colNamaCustomer, colEmail, colNoTelp, colModelMobil;

    @FXML
    private TableColumn<TestDrive, LocalDateTime> colTanggalTestDrive;

    @FXML
    private TableColumn<TestDrive, Void> colDelete;

    private ObservableList<TestDrive> testDriveList;

    public void initialize() {
        testDriveList = FXCollections.observableArrayList();
        
        colId.setCellValueFactory(cellData -> cellData.getValue().getIdTestDriveProperty());
        colNamaCustomer.setCellValueFactory(cellData -> cellData.getValue().getNamaCustomerProperty());
        colEmail.setCellValueFactory(cellData -> cellData.getValue().getEmailProperty());
        colNoTelp.setCellValueFactory(cellData -> cellData.getValue().getNoTelpProperty());
        colModelMobil.setCellValueFactory(cellData -> cellData.getValue().getModelMobilProperty());
        colTanggalTestDrive.setCellValueFactory(cellData -> cellData.getValue().getTanggalTestDriveProperty());

        loadData();

        addDeleteButtonToTable();
    }

    private void loadData() {
        testDriveList.addAll(TestDrive.getAll());
        testDriveTable.setItems(testDriveList);
    }

    private void addDeleteButtonToTable() {
        Callback<TableColumn<TestDrive, Void>, TableCell<TestDrive, Void>> cellFactory = param -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setOnAction(event -> {
                    TestDrive testDrive = getTableView().getItems().get(getIndex());
                    deleteTestDrive(testDrive);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        };
        colDelete.setCellFactory(cellFactory);
    }

    private void deleteTestDrive(TestDrive testDrive) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi");
        alert.setHeaderText("Apakah anda yakin untuk menghapus data ini?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            testDrive.delete(); 
            testDriveList.remove(testDrive);
        }  
    }
}
