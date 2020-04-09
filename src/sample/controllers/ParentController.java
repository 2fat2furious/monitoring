package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.data.Child;
import sample.data.Parent;
import sample.service.ParentService;

import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

public class ParentController extends AbstractTabController<ChildInfoController, Child> {
    @FXML
    private TableView parentsTable;
    @FXML
    private TableColumn<Parent, String> fioColumn;
    @FXML
    private Label fioLabel;
    @FXML
    private Label dateOfBirthLabel;
    @FXML
    private Label educationLabel;
    @FXML
    private Label placeOfWorkLabel;
    @FXML
    private Label positionLabel;
    @FXML
    private Label workPhoneLabel;
    @FXML
    private GridPane detailsPanel;
    @FXML
    private Button deleteButton;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    ParentService service = new ParentService();

    @FXML
    void initialize() throws SQLException {
        fioColumn.setCellValueFactory(cell -> cell.getValue().fioProperty());
        //parentsTable.setItems(service.getAll());

        deleteButton.disableProperty().bind(parentsTable.getSelectionModel().selectedItemProperty().isNull());
        detailsPanel.visibleProperty().bind(parentsTable.getSelectionModel().selectedItemProperty().isNotNull());

        parentsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {

            if (newV != null) {
                Parent parent = (Parent) newV;

                dateOfBirthLabel.setText(parent.getDateOfBirth().toLocalDate().format(formatter));
                fioLabel.setText(parent.getName());
                educationLabel.setText(parent.getEducation());
                placeOfWorkLabel.setText(parent.getPlaceOfWork());
                positionLabel.setText(parent.getPosition());
                workPhoneLabel.setText(parent.getWorkPhone());
            }
        });

        parentsTable.getSelectionModel().selectFirst();
    }

    public boolean showParentEditDialog(Parent parent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ParentController.class.getResource("parent-edit-dialog.controller.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Добавление родителя");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ParentDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setParent(parent);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private void handleNewParent() {
        Parent p = new Parent();
        boolean okClicked = showParentEditDialog(p);
        if (okClicked) {
            service.add(p.getName(),
                    p.getEducation(),
                    p.getPlaceOfWork(),
                    p.getPosition(),
                    p.getWorkPhone(),
                    p.getDateOfBirth(),
                    parent.getSelectedChild().getId_c()
            );
            loadData();
        }
    }

    @FXML
    void handleRemove() {
        int index = parentsTable.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            Parent parent = (Parent) parentsTable.getSelectionModel().getSelectedItem();
            service.delete(Long.toString(parent.getId_p()));
            parentsTable.getItems().remove(parent);
        }
    }

    @FXML
    private void handleEditParent() {
        Parent parent = (Parent) parentsTable.getSelectionModel().getSelectedItem();
        if (parent != null) {
            boolean okClicked = showParentEditDialog(parent);
            if (okClicked) {
                showParentDetails(parent);
                loadData();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.initOwner();
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Не выбран родитель");
            alert.setContentText("Пожалуйста, выберите родителя из таблицы!");
            alert.showAndWait();
        }
    }


    private void showParentDetails(Parent parent) {
        if (parent != null) {
            fioLabel.setText(parent.getName());
            educationLabel.setText(parent.getEducation());
            placeOfWorkLabel.setText(parent.getPlaceOfWork());
            positionLabel.setText(parent.getPosition());
            workPhoneLabel.setText(parent.getWorkPhone());
            dateOfBirthLabel.setText(parent.getDateOfBirth().toLocalDate().format(formatter));
        } else {
            educationLabel.setText("");
            placeOfWorkLabel.setText("");
            positionLabel.setText("");
            workPhoneLabel.setText("");
            fioLabel.setText("");
            dateOfBirthLabel.setText("");
        }
    }

    TableView<Parent> getParentTable() {
        return parentsTable;
    }

    @Override
    public void loadData() {
        parentsTable.setItems(service.getParentsByChildId(
                parent.getSelectedChild().getId_c()
        ));
    }
}
