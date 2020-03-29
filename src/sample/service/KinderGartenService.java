package sample.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.dao.KinderGartenDAO;
import sample.data.KinderGarten;

import java.sql.SQLException;

public class KinderGartenService {
    static KinderGartenDAO dao = new KinderGartenDAO();

    public ObservableList<KinderGarten> getAll() throws SQLException {
        return FXCollections.observableArrayList(dao.getAll());
    }

    public void add(String stitle, String ftitle, String addr, String phone) {
        dao.insert(stitle, ftitle, addr, phone);
    }

    public void delete(String id) {
        dao.delete(id);
    }

    public void update(KinderGarten kg) {
        dao.update(kg);
    }
}
