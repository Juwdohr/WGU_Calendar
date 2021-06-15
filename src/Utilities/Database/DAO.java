package Utilities.Database;

import javafx.collections.ObservableList;

import java.util.Optional;

public interface DAO<T> {
    Optional<T> get(int id);
    ObservableList<T> getAll();
    ObservableList<T> getAll(int userId);
    boolean insert(T t);
    boolean update(T t);
    boolean delete(int id);
}
