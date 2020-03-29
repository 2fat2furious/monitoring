package sample.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.dao.AcademicYearDAO;
import sample.data.AcademicYear;

import java.sql.SQLException;

public class AcademicYearService {
    static AcademicYearDAO dao = new AcademicYearDAO();

    public ObservableList<AcademicYear> getAll() throws SQLException {
        return FXCollections.observableArrayList(dao.getAll());
    }

    public void add(int yearInitial, int yearEnd) {
        dao.insert(yearInitial, yearEnd);
    }

    public void delete(String id_ay) {
        dao.delete(id_ay);
    }

    public void update(AcademicYear ay) {
        dao.update(ay);
    }
}
