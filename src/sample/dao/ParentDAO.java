package sample.dao;

import sample.data.JDBCConnection;
import sample.data.KinderGarten;
import sample.data.Parent;

import java.sql.*;
import java.util.ArrayList;

public class ParentDAO {
    public Parent getById(String id) {
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM parent WHERE id_p = ?");
            ps.setLong(1, Long.parseLong(id));
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return null;
            } else {
                return new Parent(
                        rs.getLong("id_p"),
                        rs.getString("name"),
                        rs.getString("education"),
                        rs.getString("placeOfWork"),
                        rs.getString("position"),
                        rs.getString("workOfBirth"),
                        rs.getDate("dateOfBirth")
                );
            }

        } catch (SQLException e) {
        }
        return null;
    }

    public ArrayList<Parent> getAll() throws SQLException {
        ArrayList<Parent> parents = new ArrayList<>();
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM parent");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                parents.add(
                        new Parent(
                                rs.getLong("id_p"),
                                rs.getString("name"),
                                rs.getString("education"),
                                rs.getString("placeOfWork"),
                                rs.getString("position"),
                                rs.getString("workPhone"),
                                rs.getDate("dateOfBirth")
                ));
            }
            return parents;
        } catch (SQLException e) {
            throw e;
        }
    }

    public void delete(String id) {
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM parent WHERE id_p = ?");
            ps.setLong(1, Long.parseLong(id));
            ps.execute();
        } catch (SQLException e) {
        }
    }

    public void insert(String name, String education, String placeOfWork, String position, String workPhone, Date dateOfBirth) {
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO parent (name, education, placeOfWork, position, workPhone, dateOfBirth) VALUES (?,?,?,?,?,?)");
            ps.setString(1, name);
            ps.setString(2, education);
            ps.setString(3, placeOfWork);
            ps.setString(4, position);
            ps.setString(5, workPhone);
            ps.setDate(6, dateOfBirth);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Parent parent) {
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE parent SET name = ?, education = ?, placeofwork = ?, position = ?, workphone = ?, dateofbirth = ? Where id_p = ?");
            ps.setString(1, parent.getName());
            ps.setString(2, parent.getEducation());
            ps.setString(3, parent.getPlaceOfWork());
            ps.setString(4, parent.getPosition());
            ps.setString(5, parent.getWorkPhone());
            ps.setDate(6, parent.getDateOfBirth());
            ps.setLong(7, parent.getId_p());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
