package sample.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.dao.SectionDAO;
import sample.data.Section;

import java.sql.SQLException;

public class SectionService {
    static SectionDAO dao = new SectionDAO();

    public ObservableList<Section> getAll() throws SQLException {
        return FXCollections.observableArrayList(dao.getAll());
    }

    public void add(String institutionName, String sectionName) {
        dao.insert(institutionName, sectionName);
    }

    public void delete(String institutionName, String sectionName) {
        dao.delete(institutionName, sectionName);
    }

//    public void update(Section p) {
//        dao.update(p);
//    }

}

