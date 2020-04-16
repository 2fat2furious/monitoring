package sample.dao;

import sample.data.JDBCConnection;
import sample.data.TypeResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TypeResultDAO {


    public TypeResult getById(String id) {
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM typeresult WHERE id_tr = ?");
            ps.setLong(1, Long.parseLong(id));
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return null;
            } else {
                return new TypeResult(
                        rs.getLong("id_tr"),
                        rs.getString("nameMark"),
                        rs.getString("descMark")
                );
            }

        } catch (SQLException e) {
        }
        return null;
    }

    public ArrayList<TypeResult> getAll() throws SQLException {
        ArrayList<TypeResult> typeResults = new ArrayList<>();
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM typeresult");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                typeResults.add(
                        new TypeResult(
                                rs.getLong("id_tr"),
                                rs.getString("nameMark"),
                                rs.getString("descMark")
                        )
                );
            }
            return typeResults;
        } catch (SQLException e) {
            throw e;
        }
    }

    public void delete(long id) {
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM typeresult WHERE id_tr = ?");
            ps.setLong(1, id);
            ps.execute();
        } catch (SQLException e) {
        }
    }

    public void insert(String nameMark, String descMark) {
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO typeresult (namemark, descmark) VALUES (?,?)");
            ps.setString(1, nameMark);
            ps.setString(2, descMark);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(TypeResult typeResult) {
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE typeresult SET namemark = ?, descmark = ? Where id_tr = ?");
            ps.setString(1, typeResult.getNameMark());
            ps.setString(2, typeResult.getDescMark());
            ps.setLong(3, typeResult.getId_tr());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
