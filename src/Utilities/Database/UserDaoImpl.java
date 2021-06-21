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

        return user;
    }

    @Override
    public Optional<User> getUser(int id) {
        Connection connection = DBConnection.getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT User_ID, User_Name FROM users WHERE id=" + id);

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
            ResultSet results = statement.executeQuery("SELECT User_ID, User_Name FROM users");

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
            PreparedStatement statement = connection.prepareStatement("SELECT User_ID, User_Name FROM users WHERE User_Name=? AND Password=?");
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
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users VALUES (NULL, ?)");
            statement.setString(1, user.getUsername());
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
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET User_Name=?, WHERE User_ID=?");
            statement.setString(1, user.getUsername());
            statement.setString(2, String.valueOf(user.getId()));
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