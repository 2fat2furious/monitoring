package sample.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.dao.ChildDAO;
import sample.data.Child;

import java.sql.Date;
import java.sql.SQLException;

public class ChildService {

    static ChildDAO dao = new ChildDAO();

    public ObservableList<Child> getAll() throws SQLException {
        return FXCollections.observableArrayList(dao.getAll());
    }

    public void add(String name, Date dateOfBirth, String placeOfBirth,
                    String homePhone, String homeAddress, String familyComposition, String healthStatus) {
        dao.insert(name, dateOfBirth, placeOfBirth, homePhone, homeAddress, familyComposition, healthStatus);
    }

    public void delete(String id) {
        dao.delete(id);
    }

    public void update(Child c) {
        dao.update(c);
    }
}
