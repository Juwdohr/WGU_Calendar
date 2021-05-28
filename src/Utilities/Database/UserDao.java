package Utilities.Database;

import Models.User;
import javafx.collections.ObservableList;

import java.util.Observable;
import java.util.Optional;

public interface UserDao {
    Optional<User> getUser(int id);
    ObservableList<User> getAllUsers();
    Optional<User> getUserByUserNameAndPassword(String username, String password);
    boolean insertUser(User user);
    boolean updateUser(User User);
    boolean deleteUser(int id);
}
