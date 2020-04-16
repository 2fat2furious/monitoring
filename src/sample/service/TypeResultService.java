package sample.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.dao.TypeResultDAO;
import sample.data.TypeResult;

import java.sql.SQLException;

public class TypeResultService {


    static TypeResultDAO dao = new TypeResultDAO();

    public ObservableList<TypeResult> getAll() throws SQLException {
        return FXCollections.observableArrayList(dao.getAll());
    }

    public void add(String nameMark, String descMark) {
        dao.insert(nameMark, descMark);
    }

    public void delete(long id) {
        dao.delete(id);
    }

    public void update(TypeResult tr) {
        dao.update(tr);
    }

}
