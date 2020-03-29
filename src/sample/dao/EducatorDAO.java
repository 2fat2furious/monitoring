package sample.dao;

import sample.data.Educator;
import sample.data.JDBCConnection;
import sample.data.KinderGarten;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EducatorDAO {
    public Educator getByFio(String fio) {
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM educator WHERE fio = ?");
            ps.setString(1, fio);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return null;
            } else {
                return new Educator(
                        rs.getLong("idKinderGarten"),
                        rs.getString("fio"),
                        rs.getString("education"),
                        rs.getString("position"),
                        rs.getInt("workExperience"),
                        rs.getString("phone")
                );
            }

        } catch (SQLException e) {
        }
        return null;
    }

    public ArrayList<Educator> getAll() throws SQLException {
        ArrayList<Educator> educators = new ArrayList<>();
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM educator");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                educators.add(
                        new Educator(
                                rs.getLong("idKinderGarten"),
                                rs.getString("fio"),
                                rs.getString("education"),
                                rs.getString("position"),
                                rs.getInt("workExperience"),
                                rs.getString("phone")
                        )
                );
            }
            return educators;
        } catch (SQLException e) {
            throw e;
        }
    }

    public void delete(String fio) {
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM educator WHERE fio = ?");
            ps.setString(1, fio);
            ps.execute();
        } catch (SQLException e) {
        }
    }

    public void insert(String fio, long idKinderGarten, String education, String position, int workExperience, String phone) {
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO educator (fio, id_kinder_garten, education, position, workexperience, phone) VALUES (?,?,?,?,?,?)");
            ps.setString(1, fio);
            ps.setLong(2, idKinderGarten);
            ps.setString(3, education);
            ps.setString(4, position);
            ps.setInt(5, workExperience);
            ps.setString(6, phone);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Educator educator) {
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE educator SET id_kinder_garten = ?, education = ?, position = ?, workexperience = ?, phone = ? Where fio = ?");
            ps.setLong(1, educator.getIdKinderGarten());
            ps.setString(2, educator.getEducation());
            ps.setString(3, educator.getPosition());
            ps.setInt(4, educator.getWorkExperience());
            ps.setString(5, educator.getPhone());
            ps.setString(6, educator.getFio());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Educator> getEducatorsWithNameKinderGarten() {
        ArrayList<Educator> educators = new ArrayList<>();
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT id_kinder_garten, full_title, fio, education, position, workexperience, phone FROM kinder_garten JOIN educator ON kinder_garten.id_kinder_garten = educator.id_kinder_garten ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                educators.add(
                        new Educator(
                                rs.getLong("idKinderGarten"),
                                rs.getString("fio"),
                                rs.getString("education"),
                                rs.getString("position"),
                                rs.getInt("workExperience"),
                                rs.getString("phone")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return educators;
    }

    public List<Educator> getAllByKinderGartenId(long idKinderGarten) {
        ArrayList<Educator> managers = new ArrayList<>();
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM educator  WHERE id_kinder_garten = ?");
            ps.setLong(1, idKinderGarten);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                managers.add(
                        new Educator(
                                rs.getLong("id_kinder_garten"),
                                rs.getString("fio"),
                                rs.getString("education"),
                                rs.getString("position"),
                                rs.getInt("workExperience"),
                                rs.getString("phone")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return managers;
    }
}
