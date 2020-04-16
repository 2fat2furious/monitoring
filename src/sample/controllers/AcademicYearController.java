package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.data.AcademicYear;
import sample.service.AcademicYearService;

import java.io.IOException;
import java.sql.SQLException;

public class AcademicYearController {
    @FXML
    private TableView academicYearsTable;
    @FXML
    private TableColumn<AcademicYear, Integer> yearInitialColumn;
    @FXML
    private TableColumn<AcademicYear, Integer> yearEndColumn;

    @FXML
    private Label yearInitialLabel;
    @FXML
    private Label yearEndLabel;

    @FXML
    private TextField yearInitialField;
    @FXML
    private TextField yearEndField;

    @FXML
    private GridPane detailsPanel;

    @FXML
    private Button deleteButton;

    AcademicYearService service = new AcademicYearService();
    public boolean editing = false;

    @FXML
    void initialize() throws SQLException {
        yearInitialColumn.setCellValueFactory(cell -> cell.getValue().yearInitialProperty().asObject());
        yearEndColumn.setCellValueFactory(cell -> cell.getValue().yearEndProperty().asObject());


        deleteButton.disableProperty().bind(academicYearsTable.getSelectionModel().selectedItemProperty().isNull());
        detailsPanel.visibleProperty().bind(academicYearsTable.getSelectionModel().selectedItemProperty().isNotNull());

        academicYearsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {

            if (newV != null) {
                AcademicYear academicYear = (AcademicYear) newV;

                yearInitialField.setText(Integer.toString(academicYear.getYearInitial()));
                yearEndField.setText(Integer.toString(academicYear.getYearEnd()));
            }
        });
        academicYearsTable.getSelectionModel().selectFirst();
    }

    public boolean showAcademicYearEditDialog(AcademicYear academicYear) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AcademicYearController.class.getResource("academic-year-edit-dialog.controller.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Добавление учебного года");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            AcademicYearDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAcademicYear(academicYear);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private void handleNewAcademicYear() {
        AcademicYear academicYear = new AcademicYear();
            service.add(
                    academicYear.getYearInitial(),
                    academicYear.getYearEnd());
            loadData();

    }

    @FXML
    void handleRemove() {
        int index = academicYearsTable.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            AcademicYear academicYear = (AcademicYear) academicYearsTable.getSelectionModel().getSelectedItem();
            service.delete(String.valueOf(academicYear.getId_ay()));
            academicYearsTable.getItems().remove(academicYear);
        }
    }

    @FXML
    private void handleEditAcademicYear() {
        AcademicYear academicYear = (AcademicYear) academicYearsTable.getSelectionModel().getSelectedItem();
        if (academicYear != null) {
            boolean okClicked = showAcademicYearEditDialog(academicYear);
            if (okClicked) {
                showAcademicYearDetails(academicYear);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Не выбран учебный год");
            alert.setContentText("Пожалуйста, выберите учебный год из таблицы!");
            alert.showAndWait();
        }
    }


    private void showAcademicYearDetails(AcademicYear academicYear) {
        if (academicYear != null) {
            yearInitialLabel.setText(Integer.toString(academicYear.getYearInitial()));
            yearEndLabel.setText(Integer.toString(academicYear.getYearEnd()));
        } else {
            yearInitialLabel.setText("");
            yearEndLabel.setText("");
        }
    }

    TableView<AcademicYear> getAcademicYearTable() {
        return academicYearsTable;
    }

    public void loadData() {

    }
}
