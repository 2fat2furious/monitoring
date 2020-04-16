package sample.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.dao.TypeOfDevelopmentProgramDAO;
import sample.data.TypeOfDevelopmentProgram;

import java.sql.SQLException;

public class TypeOfDevelopmentProgramService {

    static TypeOfDevelopmentProgramDAO dao = new TypeOfDevelopmentProgramDAO();

    public ObservableList<TypeOfDevelopmentProgram> getAll() throws SQLException {
        return FXCollections.observableArrayList(dao.getAll());
    }

    public void add(String name_p) {
        dao.insert(name_p);
    }

    public void delete(long code) {
        dao.delete(code);
    }

    public void update(TypeOfDevelopmentProgram todp) {
        dao.update(todp);
    }

}
