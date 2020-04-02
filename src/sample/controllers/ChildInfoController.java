package sample.controllers;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import sample.data.Child;

import java.util.Arrays;

public class ChildInfoController extends AbstractTabsContainer {
    @FXML
    ChildController childController;
    @FXML
    ParentController parentController;
    @FXML
    SectionController sectionController;

    @FXML
    Tab parentTab;
    @FXML
    Tab sectionTab;

    ObjectProperty<TableView.TableViewSelectionModel<Child>> childProperty;

    @FXML
    public void initialize() {
        this.childProperty = this.childController.getChildTable().selectionModelProperty();
        this.initTabDisabling();
        initChildren(Arrays.asList(childController, parentController, sectionController));
    }

    public Child getSelectedChild() {
        return childProperty.getValue().getSelectedItem();
    }

    private void initTabDisabling() {
        initDisabling(parentTab, childController.getChildTable());
        initDisabling(sectionTab, childController.getChildTable());
    }
}
