package Utilities.Database;

import Models.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Optional;

public class CountryDao implements DAO<Country> {
    private Country extractFromResults(ResultSet results) throws SQLException {
        Country country = new Country();

        country.setCountryId(results.getInt("Country_ID"));
        country.setCountryName(results.getString("Country"));

        return country;
    }

    @Override
    public Optional<Country> get(int id) {
        Connection connection = DBConnection.getConnection();
        try {
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
        Connection connection = DBConnection.getConnection();
        try {
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

        return null;
    }

    @Override
    public ObservableList<Country> getAll(int userId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean insert(Country country) {
        Connection connection = DBConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO countries VALUES (null, ?)");
            statement.setString(1, country.getCountryName());

            int result = statement.executeUpdate();

            if(result == 1) return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Country country) {
        Connection connection = DBConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE country SET Country=?, WHERE Country_ID=?");
            statement.setString(1, country.getCountryName());
            statement.setString(2, String.valueOf(country.getCountryId()));
            int result = statement.executeUpdate();

            if (result == 1) return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate("DELETE FROM countries WHERE Country_ID=" + id);

            if(result == 1) return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }
}