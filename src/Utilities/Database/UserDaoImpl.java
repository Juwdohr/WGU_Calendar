package Utilities.Database;

import Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import java.util.Optional;

public class UserDaoImpl implements UserDao{
    private User extractFromResults(ResultSet results) throws SQLException {
        User user = new User();

        user.setId(results.getInt("User_ID"));
        user.setUsername(results.getString("User_Name"));
        user.setPassword(results.getString("Password"));
        user.setCreated(results.getDate("Create_Date"));
        user.setCreatedBy(results.getString("Created_By"));
        user.setLastUpdated(results.getDate("Last_Update"));
        user.setLastUpdatedBy(results.getString("Last_Updated_By"));

        return user;
    }

    @Override
    public Optional<User> getUser(int id) {
        Connection connection = DBConnection.getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM users WHERE id=" + id);

            if(results.next()) {
                return Optional.of(extractFromResults(results));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public ObservableList<User> getAllUsers() {
        Connection connection = DBConnection.getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM users");

            ObservableList<User> users = FXCollections.observableArrayList();

            while(results.next()) {
                User user = extractFromResults(results);
                users.add(user);
            }

            return users;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public Optional<User> getUserByUserNameAndPassword(String username, String password) {
        Connection connection = DBConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE User_Name=? AND Password=?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet results = statement.executeQuery();

            if(results.next()) {
                return Optional.of(extractFromResults(results));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public boolean insertUser(User user) {
        Connection connection = DBConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users VALUES (NULL, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getCreated().toString());
            statement.setString(4, user.getCreatedBy());
            statement.setString(5, user.getLastUpdated().toString());
            statement.setString(6, user.getLastUpdatedBy());
            int result = statement.executeUpdate();

            if(result == 1) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        Connection connection = DBConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET User_Name=?, Password=?, Create_Date=?, Created_By=?, Last_Update=?, Last_Updated_By=? WHERE User_ID=?");
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getCreated().toString());
            statement.setString(4, user.getCreatedBy());
            statement.setString(5, user.getLastUpdated().toString());
            statement.setString(6, user.getLastUpdatedBy());
            statement.setString(7, String.valueOf(user.getId()));
            int result = statement.executeUpdate();

            if(result == 1){
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteUser(int id) {
        Connection connection = DBConnection.getConnection();

        try {
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate("DELETE FROM users WHERE User_ID=" + id);

            if(result == 1) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }
}

