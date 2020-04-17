package sample.controllers.group_formation;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.StringConverter;
import sample.data.ChildGroup;
import sample.service.GroupService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GroupFormationController {
    @FXML
    ComboBox<ChildGroup.GroupId> groupDropdown;
    @FXML
    ListView<ChildGroup> groupChildrenList;

    private GroupService groupService = new GroupService();

    @FXML
    public void initialize() {
        Map<ChildGroup.GroupId, List<ChildGroup>> groupMap = groupService.getGroupWithChildren();

        groupDropdown.setItems(FXCollections.observableArrayList(new ArrayList<>(groupMap.keySet())));
        StringConverter<ChildGroup.GroupId> groupConverter = new StringConverter<ChildGroup.GroupId>() {
            @Override
            public String toString(ChildGroup.GroupId object) {
                return object.getGroupName();
            }

            @Override
            public ChildGroup.GroupId fromString(String string) {
                return null;
            }
        };
        groupDropdown.setConverter(groupConverter);

        groupDropdown.valueProperty().addListener((obs, oldItem, newItem) -> {
            groupChildrenList.setItems(FXCollections.observableArrayList(groupMap.get(newItem)));
        });


    }

    private class ChildGroupCell extends ListCell<ChildGroup>
}
