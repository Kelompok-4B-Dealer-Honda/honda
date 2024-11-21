package com.hondaamartha;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.hondaamartha.model.Mobil;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;




public class CarsController {
    @FXML
    private VBox mainContainer;

    @FXML
    private VBox productMenuContainer, carTypesMenuContainer;

    @FXML
    private HBox categories;

    @FXML
    private VBox addModelPageContainer;

    @FXML
    private VBox updateModelPageContainer;

    @FXML
    private VBox addCarTypePageContainer;

    @FXML
    private TextField modelNameFieldUpdate;


    @FXML
    private TextField modelNameField;
    @FXML
    private ComboBox<String> carTypeField;
    @FXML
    private TextField transmissionField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField typeField;
    // @FXML
    // private TextField colorField;
    @FXML
    private TextField featureField;


    @FXML
    private TextField carTypeNameField, carTypeTransmissionField, carTypePriceField;

    @FXML
    private TextField carTypeFeatureField;

    @FXML
    private ImageView modelImageView;

    @FXML
    private ImageView carTypeImageView;

    private final CarRepository carRepository = new CarRepository();
    private String selectedCarModel;
    private File selectedModelFile;
    private Mobil selectedCarType;

    // initalize otomatis jalan pas buka page-nya, memang dari javafx
    @FXML
    public void initialize() {
        loadCarModels();
        carTypeField.setItems(FXCollections.observableArrayList("SUV", "Sedan", "Hatchback"));
        carTypeField.setPromptText("Kategori");
    }

    private void loadCarModels() {
        if(LoginController.getUserRole().matches("admin|superadmin")){
            productMenuContainer.setVisible(true);
            productMenuContainer.setManaged(true);
            carTypesMenuContainer.setVisible(false);
            carTypesMenuContainer.setManaged(false);
        }else{
            productMenuContainer.setVisible(false);
            productMenuContainer.setManaged(false);
        }

        List<Mobil> cars = carRepository.getUniqueCarModels();
        GridPane gridPane = new GridPane();

        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setAlignment(Pos.CENTER);

        int column = 0;
        int row = 0;

        for (Mobil car : cars) {
            VBox carBox = createCarBox(car);

            gridPane.add(carBox, column, row);

            // next pos
            column++;
            if (column >= 4) {
                column = 0;
                row++;
            }
        }

        mainContainer.getChildren().add(gridPane);
    }

    private VBox createCarBox(Mobil car) {
        String modelImagePath = "/com/hondaamartha/images/car_models/" + car.getNamaModel() + ".png";
        Image modelImage;

        try {
            modelImage = new Image(getClass().getResourceAsStream(modelImagePath));
        } catch (Exception e) {
            modelImage = new Image(getClass().getResourceAsStream("/com/hondaamartha/images/jik.png"));
        }

        ImageView imageView = new ImageView(modelImage);
        imageView.setFitWidth(200);
        imageView.setFitHeight(120);

        Label carTitle = new Label(car.getNamaModel());
        carTitle.getStyleClass().add("car-title");

        Button priceButton = new Button();
        priceButton.getStyleClass().add("price-button");

        Label priceLabel = new Label("Harga Mulai Dari");
        priceLabel.getStyleClass().add("price-label");

        Label priceValue = new Label(String.format("Rp%,.0f*", car.getHarga()));
        priceValue.getStyleClass().add("price-value");

        VBox priceBox = new VBox(priceLabel, priceValue);
        priceBox.setAlignment(javafx.geometry.Pos.CENTER);

        TextFlow textFlow = new TextFlow(priceBox);
        priceButton.setGraphic(textFlow);

        priceButton.setOnAction(event -> showCarTypes(car.getNamaModel()));


        VBox carBox = new VBox(imageView);
        carBox.setAlignment(javafx.geometry.Pos.CENTER);
        carBox.setSpacing(10);
        carBox.getStyleClass().add("car-item");

        if (LoginController.getUserRole().matches("admin|superadmin")) {
            Button pencilButton = new Button("✏️");
            pencilButton.getStyleClass().add("pencil-button");

            pencilButton.setOnAction(event -> {
                selectedCarModel = car.getNamaModel();
                highlightSelectedCarBox(carBox);
                showUpdateModelPage();
                modelNameFieldUpdate.setText(selectedCarModel);
                String selectedModelImagePath = "/com/hondaamartha/images/car_models/" + selectedCarModel + ".png";
                Image selectedModelImage;

                try {
                    selectedModelImage = new Image(getClass().getResourceAsStream(selectedModelImagePath));
                } catch (Exception e) {
                    selectedModelImage = new Image(getClass().getResourceAsStream("/com/hondaamartha/images/jik.png"));
                }

                modelImageView.setImage(selectedModelImage);
            });

            HBox titleAndEditBox = new HBox(carTitle, pencilButton);
            titleAndEditBox.setAlignment(Pos.CENTER);
            titleAndEditBox.setSpacing(5);

            carBox.getChildren().addAll(titleAndEditBox, priceButton);
        } else {
            carBox.getChildren().addAll(carTitle, priceButton);
        }

        return carBox;
    }


