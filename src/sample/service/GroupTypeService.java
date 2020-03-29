package sample.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.dao.GroupTypeDAO;
import sample.data.GroupType;

import java.sql.SQLException;

public class GroupTypeService {
    static GroupTypeDAO dao = new GroupTypeDAO();

    public ObservableList<GroupType> getAll() throws SQLException {
        return FXCollections.observableArrayList(dao.getAll());
    }

    public void add(String titleGroupType, int ageInitial, int ageEnd) {
        dao.insert(titleGroupType, ageInitial, ageEnd);
    }

    public void delete(long code) {
        dao.delete(code);
    }

    public void update(GroupType gt) {
        dao.update(gt);
    }

    public ObservableList<GroupType> getGroupTypesByKinderGartenId(long id) {
        return FXCollections.observableArrayList(dao.getGroupTypesByKinderGartenId(id));
    }
}

