package sample.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.dao.GroupDAO;
import sample.data.ChildGroup;
import sample.data.Group;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GroupService {
    private static GroupDAO dao = new GroupDAO();

    public ObservableList<Group> getAll() {
        return FXCollections.observableArrayList(dao.getAll());
    }

    public Map<ChildGroup.GroupId, List<ChildGroup>> getGroupWithChildren() {
        return dao.getGroupWithChildren().stream().collect(Collectors.groupingBy(ChildGroup::getId));
    }
}
