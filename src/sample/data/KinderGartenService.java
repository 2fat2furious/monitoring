package sample.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class KinderGartenService {
    static KinderGartenDAO dao = new KinderGartenDAO();

    public ObservableList<KinderGarten> getAll() throws SQLException {
        return FXCollections.observableArrayList(dao.getAll());
    }

    public void add(String stitle, String ftitle, String addr, String phone, String fio_head) {
        dao.insert(stitle, ftitle, addr, phone, fio_head);
    }

    public void delete(String id) {
        dao.delete(id);
    }

    public void update(KinderGarten kg) {
        dao.update(kg);
    }
}
