package Utilities.Database;

import Models.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class DivisionsDao implements DAO<Division>{
    private Division extractFromResults(ResultSet results) throws SQLException {
        return new Division(
                results.getInt("Division_ID"),
                results.getString("Division"),
                results.getInt("Country_ID")
        );
    }

    @Override
    public Optional<Division> get(int id) {
        try (Connection connection = DBConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT Division_ID, Division, Country_ID, Country FROM first_level_divisions JOIN countries on first_level_divisions.Country_ID=countries.Country_ID WHERE Division_ID=" + id);

            if(results.next()) {
                return Optional.of(extractFromResults(results));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public ObservableList<Division> getAll() {
        try (Connection connection = DBConnection.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT first_level_divisions.*, countries.Country FROM first_level_divisions JOIN countries on first_level_divisions.Country_ID=countries.Country_ID");

            ObservableList<Division> divisions = FXCollections.observableArrayList();
            while(results.next()) {
                Division division = extractFromResults(results);
                divisions.add(division);
            }

            return divisions;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return FXCollections.observableArrayList();
    }

    @Override
    public boolean insert(Division division) {
        return false;
    }

    @Override
    public boolean update(Division division) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
