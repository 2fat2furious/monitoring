package sample.dao;

import sample.data.JDBCConnection;
import sample.data.KinderGarten;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class KinderGartenDAO {
    public KinderGarten getById(String id) {
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM kinder_garten WHERE id_kinder_garten = ?");
            ps.setLong(1, Long.parseLong(id));
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return null;
            } else {
                return new KinderGarten(
                        rs.getLong("id_kinder_garten"),
                        rs.getString("short_title"),
                        rs.getString("full_title"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("fio")
                );
            }

        } catch (SQLException e) {
        }
        return null;
    }

    public ArrayList<KinderGarten> getAll() throws SQLException {
        ArrayList<KinderGarten> gartens = new ArrayList<>();
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM kinder_garten");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                gartens.add(
                        new KinderGarten(
                                rs.getLong("id_kinder_garten"),
                                rs.getString("short_title"),
                                rs.getString("full_title"),
                                rs.getString("address"),
                                rs.getString("phone"),
                                rs.getString("fio")
                        )
                );
            }
            return gartens;
        } catch (SQLException e) {
            throw e;
        }
    }

    public void delete(String id) {
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM kinder_garten WHERE id_kinder_garten = ?");
            ps.setLong(1, Long.parseLong(id));
            ps.execute();
        } catch (SQLException e) {
        }
    }

    public void insert(String stitle, String ftitle, String addr, String phone) {
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO kinder_garten (short_title, full_title, address, phone) VALUES (?,?,?,?)");
            ps.setString(1, stitle);
            ps.setString(2, ftitle);
            ps.setString(3, addr);
            ps.setString(4, phone);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(KinderGarten kinderGarten) {
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE kinder_garten SET short_title = ?, full_title = ?, address = ?, phone = ?, fio = ? Where id_kinder_garten = ?");
            ps.setString(1, kinderGarten.getShortTitle());
            ps.setString(2, kinderGarten.getFullTitle());
            ps.setString(3, kinderGarten.getAddress());
            ps.setString(4, kinderGarten.getPhone());
            ps.setString(5, kinderGarten.getFio());
            ps.setLong(6, kinderGarten.getIdKinderGarten());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
