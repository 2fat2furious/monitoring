package sample.controllers;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import sample.data.KinderGarten;

public class KinderGartenInfoController {
    @FXML
    KinderGartenController kinderGartenController;
    @FXML
    EducatorController educatorController;
    @FXML
    GroupTypeController groupTypeController;

    @FXML
    Tab educatorTab;
    @FXML
    Tab groupTypeTab;

    @FXML
    ObjectProperty<TableView.TableViewSelectionModel<KinderGarten>> kg;

    @FXML
    public void initialize() {
        this.kg = this.kinderGartenController.getKinderGartenTable().selectionModelProperty();
        this.initTabDisabling();
        this.initChildren();
        loadOnTabSelected(educatorTab, educatorController);
        loadOnTabSelected(groupTypeTab, groupTypeController);
    }

    public KinderGarten getSelectedKinderGarten() {
        return kg.getValue().getSelectedItem();
    }

    private void initChildren() {
        kinderGartenController.init(this);
        educatorController.init(this);
        groupTypeController.init(this);
    }

    private void initTabDisabling() {
        BooleanBinding gartenIsSelected = kinderGartenController
                .getKinderGartenTable()
                .getSelectionModel()
                .selectedItemProperty()
                .isNull();

        educatorTab.disableProperty().bind(gartenIsSelected);
        groupTypeTab.disableProperty().bind(gartenIsSelected);
    }

    private void loadOnTabSelected(Tab tab, AbstractGartenInfoController c) {
        tab.selectedProperty().addListener((o, oldV, newV) -> {
            if (newV) {
                c.loadData();
            }
        });
    }
}
