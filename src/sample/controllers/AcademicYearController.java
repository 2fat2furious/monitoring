package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.data.AcademicYear;
import sample.service.AcademicYearService;

import java.sql.SQLException;

public class AcademicYearController {

    @FXML
    private TableView academicYearsTable;
    @FXML
    private TableColumn<AcademicYear, Integer> yearInitialColumn;
    @FXML
    private TableColumn<AcademicYear, Integer> yearEndColumn;

    @FXML
    private TextField yearInitial;
    @FXML
    private TextField yearEnd;

    @FXML
    private Button deleteButton;

    AcademicYearService service = new AcademicYearService();
    public boolean editing = false;


    @FXML
    void initialize() throws SQLException {
        yearInitialColumn.setCellValueFactory(cell -> cell.getValue().yearInitialProperty().asObject());
        yearEndColumn.setCellValueFactory(cell -> cell.getValue().yearEndProperty().asObject());
        academicYearsTable.setItems(service.getAll());
        academicYearsTable.setOnMouseClicked(e -> {
            if (this.editing) {
                this.editing = false;
                yearInitial.setText("");
                yearEnd.setText("");
            }
        });
    }

    @FXML
    void handleSave() throws SQLException {
        int yearInitialValue = Integer.parseInt(yearInitial.getText());
        int yearEndValue = Integer.parseInt(yearEnd.getText());

        if (!this.editing) {
            service.add(
                    yearInitialValue,
                    yearEndValue
            );
            yearInitial.setText("");
            yearEnd.setText("");
        } else {
            AcademicYear academicYear = (AcademicYear) academicYearsTable.getSelectionModel().getSelectedItem();

            service.update(new AcademicYear(academicYear.getId_ay(), yearInitialValue, yearEndValue));
        }

        loadData();
    }

    @FXML
    void handleRemove() {
        int index = academicYearsTable.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            AcademicYear academicYear = (AcademicYear) academicYearsTable.getSelectionModel().getSelectedItem();
            service.delete(Long.toString(academicYear.getId_ay()));
            academicYearsTable.getItems().remove(academicYear);
        }
    }

    public void handleEdit() {
        int index = academicYearsTable.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            editing = true;
            AcademicYear academicYear = (AcademicYear) academicYearsTable.getSelectionModel().getSelectedItem();
            yearInitial.setText(Integer.toString(academicYear.getYearInitial()));
            yearEnd.setText(Integer.toString(academicYear.getYearEnd()));
        }
    }

    public void loadData() {

    }
}