    public void showCarTypes(String modelName) {
        selectedCarModel = modelName;
        if (LoginController.getUserRole().matches("admin|superadmin")) {
            productMenuContainer.setVisible(false);
            productMenuContainer.setManaged(false);
            carTypesMenuContainer.setVisible(true);
            carTypesMenuContainer.setManaged(true);
        }
    
        List<Mobil> carTypes = carRepository.getCarTypesByModel(modelName);
        
        mainContainer.getChildren().clear();
    
        Button backButton = new Button("Kembali ke Produk");
        backButton.setOnAction(e -> {
            mainContainer.getChildren().clear();
            loadCarModels();
        });
    
        HBox backButtonContainer = new HBox(backButton);
        backButtonContainer.setAlignment(Pos.TOP_LEFT);
    
        GridPane typeGrid = new GridPane();
        typeGrid.setHgap(40);
        typeGrid.setVgap(20);
        typeGrid.setAlignment(Pos.CENTER);
    
        int column = 0;
        int row = 0;
    
        for (Mobil carType : carTypes) {
            VBox typeBox = new VBox();
            typeBox.setAlignment(Pos.CENTER);
            typeBox.setSpacing(10);
            typeBox.setMinSize(220, 300);
            typeBox.getStyleClass().add("type-item");
    
            Label typeLabel = new Label(carType.getNamaModel() + " " + carType.getType());
            Label transmissionLabel = new Label("Transmisi: " + carType.getTransmisi());
            Label featuresLabel = new Label("Fitur: " + carType.getFitur());
    
            Button priceButton = new Button("Rp" + String.format("%,.0f", carType.getHarga()));
            priceButton.getStyleClass().add("red-button");
            priceButton.setOnAction(event -> openCarDetails(carType));
    
            if (LoginController.getUserRole().matches("admin|superadmin")) {
                Button pencilButton = new Button("✏️");
                pencilButton.getStyleClass().add("pencil-button");
    
                pencilButton.setOnAction(event -> {
                    selectedCarType = carType;
                    populateCarTypeUpdateFields(carType);
                    
                    String typeImagePath = "/com/hondaamartha/images/cars/" + carType.getType() + ".png";
                    Image carTypeImage;
                    try {
                        carTypeImage = new Image(getClass().getResourceAsStream(typeImagePath));
                    } catch (Exception e) {
                        carTypeImage = new Image(getClass().getResourceAsStream("/com/hondaamartha/images/car.png"));
                    }

                    carTypeImageView.setImage(carTypeImage);
                });
    
                HBox typeLabelAndEditBox = new HBox(typeLabel, pencilButton);
                typeLabelAndEditBox.setAlignment(Pos.CENTER);
                typeLabelAndEditBox.setSpacing(5);
    
                typeBox.getChildren().addAll(typeLabelAndEditBox, transmissionLabel, featuresLabel, priceButton);
            } else {
                typeBox.getChildren().addAll(typeLabel, transmissionLabel, featuresLabel, priceButton);
            }
    
            typeGrid.add(typeBox, column, row);
            column++;
        }
    
        mainContainer.getChildren().addAll(backButtonContainer, typeGrid);
    }
    
    private void populateCarTypeUpdateFields(Mobil carType) {
        carTypeNameField.setText(carType.getType());
        carTypeTransmissionField.setText(carType.getTransmisi());
        carTypePriceField.setText(String.format("%.0f",carType.getHarga()));
        // carTypeColorField.setText(carType.getWarna());
        carTypeFeatureField.setText(carType.getFitur());
    }

