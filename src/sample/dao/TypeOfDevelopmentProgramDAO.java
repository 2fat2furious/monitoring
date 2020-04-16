package sample.dao;

import sample.data.JDBCConnection;
import sample.data.TypeOfDevelopmentProgram;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TypeOfDevelopmentProgramDAO {

    public TypeOfDevelopmentProgram getByCode(String code) {
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM typeOfDevelopmentProgram WHERE code_p = ?");
            ps.setLong(1, Long.parseLong(code));
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return null;
            } else {
                return new TypeOfDevelopmentProgram(
                        rs.getLong("code_p"),
                        rs.getString("name_p")
                );
            }

        } catch (SQLException e) {
        }
        return null;
    }

    public ArrayList<TypeOfDevelopmentProgram> getAll() throws SQLException {
        ArrayList<TypeOfDevelopmentProgram> typeOfDevelopmentPrograms = new ArrayList<>();
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM typeofdevelopmentprogram");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                typeOfDevelopmentPrograms.add(
                        new TypeOfDevelopmentProgram(
                                rs.getLong("code_p"),
                                rs.getString("name_p")
                        )
                );
            }
            return typeOfDevelopmentPrograms;
        } catch (SQLException e) {
            throw e;
        }
    }

    public void delete(long code_p) {
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM typeofdevelopmentprogram WHERE code_p = ?");
            ps.setLong(1, code_p);
            ps.execute();
        } catch (SQLException e) {
        }
    }

    public void insert(String name_p) {
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO typeofdevelopmentprogram (name_p) VALUES (?)");
            ps.setString(1, name_p);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(TypeOfDevelopmentProgram typeOfDevelopmentProgram) {
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE typeofdevelopmentprogram SET name_p = ? Where code_p = ?");
            ps.setString(1, typeOfDevelopmentProgram.getName_p());
            ps.setLong(2, typeOfDevelopmentProgram.getCode_p());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
