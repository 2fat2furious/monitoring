package sample.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.dao.EducatorDAO;
import sample.data.Educator;

import java.sql.SQLException;

public class EducatorService {
    static EducatorDAO dao = new EducatorDAO();

    public ObservableList<Educator> getAll() throws SQLException {
        return FXCollections.observableArrayList(dao.getAll());
    }

    public ObservableList<Educator> getEducatorsByKGId(long id) throws SQLException {
        return FXCollections.observableArrayList(dao.getAllByKinderGartenId(id));
    }

    public void add(String fio, long idKinderGarten, String education, String position, int workExperience, String phone) {
        dao.insert(fio, idKinderGarten, education, position, workExperience, phone);
    }

    public void delete(String fio) {
        dao.delete(fio);
    }

    public void update(Educator ed) {
        dao.update(ed);
    }


}
