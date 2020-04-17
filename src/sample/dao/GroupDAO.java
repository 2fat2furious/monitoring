package sample.dao;

import javafx.util.StringConverter;
import sample.data.ChildGroup;
import sample.data.Group;
import sample.data.JDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GroupDAO {
    public List<Group> getAll() {
        List<Group> groups = new ArrayList<>();
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM groupKG");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                groups.add(new Group(
                        rs.getLong("id_kinder_garten"),
                        rs.getLong("id_ay"),
                        rs.getString("titleGroup")
                ));
            }
            return groups;
        } catch (SQLException e) {
            return Collections.emptyList();
        }
    }

    public List<ChildGroup> getGroupWithChildren() {
        List<ChildGroup> childGroups = new ArrayList<>();
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT gr.titlegroup, gr.id_ay, gr.id_kinder_garten, c.id_c, c.name FROM groupkg gr LEFT JOIN child c ON gr.id_kinder_garten = c.id_kinder_garten and gr.titlegroup = c.titlegroup and gr.id_ay = c.id_ay");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                childGroups.add(new ChildGroup(
                        rs.getLong("id_kinder_garten"),
                        rs.getString("titlegroup"),
                        rs.getLong("id_ay"),
                        rs.getLong("id_c"),
                        rs.getString("name"))
                );
            }
        } catch (SQLException e) {
            return Collections.emptyList();
        }

        return childGroups;
    }

    public static class GroupConverter extends StringConverter<Group> {

        @Override
        public String toString(Group group) {
            return group.getTitle();
        }

        @Override
        public Group fromString(String string) {
            return null;
        }
    }
}
