package sample.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Properties;

public class JDBCConnection {
    //conection pool
    public static synchronized Connection getConnection() throws SQLException {
        Properties prop = new Properties();
        try {
            Class.forName("org.postgresql.Driver");
            Locale.setDefault(Locale.ENGLISH);
        } catch (ClassNotFoundException e) {
        }
        return DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/monitoring", "postgres",
                "123456");
    }
}