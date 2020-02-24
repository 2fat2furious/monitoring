package sample.controllers;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.When;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sample.data.KinderGarten;
import sample.data.KinderGartenService;

import java.sql.SQLException;

public class KinderGartenController {
    @FXML
    private TableView gartensTable;
    @FXML
    private TableColumn<KinderGarten, String> shortTitleColumn;
    @FXML
    private TableColumn<KinderGarten, String> fullTitleColumn;
    @FXML
    private TableColumn<KinderGarten, String> addressColumn;
    @FXML
    private TableColumn<KinderGarten, String> phoneColumn;
    @FXML
    private TableColumn<KinderGarten, String> fioManageressColumn;

    @FXML
    private TextField shortTitle;
    @FXML
    private TextField fullTitle;
    @FXML
    private TextField address;
    @FXML
    private TextField phone;
    @FXML
    private TextField fioManageress;
    @FXML
    private Button deleteButton;

    KinderGartenService service = new KinderGartenService();
    public boolean editing = false;


    @FXML
    void initialize() throws SQLException {
        shortTitleColumn.setCellValueFactory(cell -> cell.getValue().shortTitleProperty());
        fullTitleColumn.setCellValueFactory(cell -> cell.getValue().fullTitleProperty());
        addressColumn.setCellValueFactory(cell -> cell.getValue().addressProperty());
        phoneColumn.setCellValueFactory(cell -> cell.getValue().phoneProperty());
        fioManageressColumn.setCellValueFactory(cell -> cell.getValue().fioManageressProperty());
        gartensTable.setItems(service.getAll());
        gartensTable.setOnMouseClicked(e -> {
            if (this.editing) {
                this.editing = false;
                shortTitle.setText("");
                fullTitle.setText("");
                address.setText("");
                phone.setText("");
                fioManageress.setText("");

            }
        });
    }

    @FXML
    void handleSave() throws SQLException {
        if (!this.editing) {
            service.add(
                    shortTitle.getText(),
                    fullTitle.getText(),
                    address.getText(),
                    phone.getText(),
                    fioManageress.getText()
            );

            shortTitle.setText("");
            fullTitle.setText("");
            address.setText("");
            phone.setText("");
            fioManageress.setText("");
        } else {
            KinderGarten kg = (KinderGarten) gartensTable.getSelectionModel().getSelectedItem();

            service.update(new KinderGarten(kg.getIdKindergarten(), shortTitle.getText(), fullTitle.getText(), address.getText(), phone.getText(), fioManageress.getText()));
        }

        gartensTable.setItems(service.getAll());
    }

    @FXML
    void handleRemove() {
        int index = gartensTable.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            KinderGarten kg = (KinderGarten) gartensTable.getSelectionModel().getSelectedItem();
            service.delete(Long.toString(kg.getIdKindergarten()));
            gartensTable.getItems().remove(kg);
        }
    }

    public void handleEdit() {
        int index = gartensTable.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            editing = true;
            KinderGarten kg = (KinderGarten) gartensTable.getSelectionModel().getSelectedItem();
            shortTitle.setText(kg.getShortTitle());
            fullTitle.setText(kg.getFullTitle());
            address.setText(kg.getAddress());
            phone.setText(kg.getPhone());
            fioManageress.setText(kg.getFioManageress());
        }
    }
}
