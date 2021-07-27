package Utilities.Database;

import Models.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Optional;

public class CountryDao implements DAO<Country> {
    private Country extractFromResults(ResultSet results) throws SQLException {
        return new Country(results.getInt("Country_ID"), results.getString("Country"));
    }

    @Override
    public Optional<Country> get(int id) {
        try (Connection connection = DBConnection.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT Country_ID, Country FROM countries WHERE Country_ID=" + id);

            if(results.next()) {
                return Optional.of(extractFromResults(results));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public ObservableList<Country> getAll() {
        try(Connection connection = DBConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT Country_ID, Country FROM countries");

            ObservableList<Country> countries = FXCollections.observableArrayList();

            while(results.next()) {
                Country country = extractFromResults(results);
                countries.add(country);
            }

            return countries;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return FXCollections.observableArrayList();
    }

    @Override
    public boolean insert(Country country) {
        return false;
    }

    @Override
    public boolean update(Country country) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}