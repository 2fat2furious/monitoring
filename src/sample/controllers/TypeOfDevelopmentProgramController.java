package sample.controllers;

import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.data.TypeOfDevelopmentProgram;
import sample.service.TypeOfDevelopmentProgramService;

import java.io.IOException;
import java.sql.SQLException;

public class TypeOfDevelopmentProgramController {

    @FXML
    private TableView typesOfDevelopmentProgramTable;
    @FXML
    private TableColumn<TypeOfDevelopmentProgram, String> name_pColumn;

    @FXML
    private GridPane detailsPanel;
    @FXML
    private Button deleteButton;
    @FXML
    private Button editingButton;

    TypeOfDevelopmentProgramService service = new TypeOfDevelopmentProgramService();

    @FXML
    void initialize() throws SQLException {
        name_pColumn.setCellValueFactory(cell -> cell.getValue().name_pProperty());
        typesOfDevelopmentProgramTable.setItems(service.getAll());

        BooleanBinding modelIsSelected = typesOfDevelopmentProgramTable.getSelectionModel().selectedItemProperty().isNull();
        deleteButton.disableProperty().bind(modelIsSelected);
        editingButton.disableProperty().bind(modelIsSelected);
        detailsPanel.visibleProperty().bind(typesOfDevelopmentProgramTable.getSelectionModel().selectedItemProperty().isNotNull());

        typesOfDevelopmentProgramTable.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {

            if (newV != null) {
                TypeOfDevelopmentProgram todp = (TypeOfDevelopmentProgram) newV;

                name_pColumn.setText(todp.getName_p());
            }
        });

        typesOfDevelopmentProgramTable.getSelectionModel().selectFirst();
    }

    public boolean showTypeOfDevelopmentProgramEditDialog(TypeOfDevelopmentProgram typeOfDevelopmentProgram) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TypeOfDevelopmentProgramController.class.getResource("type-of-development-program.controller.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Добавление программы развития");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            TypeOfDevelopmentProgramDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setTypeOfDevelopmentProgram(typeOfDevelopmentProgram);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private void handleNewTypeOfDevelopmentProgram() {
        TypeOfDevelopmentProgram tempType = new TypeOfDevelopmentProgram();
        boolean okClicked = showTypeOfDevelopmentProgramEditDialog(tempType);
        if (okClicked) {
            service.add(tempType.getName_p());
            loadData();
        }
    }

    @FXML
    void handleRemove() {
        int index = typesOfDevelopmentProgramTable.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            TypeOfDevelopmentProgram typeOfDevelopmentProgram = (TypeOfDevelopmentProgram) typesOfDevelopmentProgramTable.getSelectionModel().getSelectedItem();
            service.delete(typeOfDevelopmentProgram.getCode_p());
            typesOfDevelopmentProgramTable.getItems().remove(typeOfDevelopmentProgram);
        }
    }

    @FXML
    private void handleEdit() {
        TypeOfDevelopmentProgram typeOfDevelopmentProgram = (TypeOfDevelopmentProgram) typesOfDevelopmentProgramTable.getSelectionModel().getSelectedItem();
        if (typeOfDevelopmentProgram != null) {
            boolean okClicked = showTypeOfDevelopmentProgramEditDialog(typeOfDevelopmentProgram);
            if (okClicked) {
                showTypeOfDevelopmentProgramDetails(typeOfDevelopmentProgram);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Не выбран вид программы развития");
            alert.setContentText("Пожалуйста, выберите вид программы развития из таблицы!");
            alert.showAndWait();
        }
    }


    private void showTypeOfDevelopmentProgramDetails(TypeOfDevelopmentProgram typeOfDevelopmentProgram) {
        if (typeOfDevelopmentProgram != null) {
            name_pColumn.setText(typeOfDevelopmentProgram.getName_p());
        } else {
            name_pColumn.setText("");
        }
    }

    TableView<TypeOfDevelopmentProgram> getTypesOfDevelopmentProgramTable() {
        return typesOfDevelopmentProgramTable;
    }

    public void loadData() {

    }
}
