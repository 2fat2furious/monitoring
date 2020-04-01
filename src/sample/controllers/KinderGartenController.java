package sample.controllers;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.data.KinderGarten;
import sample.service.KinderGartenService;

import java.io.IOException;
import java.sql.SQLException;

public class KinderGartenController extends AbstractTabController<KinderGartenInfoController, KinderGarten> {
    @FXML
    private TableView gartensTable;
    @FXML
    private TableColumn<KinderGarten, String> shortTitleColumn;
    @FXML
    private Label shortTitleLabel;
    @FXML
    private Label fullTitleLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label fioLabel;
    @FXML
    private GridPane detailsPanel;

    @FXML
    private Button deleteButton;
    @FXML
    private Button editingButton;

    KinderGartenService service = new KinderGartenService();

    @FXML
    void initialize() throws SQLException {
        shortTitleColumn.setCellValueFactory(cell -> cell.getValue().shortTitleProperty());
        gartensTable.setItems(service.getAll());

        BooleanBinding modelIsSelected = gartensTable.getSelectionModel().selectedItemProperty().isNull();
        deleteButton.disableProperty().bind(modelIsSelected);
        editingButton.disableProperty().bind(modelIsSelected);
        detailsPanel.visibleProperty().bind(gartensTable.getSelectionModel().selectedItemProperty().isNotNull());

        gartensTable.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {

            if (newV != null) {
                KinderGarten kg = (KinderGarten) newV;

                fullTitleLabel.setText(kg.getFullTitle());
                shortTitleLabel.setText(kg.getShortTitle());
                addressLabel.setText(kg.getAddress());
                phoneLabel.setText(kg.getPhone());
            }
        });

        gartensTable.getSelectionModel().selectFirst();
    }

    public boolean showKinderGartenEditDialog(KinderGarten kinderGarten) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(KinderGartenController.class.getResource("kinder-garten-edit-dialog.controller.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Добавление детского сада");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            KinderGartenDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setKinderGarten(kinderGarten);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private void handleNewKinderGarten() {
        KinderGarten tempKinderGarten = new KinderGarten();
        boolean okClicked = showKinderGartenEditDialog(tempKinderGarten);
        if (okClicked) {
            service.add(tempKinderGarten.getShortTitle(),
                    tempKinderGarten.getFullTitle(),
                    tempKinderGarten.getAddress(),
                    tempKinderGarten.getPhone());
            loadData();
        }
    }

    @FXML
    void handleRemove() {
        int index = gartensTable.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            KinderGarten kg = (KinderGarten) gartensTable.getSelectionModel().getSelectedItem();
            service.delete(Long.toString(kg.getIdKinderGarten()));
            gartensTable.getItems().remove(kg);
        }
    }

    @FXML
    private void handleEditKinderGarten() {
        KinderGarten kinderGarten = (KinderGarten) gartensTable.getSelectionModel().getSelectedItem();
        if (kinderGarten != null) {
            boolean okClicked = showKinderGartenEditDialog(kinderGarten);
            if (okClicked) {
                showKinderGartenDetails(kinderGarten);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.initOwner();
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Не выбран детский сад");
            alert.setContentText("Пожалуйста, выберите детский сад из таблицы!");
            alert.showAndWait();
        }
    }


    private void showKinderGartenDetails(KinderGarten kinderGarten) {
        if (kinderGarten != null) {
            shortTitleLabel.setText(kinderGarten.getShortTitle());
            fullTitleLabel.setText(kinderGarten.getFullTitle());
            addressLabel.setText(kinderGarten.getAddress());
            phoneLabel.setText(kinderGarten.getPhone());
            fioLabel.setText(kinderGarten.getFio());
        } else {
            shortTitleLabel.setText("");
            fullTitleLabel.setText("");
            addressLabel.setText("");
            phoneLabel.setText("");
            fioLabel.setText("");
        }
    }

    TableView<KinderGarten> getKinderGartenTable() {
        return gartensTable;
    }

    @Override
    public void loadData() {

    }
}
