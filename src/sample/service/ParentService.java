package sample.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.dao.ParentDAO;
import sample.data.Parent;

import java.sql.Date;
import java.sql.SQLException;

public class ParentService {

    static ParentDAO dao = new ParentDAO();

    public ObservableList<Parent> getAll() throws SQLException {
        return FXCollections.observableArrayList(dao.getAll());
    }

    public ObservableList<Parent> getParentsByChildId(long id) {
        return FXCollections.observableArrayList(dao.getAllByChildId(id));
    }

    public void add(String name, String education, String placeOfWork, String position, String workPhone, Date dateOfBirth) {
        dao.insert(name, education, placeOfWork, position, workPhone, dateOfBirth);
    }

    public void delete(String id) {
        dao.delete(id);
    }

    public void update(Parent p) {
        dao.update(p);
    }
}