    @FXML
    private void openCarDetails(Mobil selectedCar) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("carDetails.fxml"));
            Parent root = loader.load();

            CarDetailsController controller = loader.getController();
            controller.setCarDetails(selectedCar);
            controller.setParentController(this);

            mainContainer.getChildren().clear();
            
            mainContainer.getChildren().add(root);

            productMenuContainer.setVisible(false);
            productMenuContainer.setManaged(false);
            carTypesMenuContainer.setVisible(false);
            carTypesMenuContainer.setManaged(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // admin
    @FXML
    private void updateModel() {
        String newModelName = modelNameFieldUpdate.getText();
        
        if (newModelName != null && !newModelName.isEmpty() && !newModelName.equals(selectedCarModel)) {
            carRepository.updateCarModelName(selectedCarModel, newModelName);

            
            
            Path currentImagePath = Paths.get("hondaamartha/hondaamartha/src/main/resources/com/hondaamartha/images/car_models/" + selectedCarModel + ".png");
            Path newImagePath = Paths.get("hondaamartha/hondaamartha/src/main/resources/com/hondaamartha/images/car_models/" + newModelName + ".png");
            
            try {
                if (Files.exists(currentImagePath)) {
                    Files.move(currentImagePath, newImagePath, StandardCopyOption.REPLACE_EXISTING);
                }
                modelImageView.setImage(new Image(newImagePath.toUri().toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mainContainer.getChildren().clear();
        loadCarModels();
    }

    

    @FXML
    private void uploadModelImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        selectedModelFile = fileChooser.showOpenDialog(null);

        if (selectedModelFile != null) {
            try {
                modelImageView.setImage(new Image(new FileInputStream(selectedModelFile)));

                String modelName = modelNameFieldUpdate.getText();
                String extension = getFileExtension(selectedModelFile.getName());
                
                Path from = Paths.get(selectedModelFile.toURI());
                Path to = Paths.get("hondaamartha/src/main/resources/com/hondaamartha/images/car_models/"+modelName+"."+extension);
                Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }
    
    @FXML
    private void showAddModelPage() {
        addModelPageContainer.setVisible(true);
        addModelPageContainer.setManaged(true);
        updateModelPageContainer.setVisible(false);
        updateModelPageContainer.setManaged(false);
    }

    private void highlightSelectedCarBox(VBox carBox) {
        if (!mainContainer.getChildren().isEmpty() && mainContainer.getChildren().get(0) instanceof GridPane) {
            GridPane gridPane = (GridPane) mainContainer.getChildren().get(0);
    
            gridPane.getChildren().forEach(node -> node.getStyleClass().remove("selected-car-box"));
        }

        carBox.getStyleClass().add("selected-car-box");
    }

    @FXML
    private void showUpdateModelPage() {
        addModelPageContainer.setVisible(false);
        addModelPageContainer.setManaged(false);
        updateModelPageContainer.setVisible(true);
        updateModelPageContainer.setManaged(true);
    }

    @FXML
    public void showAddCarTypePage() {
        addCarTypePageContainer.setVisible(true);
        addCarTypePageContainer.setManaged(true);
        // updateCarTypePageContainer.setVisible(false);
        // updateCarTypePageContainer.setManaged(false);
    }

    @FXML
    public void showUpdateCarTypePage() {
        addCarTypePageContainer.setVisible(false);
        addCarTypePageContainer.setManaged(false);
        // updateCarTypePageContainer.setVisible(true);
        // updateCarTypePageContainer.setManaged(true);
    }

    public void toggleCarTypesMenuContainer(boolean isVisible) {
        carTypesMenuContainer.setVisible(isVisible);
        carTypesMenuContainer.setManaged(isVisible);
        productMenuContainer.setVisible(!isVisible);
        productMenuContainer.setManaged(!isVisible);
    }

    @FXML
    private void manageTestDriveButton(){
        try {
            App.setRoot("manageTestDrive");
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
    private void updateCarType() throws SQLException {
        if (selectedCarType.getIdMobil() != null) {
            String carType = carTypeNameField.getText();
            String transmission = carTypeTransmissionField.getText();
            // String color = carTypeColorField.getText();
            String features = carTypeFeatureField.getText();
            String category = null;
            String query = "SELECT jenis_mobil FROM mobil WHERE nama_model = ?";
            try (Connection conn = Database.connect();
                PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, selectedCarModel);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    category = rs.getString("jenis_mobil");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }

            if (category == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setHeaderText("Kategori tidak ditemukan untuk model: " + selectedCarModel);
                alert.showAndWait();
                return;
            }
    
            double price;
            try {
                price = Double.parseDouble(carTypePriceField.getText());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setHeaderText("Harga tidak valid");
                alert.showAndWait();
                return;
            }
    
            boolean isUpdated = carRepository.updateCarType(selectedCarType.getIdMobil(), carType, transmission, category, "color", price, features);
    
            if (isUpdated) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Update Success");
                alert.setHeaderText("Tipe mobil berhasil diupdate");
                alert.showAndWait();
                showCarTypes(selectedCarModel);
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Update Failed");
                alert.setHeaderText("Gagal");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void deleteCarType() {
        if (selectedCarType != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Delete Confirmation");
            alert.setHeaderText("Delete Type");
            alert.setContentText("Apakah anda yakin untuk menghapus tipe ini ?");

            if (alert.showAndWait().get() == ButtonType.OK) {
                boolean isDeleted = carRepository.deleteCarType(selectedCarType.getIdMobil());

                if (isDeleted) {
                    Alert successAlert = new Alert(AlertType.INFORMATION);
                    successAlert.setTitle("Delete Success");
                    successAlert.setHeaderText("Tipe mobil berhasil dihapus");
                    successAlert.show();
                    showCarTypes(selectedCarModel);
                    carTypeNameField.clear();
                    carTypeTransmissionField.clear();
                    carTypePriceField.clear();
                    // carTypeColorField.clear();
                    carTypeFeatureField.clear();
                } else {
                    Alert errorAlert = new Alert(AlertType.ERROR);
                    errorAlert.setTitle("Delete Failed");
                    errorAlert.setHeaderText("Gagal");
                    errorAlert.showAndWait();
                }
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Car Type Selected");
            alert.setHeaderText("Gaada yang dipilih.");
            alert.showAndWait();
        }
    }

    @FXML
    private void addModel() throws SQLException {
        String modelName = modelNameField.getText();
        String carType = carTypeField.getValue();
        String transmission = transmissionField.getText();
        String type = typeField.getText();
        // String color = colorField.getText();
        String features = featureField.getText();
        
        double price;
        try {
            price = Double.parseDouble(priceField.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Harga tidak valid");
            alert.showAndWait();
            return;
        }

        Mobil newCar = new Mobil(generateMobilId(), modelName, carType, transmission, price, LoginController.getCurrentUser().getIdUser(), type, features);

        newCar.save();

        modelNameField.clear();
        carTypeField.getSelectionModel().clearSelection();;
        transmissionField.clear();
        priceField.clear();
        typeField.clear();
        // colorField.clear();
        featureField.clear();

        mainContainer.getChildren().clear();
        loadCarModels();
    }

    @FXML
    private void addCarType() throws SQLException {
        String carType = carTypeNameField.getText();
        String transmission = carTypeTransmissionField.getText();
        // String color = carTypeColorField.getText();
        String features = carTypeFeatureField.getText();

        double price;
        try {
            price = Double.parseDouble(carTypePriceField.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Harga tidak valid");
            alert.showAndWait();
            return;
        }

        String modelName = selectedCarModel;
        String category = null;
        String query = "SELECT jenis_mobil FROM mobil WHERE nama_model = ?";
        try (Connection conn = Database.connect();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, modelName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                category = rs.getString("jenis_mobil");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        if (category == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Kategori tidak ditemukan untuk model: " + modelName);
            alert.showAndWait();
            return;
        }

        Mobil newCarType = new Mobil(generateMobilId(), modelName, category, transmission, price, LoginController.getCurrentUser().getIdUser(), carType, features);
        
        newCarType.save();

        carTypeNameField.clear();
        carTypeTransmissionField.clear();
        carTypePriceField.clear();
        // carTypeColorField.clear();
        carTypeFeatureField.clear();

        mainContainer.getChildren().clear();
        showCarTypes(modelName);
    }

    private String generateMobilId() throws SQLException {
        String newId = "MOB00000001"; // default

        // max id_test_drive
        String query = "SELECT id_mobil FROM mobil ORDER BY id_mobil DESC LIMIT 1";
        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String lastId = rs.getString("id_mobil");
                
                // parse numeric, tambah
                int numericPart = Integer.parseInt(lastId.substring(3));
                numericPart++;
                
                // format "TD" + 7 dig num, leading 0s
                newId = String.format("MOB%07d", numericPart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return newId;
    }

    @FXML
    private void deleteModel() {
        if (selectedCarModel != null && !selectedCarModel.isEmpty()) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Delete Confirmation");
            alert.setHeaderText("Delete Model");
            alert.setContentText("Yakin untuk delete model: " + selectedCarModel + "?");

            if (alert.showAndWait().get() == ButtonType.OK) {
                carRepository.deleteCarModel(selectedCarModel);
                selectedCarModel = null;
                mainContainer.getChildren().clear();
                loadCarModels();
            }
        } else {
            System.out.println("Gaada model yang dipilih.");
        }
    }

}

