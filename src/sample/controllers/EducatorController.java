package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.data.Educator;
import sample.data.KinderGarten;
import sample.service.EducatorService;


import java.io.IOException;
import java.sql.SQLException;

public class EducatorController extends AbstractTabController<KinderGartenInfoController, KinderGarten> {
    @FXML
    private TableView educatorsTable;
    @FXML
    private TableColumn<Educator, String> nameColumn;
    @FXML
    private TableColumn<Educator, String> positionColumn;

    @FXML
    private TableColumn<Educator, Long> idkgColumn;

    @FXML
    private Label nameLabel;
    @FXML
    private Label educationLabel;
    @FXML
    private Label positionLabel;
    @FXML
    private Label experienceLabel;
    @FXML
    private Label phoneLabel;

    @FXML
    private GridPane detailsPanel;

    @FXML
    private TextField name;
    @FXML
    private TextField education;
    @FXML
    private TextField position;
    @FXML
    private TextField experience;
    @FXML
    private TextField phone;
    @FXML
    private TextField idkg;

    @FXML
    private Button deleteButton;

    @FXML
    private Label titleKinderGartenLabel;

    EducatorService service = new EducatorService();
    public boolean editing = false;
    private long kgId = 1;

    @FXML
    void initialize() throws SQLException {
        nameColumn.setCellValueFactory(cell -> cell.getValue().fioProperty());
        positionColumn.setCellValueFactory(cell -> cell.getValue().positionProperty());


        deleteButton.disableProperty().bind(educatorsTable.getSelectionModel().selectedItemProperty().isNull());
        detailsPanel.visibleProperty().bind(educatorsTable.getSelectionModel().selectedItemProperty().isNotNull());

        educatorsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {

            if (newV != null) {
                Educator educator = (Educator) newV;

                name.setText(educator.getFio());
                education.setText(educator.getEducation());
                position.setText(educator.getPosition());
                experience.setText(Integer.toString(educator.getWorkExperience()));
                phone.setText(educator.getPhone());
            }
        });

        educatorsTable.getSelectionModel().selectFirst();
    }

    public boolean showEducatorEditDialog(Educator educator) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(EducatorController.class.getResource("educator-edit-dialog.controller.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Добавление воспитателя");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            EducatorDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setEducator(educator);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private void handleNewEducator() {
        Educator educator = new Educator();
        boolean okClicked = showEducatorEditDialog(educator);
        if (okClicked) {
            service.add(educator.getFio(),
                    educator.getIdKinderGarten(),
                    educator.getEducation(),
                    educator.getPosition(),
                    educator.getWorkExperience(),
                    educator.getPhone());
            loadData();
        }
    }

    @FXML
    void handleRemove() {
        int index = educatorsTable.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            Educator educator = (Educator) educatorsTable.getSelectionModel().getSelectedItem();
            service.delete(educator.getFio());
            educatorsTable.getItems().remove(educator);
        }
    }

    @FXML
    private void handleEditEducator() {
        Educator educator = (Educator) educatorsTable.getSelectionModel().getSelectedItem();
        if (parent != null) {
            boolean okClicked = showEducatorEditDialog(educator);
            if (okClicked) {
                showEducatorDetails(educator);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.initOwner();
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Не выбран воспитатель");
            alert.setContentText("Пожалуйста, выберите воспитателя из таблицы!");
            alert.showAndWait();
        }
    }


    private void showEducatorDetails(Educator educator) {
        if (parent != null) {
            nameLabel.setText(educator.getFio());
            educationLabel.setText(educator.getEducation());
            positionLabel.setText(educator.getPosition());
            experienceLabel.setText(Integer.toString(educator.getWorkExperience()));
            phoneLabel.setText(educator.getPhone());
        } else {
            nameLabel.setText("");
            educationLabel.setText("");
            positionLabel.setText("");
            experienceLabel.setText("");
            phoneLabel.setText("");
        }
    }

    TableView<Educator> getEducatorTable() {
        return educatorsTable;
    }

    @Override
    public void loadData() {
        // TODO: ADD TITLE
        try {
            educatorsTable.setItems(service.getEducatorsByKGId(parent.kg.getValue().getSelectedItem().getIdKinderGarten()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
