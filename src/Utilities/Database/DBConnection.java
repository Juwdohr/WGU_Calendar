package Utilities.Database;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://wgudb.ucertify.com:3306/WJ07okc";
    private static final String USER = "U07okc";
    private static final String PASSWORD = "53689087951";
    private static Connection connection;

    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new Driver());
            if(connection == null || connection.isClosed())
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return connection;
        } catch (SQLException ex) {
            throw new RuntimeException("Error Connecting to the Database", ex);
        }
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed())
                connection.close();
        } catch (SQLException ex) {
            throw new RuntimeException("Error Closing the Connection to the Database", ex);
        }
    }
}
