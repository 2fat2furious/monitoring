package sample.dao;

import sample.data.AcademicYear;
import sample.data.JDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AcademicYearDAO {

    public AcademicYear getById(String id) {
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM academic_year WHERE id_ay = ?");
            ps.setLong(1, Long.parseLong(id));
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return null;
            } else {
                return new AcademicYear(
                        rs.getLong("id_ay"),
                        rs.getInt("initial_year"),
                        rs.getInt("end_year")
                );
            }

        } catch (SQLException e) {
        }
        return null;
    }

    public ArrayList<AcademicYear> getAll() throws SQLException {
        ArrayList<AcademicYear> academicYears = new ArrayList<>();
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM academic_year");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                academicYears.add(
                        new AcademicYear(
                                rs.getLong("id_ay"),
                                rs.getInt("initial_year"),
                                rs.getInt("end_year")
                        )
                );
            }
            return academicYears;
        } catch (SQLException e) {
            throw e;
        }
    }

    public void delete(String id) {
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM academic_year WHERE id_ay = ?");
            ps.setLong(1, Long.parseLong(id));
            ps.execute();
        } catch (SQLException e) {
        }
    }

    public void insert(int yearInitial, int yearEnd) {
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO academic_year (initial_year, end_year) VALUES (?,?)");
            ps.setInt(1, yearInitial);
            ps.setInt(2, yearEnd);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(AcademicYear academicYear) {
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE academic_year SET initial_year = ?, end_year = ? Where id_ay = ?");
            ps.setInt(1, academicYear.getYearInitial());
            ps.setInt(2, academicYear.getYearEnd());
            ps.setLong(3, academicYear.getId_ay());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
