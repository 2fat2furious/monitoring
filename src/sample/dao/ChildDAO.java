package sample.dao;

import sample.data.Child;
import sample.data.JDBCConnection;

import java.sql.*;
import java.util.ArrayList;

public class ChildDAO {
    public Child getById(String id) {
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM child WHERE id_c = ?");
            ps.setLong(1, Long.parseLong(id));
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return null;
            } else {
                return new Child(
                        rs.getLong("id_c"),
                        rs.getString("name"),
                        rs.getDate("dateOfBirth"),
                        rs.getString("placeOfBirth"),
                        rs.getString("homePhone"),
                        rs.getString("homeAddress"),
                        rs.getString("familyComposition"),
                        rs.getString("healthStatus")
                );
            }

        } catch (SQLException e) {
        }
        return null;
    }

    public ArrayList<Child> getAll() throws SQLException {
        ArrayList<Child> childs = new ArrayList<>();
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM child");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                childs.add(
                        new Child(
                                rs.getLong("id_c"),
                                rs.getString("name"),
                                rs.getDate("dateOfBirth"),
                                rs.getString("placeOfBirth"),
                                rs.getString("homePhone"),
                                rs.getString("homeAddress"),
                                rs.getString("familyComposition"),
                                rs.getString("healthStatus")
                        ));
            }
            return childs;
        } catch (SQLException e) {
            throw e;
        }
    }

    public void delete(String id) {
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM child WHERE id_c = ?");
            ps.setLong(1, Long.parseLong(id));
            ps.execute();
        } catch (SQLException e) {
        }
    }

    public void insert(String name, Date dateOfBirth, String placeOfBirth,
                       String homePhone, String homeAddress, String familyComposition, String healthStatus) {
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO child(name, dateOfBirth, placeOfBirth, " +
                    " homePhone, homeAddress, familyComposition, healthStatus) VALUES (?,?,?,?,?,?,?)");
                    ps.setString(1, name);
                    ps.setDate(2, dateOfBirth);
                    ps.setString(3, placeOfBirth);
                    ps.setString(4, homePhone);
                    ps.setString(5, homeAddress);
                    ps.setString(6, familyComposition);
                    ps.setString(7, healthStatus);
                    ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Child child) {
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE child SET name = ?, dateOfBirth = ?, placeOfBirth = ?, " +
                    "homePhone = ?, homeAddress = ?, familyComposition = ?, healthstatus = ? Where id_c = ?");
            ps.setString(1, child.getName());
            ps.setDate(2, child.getDateOfBirth());
            ps.setString(3, child.getPlaceOfBirth());
            ps.setString(4, child.getHomePhone());
            ps.setString(5, child.getHomeAddress());
            ps.setString(6, child.getFamilyComposition());
            ps.setString(7, child.getHealthStatus());
            ps.setBoolean(8, child.isSocialAssistance());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
