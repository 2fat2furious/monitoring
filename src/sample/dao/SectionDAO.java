package sample.dao;

import sample.data.JDBCConnection;
import sample.data.Section;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SectionDAO {


    public ArrayList<Section> getAll() throws SQLException {
        ArrayList<Section> sections = new ArrayList<>();
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM section");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sections.add(
                        new Section(
                                rs.getString("institutionName "),
                                rs.getString("sectionName")
                        )
                );
            }
            return sections;
        } catch (SQLException e) {
            throw e;
        }
    }

    public void delete(String institutionName, String sectionName) {
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM section WHERE institutionName  = ? AND sectionName = ?");
            ps.setString(1, institutionName);
            ps.setString(2, sectionName);
            ps.execute();
        } catch (SQLException e) {
        }
    }

    public void insert(String institutionName, String sectionName) {
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO section (institutionName,sectionName) VALUES (?,?)");
            ps.setString(1, institutionName);
            ps.setString(2, sectionName);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
