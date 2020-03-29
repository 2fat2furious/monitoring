//package sample.controllers;
//
//import javafx.beans.binding.BooleanBinding;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.GridPane;
//import javafx.stage.Modality;
//import javafx.stage.Stage;
//import sample.data.Child;
//import sample.data.KinderGarten;
//import sample.service.ChildService;
//import sample.service.KinderGartenService;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import java.time.format.DateTimeFormatter;
//
//public class ChildController extends AbstractChildInfoController{
//
//    @FXML
//    private TableView childsTable;
//    @FXML
//    private TableColumn<Child, String> fioColumn;
//    @FXML
//    private Label fioLabel;
//    @FXML
//    private Label dateOfBirthLabel;
//    @FXML
//    private Label placeOfBirthLabel;
//    @FXML
//    private Label homePhoneLabel;
//    @FXML
//    private Label homeAddressLabel;
//    @FXML
//    private Label familyCompositionLabel;
//    @FXML
//    private Label healthStatusLabel;
//    @FXML
//    private GridPane detailsPanel;
//    @FXML
//    private Button deleteButton;
//    @FXML
//    private Button editingButton;
//
//
//    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//
//    ChildService service = new ChildService();
//
//    @FXML
//    void initialize() throws SQLException {
//        fioColumn.setCellValueFactory(cell -> cell.getValue().fioProperty());
////        fullTitleLabel.setCellValueFactory(cell -> cell.getValue().fullTitleProperty());
////        addressColumn.setCellValueFactory(cell -> cell.getValue().addressProperty());
////        phoneColumn.setCellValueFactory(cell -> cell.getValue().phoneProperty());
////        fioColumn.setCellValueFactory(cell -> cell.getValue().fioProperty());
//        childsTable.setItems(service.getAll());
//
//        BooleanBinding modelIsSelected = childsTable.getSelectionModel().selectedItemProperty().isNull();
//        deleteButton.disableProperty().bind(modelIsSelected);
//        editingButton.disableProperty().bind(modelIsSelected);
//        detailsPanel.visibleProperty().bind(childsTable.getSelectionModel().selectedItemProperty().isNotNull());
//
//        childsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
//
//            if (newV != null) {
//                Child child = (Child) newV;
//
//                fioLabel.setText(child.getName());
//                dateOfBirthLabel.setText(child.getDateOfBirth().toLocalDate().format(formatter));
//                placeOfBirthLabel.setText(child.getPlaceOfBirth());
//                homePhoneLabel.setText(child.getHomePhone());
//                homeAddressLabel.setText(child.getHomeAddress());
//                familyCompositionLabel.setText(child.getFamilyComposition());
//                healthStatusLabel.setText(child.getHealthStatus());
//            }
//        });
//
//        childsTable.getSelectionModel().selectFirst();
//    }
//
//    public boolean showChildEditDialog(Child child) {
//        try {
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(sample.controllers.ChildController.class.getResource("child-edit-dialog.controller.fxml"));
//            AnchorPane page = (AnchorPane) loader.load();
//
//            Stage dialogStage = new Stage();
//            dialogStage.setTitle("Добавление ребенка");
//            dialogStage.initModality(Modality.WINDOW_MODAL);
////            dialogStage.initOwner(primaryStage);
//            Scene scene = new Scene(page);
//            dialogStage.setScene(scene);
//
//            ChildDialogController controller = loader.getController();
//            controller.setDialogStage(dialogStage);
//            controller.setChild(child);
//
//            dialogStage.showAndWait();
//
//            return controller.isOkClicked();
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    @FXML
//    private void handleNewChild() {
//        Child child = new Child();
//        boolean okClicked = showChildEditDialog(child);
//        if (okClicked) {
//            service.add(child.getName(),
//                    child.getDateOfBirth(),
//                    child.getPlaceOfBirth(),
//                    child.getHomePhone(),
//                    child.getHomeAddress(),
//                    child.getFamilyComposition(),
//                    child.getHealthStatus()
//            );
//            loadData();
//        }
//    }
//
//    @FXML
//    void handleRemove() {
//        int index = childsTable.getSelectionModel().getSelectedIndex();
//        if (index >= 0) {
//            Child child = (Child) childsTable.getSelectionModel().getSelectedItem();
//            service.delete(Long.toString(child.getId_c()));
//            childsTable.getItems().remove(child);
//        }
//    }
//
//    @FXML
//    private void handleEditChild() {
//        Child child = (Child) childsTable.getSelectionModel().getSelectedItem();
//        if (child != null) {
//            boolean okClicked = showChildEditDialog(child);
//            if (okClicked) {
//                showChildDetails(child);
//            }
//        } else {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
////            alert.initOwner();
//            alert.setTitle("Ничего не выбрано");
//            alert.setHeaderText("Не выбран ребенок");
//            alert.setContentText("Пожалуйста, выберите ребенка из таблицы!");
//            alert.showAndWait();
//        }
//    }
//
//
//    private void showChildDetails(Child child) {
//        if (child != null) {
//            fioLabel.setText(child.getName());
//            dateOfBirthLabel.setText(child.getDateOfBirth().toLocalDate().format(formatter));
//            placeOfBirthLabel.setText(child.getPlaceOfBirth());
//            homePhoneLabel.setText(child.getHomePhone());
//            homeAddressLabel.setText(child.getHomeAddress());
//            familyCompositionLabel.setText(child.getFamilyComposition());
//            healthStatusLabel.setText(child.getHealthStatus());
//        } else {
//            fioLabel.setText("");
//        }
//    }
//
//    TableView<Child> getChildTable() {
//        return childsTable;
//    }
//
//    void loadData() {
//
//    }
//
//}
