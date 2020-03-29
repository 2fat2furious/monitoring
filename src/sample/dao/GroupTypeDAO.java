package sample.dao;

import sample.data.Educator;
import sample.data.GroupType;
import sample.data.JDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupTypeDAO {
    public GroupType getById(String id) {
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM grouptype WHERE code = ?");
            ps.setLong(1, Long.parseLong(id));
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return null;
            } else {
                return new GroupType(
                        rs.getLong("code"),
                        rs.getString("titleGroupType"),
                        rs.getInt("ageInitial"),
                        rs.getInt("ageEnd")
                );
            }

        } catch (SQLException e) {
        }
        return null;
    }

    public ArrayList<GroupType> getAll() throws SQLException {
        ArrayList<GroupType> grouptypes = new ArrayList<>();
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM grouptype");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                grouptypes.add(
                        new GroupType(
                                rs.getLong("code"),
                                rs.getString("titleGroupType"),
                                rs.getInt("ageInitial"),
                                rs.getInt("ageEnd")
                        )
                );
            }
            return grouptypes;
        } catch (SQLException e) {
            throw e;
        }
    }

    public void delete(long code) {
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM grouptype WHERE code = ?");
            ps.setLong(1, code);
            ps.execute();
        } catch (SQLException e) {
        }
    }

    public void insert(String titleGroupType, int ageInitial, int ageEnd) {
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO groupType (titleGroupType, ageInitial, ageEnd) VALUES (?,?,?)");
            ps.setString(1, titleGroupType);
            ps.setInt(2, ageInitial);
            ps.setInt(3, ageEnd);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(GroupType groupType) {
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE grouptype SET titlegrouptype = ?, ageinitial = ?, ageend = ? Where code = ?");
            ps.setString(1, groupType.getTitleGroupType());
            ps.setInt(2, groupType.getAgeInitial());
            ps.setInt(3, groupType.getAgeEnd());
            ps.setLong(4, groupType.getCode());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<GroupType> getGroupTypesByKinderGartenId(long idKinderGarten){
        {
            ArrayList<GroupType> grouptypes = new ArrayList<>();
            try (Connection connection = JDBCConnection.getConnection()) {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM grouptype " +
                        "JOIN groupkg g ON grouptype.code = g.code " +
                        "JOIN kinder_garten kg ON g.id_kinder_garten = kg.id_kinder_garten " +
                        "WHERE kg.id_kinder_garten = ? ");
                ps.setLong(1, idKinderGarten);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    grouptypes.add(
                            new GroupType(
                                    rs.getLong("code"),
                                    rs.getString("titleGroupType"),
                                    rs.getInt("startAge"),
                                    rs.getInt("endAge")
                            )
                    );
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return grouptypes;
        }
    }
}
