package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.data.GroupType;
import sample.data.KinderGarten;
import sample.service.GroupTypeService;

import java.sql.SQLException;

public class GroupTypeController extends AbstractTabController<KinderGartenInfoController, KinderGarten> {
    @FXML
    private TableView groupTypesTable;
    @FXML
    private TableColumn<GroupType, String> groupTypeColumn;
    @FXML
    private TableColumn<GroupType, Integer> startAgeColumn;
    @FXML
    private TableColumn<GroupType, Integer> endAgeColumn;

    @FXML
    private TextField groupType;
    @FXML
    private TextField startAge;
    @FXML
    private TextField endAge;

    @FXML
    private Button deleteButton;

    @FXML
    private Label titleKinderGartenLabel;

    GroupTypeService service = new GroupTypeService();
    public boolean editing = false;


    @FXML
    void initialize() throws SQLException {
        groupTypeColumn.setCellValueFactory(cell -> cell.getValue().titleGroupTypeProperty());
        startAgeColumn.setCellValueFactory(cell -> cell.getValue().ageInitialProperty().asObject());
        endAgeColumn.setCellValueFactory(cell -> cell.getValue().ageEndProperty().asObject());
        groupTypesTable.setItems(service.getAll());
        groupTypesTable.setOnMouseClicked(e -> {
            if (this.editing) {
                this.editing = false;
                groupType.setText("");
                startAge.setText("");
                endAge.setText("");
            }
        });
    }

    @FXML
    void handleSave() throws SQLException {
        int startAgeValue = Integer.parseInt(startAge.getText());
        int endAgeValue = Integer.parseInt(endAge.getText());

        if (!this.editing) {
            service.add(
                    groupType.getText(),
                    startAgeValue,
                    endAgeValue
            );

            groupType.setText("");
            startAge.setText("");
            endAge.setText("");
        } else {
            GroupType gt = (GroupType) groupTypesTable.getSelectionModel().getSelectedItem();

            service.update(new GroupType(gt.getCode(), groupType.getText(), startAgeValue, endAgeValue));
        }

        loadData();
    }

    @FXML
    void handleRemove() {
        int index = groupTypesTable.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            GroupType gt = (GroupType) groupTypesTable.getSelectionModel().getSelectedItem();
            service.delete(gt.getCode());
            groupTypesTable.getItems().remove(gt);
        }
    }

    public void handleEdit() {
        int index = groupTypesTable.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            editing = true;
            GroupType gt = (GroupType) groupTypesTable.getSelectionModel().getSelectedItem();
            groupType.setText(gt.getTitleGroupType());
            startAge.setText(Integer.toString(gt.getAgeInitial()));
            endAge.setText(Integer.toString(gt.getAgeEnd()));
        }
    }

    @Override
    public void loadData() {
        groupTypesTable.setItems(service.getGroupTypesByKinderGartenId(parent.getSelectedKinderGarten().getIdKinderGarten()));
        titleKinderGartenLabel.setText(parent.getSelectedKinderGarten().getFullTitle());
    }
}
