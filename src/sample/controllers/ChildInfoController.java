//package sample.controllers;
//
//import javafx.beans.binding.BooleanBinding;
//import javafx.beans.property.ObjectProperty;
//import javafx.fxml.FXML;
//import javafx.scene.control.Tab;
//import javafx.scene.control.TableView;
//import sample.data.Child;
//
//public class ChildInfoController {
//
//        @FXML
//        ChildController childController;
//        @FXML
//        ParentController parentController;
//        @FXML
//        SectionController sectionController;
//
//        @FXML
//        Tab parentTab;
//        @FXML
//        Tab sectionTab;
//
//        ObjectProperty<TableView.TableViewSelectionModel<Child>> child;
//
//        @FXML
//        public void initialize() {
//            this.child = this.childController.getChildTable().selectionModelProperty();
//            this.initTabDisabling();
//            this.initChildren();
//            loadOnTabSelected(parentTab, parentController);
//            loadOnTabSelected(sectionTab, sectionController);
//        }
//
//        public Child getSelectedChild() {
//            return child.getValue().getSelectedItem();
//        }
//
//        private void initChildren() {
//            childController.init(this);
//            parentController.init(this);
//            sectionController.init(this);
//        }
//
//        private void initTabDisabling() {
//            BooleanBinding childIsSelected = childController
//                    .getChildTable()
//                    .getSelectionModel()
//                    .selectedItemProperty()
//                    .isNull();
//
//            parentTab.disableProperty().bind(childIsSelected);
//            sectionTab.disableProperty().bind(childIsSelected);
//        }
//
//        private void loadOnTabSelected(Tab tab, AbstractChildInfoController c) {
//            tab.selectedProperty().addListener((o, oldV, newV) -> {
//                if (newV) {
//                    c.loadData();
//                }
//            });
//        }
//
//}
