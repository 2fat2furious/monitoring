package sample.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.dao.GroupDAO;
import sample.data.Group;

import java.sql.SQLException;

public class GroupService {
    private static GroupDAO dao = new GroupDAO();

    public ObservableList<Group> getAll() {
        return FXCollections.observableArrayList(dao.getAll());
    }
}
