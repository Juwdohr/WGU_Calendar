package Utilities.Database;

import Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import java.util.Optional;

public class UserDao implements DAO<User>{

    private User extractFromResults(ResultSet results) throws SQLException {
        return new User(results.getInt("User_ID"), results.getString("User_Name"));
    }

    public Optional<User> getUserByUserNameAndPassword(String username, String password) {
        try(Connection connection = DBConnection.getConnection()){
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
    public Optional<User> get(int id) {
        try(Connection connection = DBConnection.getConnection()){
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
    public ObservableList<User> getAll() {
        try(Connection connection = DBConnection.getConnection()){
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

        return FXCollections.observableArrayList();
    }

    @Override
    public boolean insert(User user) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}