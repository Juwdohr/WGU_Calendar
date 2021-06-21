package Utilities.Database;

import Models.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Optional;

public class DivisionsDao implements DAO<Division>{
    private Division extractFromResults(ResultSet results) throws SQLException {
        Division division = new Division();

        division.setDivisionId(results.getInt("Division_ID"));
        division.setDivisionName(results.getString("Division"));
        division.setCountryId(results.getInt("Country_ID"));

        return division;
    }

    @Override
    public Optional<Division> get(int id) {
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT Division_ID, Division, Country_ID FROM first_level_divisions WHERE Division_ID=" + id);

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
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT Division_ID, Division, Country FROM first_level_divisions");

            ObservableList<Division> divisions = FXCollections.observableArrayList();
            while(results.next()) {
                Division division = extractFromResults(results);
                divisions.add(division);
            }

            return divisions;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ObservableList<Division> getAll(int userId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean insert(Division division) {
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO first_level_divisions VALUES (NULL, ?, ?)");
            statement.setString(1, division.getDivisionName());
            statement.setString(2, String.valueOf(division.getCountryId()));
            int results = statement.executeUpdate();

            if (results == 1) return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(Division division) {
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE first_level_division SET Division=?, Country_ID=? WHERE Division_ID=?");
            statement.setString(1, division.getDivisionName());
            statement.setString(2, String.valueOf(division.getCountryId()));
            statement.setString(3, String.valueOf(division.getDivisionId()));
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
            int result = statement.executeUpdate("DELETE FROM first_level_divisions WHERE Division_ID=" + id);

            if(result == 1) return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }
}
