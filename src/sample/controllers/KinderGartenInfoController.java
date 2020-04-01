package sample.controllers;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import sample.data.KinderGarten;

import java.util.Arrays;

public class KinderGartenInfoController extends AbstractTabsContainer {
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
        initChildren(Arrays.asList(kinderGartenController, educatorController, groupTypeController));
        loadOnTabSelected(educatorTab, educatorController);
        loadOnTabSelected(groupTypeTab, groupTypeController);
    }

    public KinderGarten getSelectedKinderGarten() {
        return kg.getValue().getSelectedItem();
    }

    private void initTabDisabling() {
        initDisabling(educatorTab, kinderGartenController.getKinderGartenTable());
        initDisabling(groupTypeTab, kinderGartenController.getKinderGartenTable());
    }
}
