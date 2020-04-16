package sample.controllers;

import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.data.TypeResult;
import sample.service.TypeResultService;

import java.io.IOException;
import java.sql.SQLException;

public class TypeResultController {


    @FXML
    private TableView typesResultTable;
    @FXML
    private TableColumn<TypeResult, String> nameMarkColumn;
    @FXML
    private TableColumn<TypeResult, String> descMarkColumn;

    @FXML
    private GridPane detailsPanel;
    @FXML
    private Button deleteButton;
    @FXML
    private Button editingButton;

    TypeResultService service = new TypeResultService();

    @FXML
    void initialize() throws SQLException {
        nameMarkColumn.setCellValueFactory(cell -> cell.getValue().nameMarkProperty());
        descMarkColumn.setCellValueFactory(cell -> cell.getValue().descMarkProperty());
        typesResultTable.setItems(service.getAll());

        BooleanBinding modelIsSelected = typesResultTable.getSelectionModel().selectedItemProperty().isNull();
        deleteButton.disableProperty().bind(modelIsSelected);
        editingButton.disableProperty().bind(modelIsSelected);
        detailsPanel.visibleProperty().bind(typesResultTable.getSelectionModel().selectedItemProperty().isNotNull());

        typesResultTable.getSelectionModel().selectFirst();
    }

    public boolean showTypeResultEditDialog(TypeResult typeResult) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TypeResultController.class.getResource("type-result-edit-dialog.controller.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Добавление типа результата");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            TypeResultDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setTypeResult(typeResult);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private void handleNew() {
        TypeResult tempType = new TypeResult();
        boolean okClicked = showTypeResultEditDialog(tempType);
        if (okClicked) {
            service.add(tempType.getNameMark(),
                    tempType.getDescMark());
            loadData();
        }
    }

    @FXML
    void handleRemove() {
        int index = typesResultTable.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            TypeResult typeResult = (TypeResult) typesResultTable.getSelectionModel().getSelectedItem();
            service.delete(typeResult.getId_tr());
            typesResultTable.getItems().remove(typeResult);
        }
    }

    @FXML
    private void handleEdit() {
        TypeResult typeResult = (TypeResult) typesResultTable.getSelectionModel().getSelectedItem();
        if (typeResult != null) {
            boolean okClicked = showTypeResultEditDialog(typeResult);
            if (okClicked) {
                showTypeResultDetails(typeResult);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Не выбран тип результата");
            alert.setContentText("Пожалуйста, выберите тип результата из таблицы!");
            alert.showAndWait();
        }
    }


    private void showTypeResultDetails(TypeResult typeResult) {
        if (typeResult != null) {
            nameMarkColumn.setText(typeResult.getNameMark());
            descMarkColumn.setText(typeResult.getDescMark());
        } else {
            nameMarkColumn.setText("");
            descMarkColumn.setText("");
        }
    }

    TableView<TypeResult> getTypesResultTable() {
        return typesResultTable;
    }

    public void loadData() {

    }
}
