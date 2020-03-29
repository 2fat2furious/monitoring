//package sample.controllers;
//
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import sample.data.Section;
//import sample.service.SectionService;
//
//import java.sql.SQLException;
//
//public class SectionController extends AbstractChildInfoController {
//
//    @FXML
//    private TableView sectionsTable;
//    @FXML
//    private TableColumn<Section, String> institutionNameColumn;
//    @FXML
//    private TableColumn<Section, String> sectionNameColumn;
//
//    @FXML
//    private TextField institutionNameField;
//    @FXML
//    private TextField sectionNameField;
//
//    @FXML
//    private Button deleteButton;
//
//    @FXML
//    private Label titleSectionLabel;
//
//    SectionService service = new SectionService();
//    public boolean editing = false;
//
//
//    @FXML
//    void initialize() throws SQLException {
//        institutionNameColumn.setCellValueFactory(cell -> cell.getValue().institutionNameProperty());
//        sectionNameColumn.setCellValueFactory(cell -> cell.getValue().sectionNameProperty());
//        sectionsTable.setItems(service.getAll());
//        sectionsTable.setOnMouseClicked(e -> {
//            if (this.editing) {
//                this.editing = false;
//                institutionNameField.setText("");
//                sectionNameField.setText("");
//            }
//        });
//    }
//
//    @FXML
//    void handleSave() throws SQLException {
//
//        if (!this.editing) {
//            service.add(
//                    institutionNameField.getText(),
//                    sectionNameField.getText()
//            );
//
//            institutionNameField.setText("");
//            sectionNameField.setText("");
//        } else {
//            Section section = (Section) sectionsTable.getSelectionModel().getSelectedItem();
////
////            service.update(new SectionController(section.getInstitutionName(), section.getSectionName()));
//        }
//
//        loadData();
//    }
//
//    @FXML
//    void handleRemove() {
//        int index = sectionsTable.getSelectionModel().getSelectedIndex();
//        if (index >= 0) {
//            Section section = (Section) sectionsTable.getSelectionModel().getSelectedItem();
//            service.delete(section.getInstitutionName(), section.getSectionName());
//            sectionsTable.getItems().remove(section);
//        }
//    }
//
////    public void handleEdit() {
////        int index = sectionsTable.getSelectionModel().getSelectedIndex();
////        if (index >= 0) {
////            editing = true;
////            Section section = (Section) sectionsTable.getSelectionModel().getSelectedItem();
////            section.setText(section.getInstitutionName());
////            startAge.setText(Integer.toString(gt.getAgeInitial()));
////            endAge.setText(Integer.toString(gt.getAgeEnd()));
////        }
////    }
//
//    @Override
//    void loadData() {
//
//    }
//}
