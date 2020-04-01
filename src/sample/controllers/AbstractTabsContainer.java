package sample.controllers;

import javafx.scene.control.Tab;
import javafx.scene.control.TableView;

import java.util.List;

public abstract class AbstractTabsContainer {
    protected void initChildren(List<AbstractTabController> tabs) {
        for (AbstractTabController tab : tabs) {
            tab.init(this);
        }
    }

    protected void loadOnTabSelected(Tab tab, TabController tabController) {
        tab.selectedProperty().addListener((o, oldV, newV) -> {
            if (newV) {
                tabController.loadData();
            }
        });
    }

    protected void initDisabling(Tab tab, TableView<?> tableView) {
        tab.disableProperty().bind(
                tableView
                        .getSelectionModel()
                        .selectedItemProperty()
                        .isNull()
        );
    }
}
