package Utilities.Database;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static final String URL = "jdbc:mysql://wgudb.ucertify.com:3306/WJ07okc";
    public static final String USER = "U07okc";
    public static final String PASSWORD = "53689087951";

    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ex) {
            throw new RuntimeException("Error Connecting to the Database", ex);
        }
    }
}
