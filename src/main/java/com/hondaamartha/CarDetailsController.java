package com.hondaamartha;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.hondaamartha.car.Category;
import com.hondaamartha.model.CreditSimulation;
import com.hondaamartha.model.Mobil;
import com.hondaamartha.model.ServiceFee;
import com.hondaamartha.model.TestDrive;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class CarDetailsController {

    @FXML
    private TextField dpTextField;

    @FXML
    private VBox simulationSection;

    @FXML
    private VBox testDriveForm;

    @FXML
    private VBox serviceFeeSection;

    @FXML
    private TextField kilometersField;
    @FXML
    private TableView<ServiceFee> serviceFeeTable;
    @FXML
    private TableColumn<ServiceFee, String> serviceColumn;
    @FXML
    private TableColumn<ServiceFee, String> feeColumn;

    @FXML
    private TableView<CreditSimulation> simulationTable;

    @FXML
    private TableColumn<CreditSimulation, String> descriptionColumn;
    @FXML
    private TableColumn<CreditSimulation, String> oneYearColumn;
    @FXML
    private TableColumn<CreditSimulation, String> twoYearColumn;
    @FXML
    private TableColumn<CreditSimulation, String> threeYearColumn;
    @FXML
    private TableColumn<CreditSimulation, String> fourYearColumn;
    @FXML
    private TableColumn<CreditSimulation, String> fiveYearColumn;

    @FXML
    private Label carNameLabel, priceLabel, featureLabel;

    private Mobil car;

    @FXML
    private void showSimulationTable() {
        simulationSection.setVisible(true);
        simulationSection.setManaged(true);
        testDriveForm.setVisible(false);
        testDriveForm.setManaged(false);
        serviceFeeSection.setVisible(false);
        serviceFeeSection.setManaged(false);
    }

    @FXML
    private void showTestDriveForm() {
        simulationSection.setVisible(false);
        simulationSection.setManaged(false);
        testDriveForm.setVisible(true);
        testDriveForm.setManaged(true);
        serviceFeeSection.setVisible(false);
        serviceFeeSection.setManaged(false);
    }

    @FXML
    private void showServiceFeeTable() {
        simulationSection.setVisible(false);
        simulationSection.setManaged(false);
        testDriveForm.setVisible(false);
        testDriveForm.setManaged(false);
        serviceFeeSection.setVisible(true);
        serviceFeeSection.setManaged(true);
    }


    // kiri
    public void setCarDetails(Mobil car) {
        this.car = car;
        this.modelName = car.getNamaModel();

        carNameLabel.setText(car.getNamaModel() + " " + car.getType());
        priceLabel.setText(String.format("Harga: Rp%,.0f", car.getHarga()));
        featureLabel.setText("Fitur:\n"+car.getFitur().replace(", ","\n"));
        // List<String> colorsList = CarRepository.getCarColors(car.getNamaModel());
        // String colors = String.join("\n", colorsList);
        // colorsLabel.setText("Warna:\n"+colors);

        initializeTableColumns();
    }

    private void initializeTableColumns() {
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        oneYearColumn.setCellValueFactory(new PropertyValueFactory<>("oneYear"));
        twoYearColumn.setCellValueFactory(new PropertyValueFactory<>("twoYear"));
        threeYearColumn.setCellValueFactory(new PropertyValueFactory<>("threeYear"));
        fourYearColumn.setCellValueFactory(new PropertyValueFactory<>("fourYear"));
        fiveYearColumn.setCellValueFactory(new PropertyValueFactory<>("fiveYear"));

        serviceColumn.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        feeColumn.setCellValueFactory(new PropertyValueFactory<>("fee"));
    }

    @FXML
    private void simulateCredit() {
        double hargaMobil = car.getHarga();
        double uangMuka = Double.parseDouble(dpTextField.getText()) / 100 * hargaMobil;
        double principal = hargaMobil - uangMuka;
        double annualInterestRate = 0.05;  // 5%

        ObservableList<CreditSimulation> simulations = FXCollections.observableArrayList();

        simulations.add(new CreditSimulation("Uang Muka",
            String.format("Rp%,.0f", uangMuka), 
            String.format("Rp%,.0f", uangMuka),
            String.format("Rp%,.0f", uangMuka), 
            String.format("Rp%,.0f", uangMuka), 
            String.format("Rp%,.0f", uangMuka)
        ));

        simulations.add(new CreditSimulation("Angsuran/Bulan",
            calculateInstallment(principal, annualInterestRate, 1),
            calculateInstallment(principal, annualInterestRate, 2),
            calculateInstallment(principal, annualInterestRate, 3),
            calculateInstallment(principal, annualInterestRate, 4),
            calculateInstallment(principal, annualInterestRate, 5)
        ));

        simulations.add(new CreditSimulation("Admin Fee",
            String.format("Rp%,.0f", calculateAdminFee(1)),
            String.format("Rp%,.0f", calculateAdminFee(2)),
            String.format("Rp%,.0f", calculateAdminFee(3)),
            String.format("Rp%,.0f", calculateAdminFee(4)),
            String.format("Rp%,.0f", calculateAdminFee(5))
        ));

        simulations.add(new CreditSimulation("Asuransi",
            String.format("Rp%,.0f", calculateInsurance(principal)),
            String.format("Rp%,.0f", calculateInsurance(principal)),
            String.format("Rp%,.0f", calculateInsurance(principal)),
            String.format("Rp%,.0f", calculateInsurance(principal)),
            String.format("Rp%,.0f", calculateInsurance(principal))
        ));

        simulations.add(new CreditSimulation("Total Pembayaran",
            calculateTotalPayment(uangMuka, calculateInstallment(principal, annualInterestRate, 1), calculateAdminFee(1), calculateInsurance(principal), 1),
            calculateTotalPayment(uangMuka, calculateInstallment(principal, annualInterestRate, 2), calculateAdminFee(2), calculateInsurance(principal), 2),
            calculateTotalPayment(uangMuka, calculateInstallment(principal, annualInterestRate, 3), calculateAdminFee(3), calculateInsurance(principal), 3),
            calculateTotalPayment(uangMuka, calculateInstallment(principal, annualInterestRate, 4), calculateAdminFee(4), calculateInsurance(principal), 4),
            calculateTotalPayment(uangMuka, calculateInstallment(principal, annualInterestRate, 5), calculateAdminFee(5), calculateInsurance(principal), 5)
        ));

        simulationTable.setItems(simulations);
    }

    private String calculateInstallment(double principal, double annualInterestRate, int years) {
        int totalMonths = years * 12;
        double monthlyInterestRate = annualInterestRate / 12;
        double monthlyPayment = (principal * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -totalMonths));
        return String.format("Rp%,.0f", monthlyPayment);
    }

    private double calculateAdminFee(int year) {
        return 1200000 + (year - 1) * 50000;
    }

    private double calculateInsurance(double principal) {
        return principal * 0.06;
    }

    private String calculateTotalPayment(double downPayment, String installment, double adminFee, double insurance, int years) {
        double totalInstallment = Double.parseDouble(installment.replace("Rp", "").replace(",", "")) * years * 12;
        double totalAdminFee = adminFee * years;
        return String.format("Rp%,.0f", downPayment + totalInstallment + totalAdminFee + insurance);
    }
  
    private CarsController parentController;
    private String modelName;

    public void setParentController(CarsController parentController) {
        this.parentController = parentController;
    }

    @FXML
    private void goBackToCarTypes() {
        if (parentController != null && modelName != null) {
            parentController.showCarTypes(modelName);
        }
    }


    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField locationField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextArea remarksField;

    @FXML
    private void handleTestDriveSubmit() throws SQLException {
        String namaCustomer = nameField.getText();
        String email = emailField.getText();
        String noTelp = phoneField.getText();
        String lokasi = locationField.getText();
        LocalDate tanggalTestDrive = datePicker.getValue();
        String keterangan = remarksField.getText();
        
        if (namaCustomer.isEmpty() || email.isEmpty() || noTelp.isEmpty() || lokasi.isEmpty() || tanggalTestDrive == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please complete all fields.");
            alert.show();
            return;
        }

        // bikin instance testdrive baru
        TestDrive testDrive = new TestDrive(
            generateTestDriveId(), 
            namaCustomer,
            email,
            lokasi,
            noTelp,
            LocalDateTime.of(tanggalTestDrive, LocalDateTime.now().toLocalTime()),
            car.getNamaModel(),
            car.getType(),
            keterangan,
            lokasi,
            LoginController.getCurrentUser().getIdUser(),
            car.getIdMobil()
        );

        testDrive.insert();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Test drive scheduled successfully!");
        alert.show();


    }

    private String generateTestDriveId() throws SQLException {
        String newId = "TD00000001"; // default

        // curr. max id_test_drive
        String query = "SELECT id_test_drive FROM test_drive ORDER BY id_test_drive DESC LIMIT 1";
        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String lastId = rs.getString("id_test_drive");
                
                // parse numeric, increment
                int numericPart = Integer.parseInt(lastId.substring(2));
                numericPart++;
                
                // format back to "TD" + 8 dig num, leading 0s
                newId = String.format("TD%08d", numericPart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return newId;
    }

    @FXML
    private void calculateServiceFee() {
        double kilometers = Double.parseDouble(kilometersField.getText());

        Category category = car.getCategory();

        double fee = category.calculateFee(kilometers);

        ObservableList<ServiceFee> services = category.getServices(kilometers);

        serviceFeeTable.setItems(services);
        serviceColumn.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        feeColumn.setCellValueFactory(new PropertyValueFactory<>("fee"));

        System.out.println("Total fee: " + fee);
    }
}

