package Utilities.Database;

import Models.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Optional;

public class AppointmentDao implements DAO<Appointment>{

    private static Appointment extractFromResults(ResultSet results) throws SQLException {
        Appointment appointment = new Appointment();

        appointment.setId(results.getInt("Appointment_ID"));
        appointment.setTitle(results.getString("Title"));
        appointment.setDescription(results.getString("Description"));
        appointment.setLocation(results.getString("Location"));
        appointment.setType(results.getString("Type"));
        appointment.setStart(results.getTimestamp("Start").toLocalDateTime());
        appointment.setEnd(results.getTimestamp("End").toLocalDateTime());
        appointment.setCreated(results.getTimestamp("Create_Date").toLocalDateTime());
        appointment.setCreatedBy(results.getString("Created_By"));
        appointment.setLastUpdate(results.getTimestamp("Last_Update").toLocalDateTime());
        appointment.setLastUpdateBy(results.getString("Last_Updated_By"));
        appointment.setCustomerId(results.getInt("Customer_ID"));
        appointment.setContactId(results.getInt("Contact_ID"));
        appointment.setUserId(results.getInt("User_ID"));

        return appointment;
    }

    @Override
    public Optional<Appointment> get(int id) {
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM appointments WHERE id=" + id);

            if(results.next()) {
                return Optional.of(extractFromResults(results));
            }

        } catch (SQLException ex) {
            //TODO: Log exception
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public ObservableList<Appointment> getAll() {
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM appointments");

            ObservableList<Appointment> appointments = FXCollections.observableArrayList();

            while(results.next()) {
                appointments.add(extractFromResults(results));
            }

            return appointments;

        } catch (SQLException ex) {
            //TODO: Log Exception;
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public ObservableList<Appointment> getAll(int userId) {
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM appointments WHERE User_ID=" + userId);

            ObservableList<Appointment> appointments = FXCollections.observableArrayList();

            while(results.next()) {
                appointments.add(extractFromResults(results));
            }

            return appointments;

        } catch (SQLException ex) {
            //TODO: Log Exception;
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean insert(Appointment appointment) {
        Connection connection = DBConnection.getConnection();

        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO appointments VALUE(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, appointment.getTitle());
            statement.setString(2, appointment.getDescription());
            statement.setString(3, appointment.getLocation());
            statement.setString(4, appointment.getType());
            statement.setString(5, appointment.getStart().toString());
            statement.setString(6, appointment.getEnd().toString());
            statement.setString(7, appointment.getCreated().toString());
            statement.setString(8, appointment.getCreatedBy());
            statement.setString(9, appointment.getLastUpdate().toString());
            statement.setString(10, appointment.getLastUpdateBy());
            statement.setString(11, String.valueOf(appointment.getCustomerId()));
            statement.setString(12, String.valueOf(appointment.getContactId()));
            statement.setString(13, String.valueOf(appointment.getUserId()));

            int result = statement.executeUpdate();

            if(result == 1) return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(Appointment appointment) {
        Connection connection = DBConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE appointments SET Title=?, Description=?, Location=?, Type=?, Start=?, End=?, Create_Date=?, Created_By=?, Last_Update=?, Last_Updated_By=?, Customer_ID=?, Contact_ID=?, User_ID=? WHERE Appointment_ID=?");
            statement.setString(1, appointment.getTitle());
            statement.setString(2, appointment.getDescription());
            statement.setString(3, appointment.getLocation());
            statement.setString(4, appointment.getType());
            statement.setString(5, appointment.getStart().toString());
            statement.setString(6, appointment.getEnd().toString());
            statement.setString(7, appointment.getCreated().toString());
            statement.setString(8, appointment.getCreatedBy());
            statement.setString(9, appointment.getLastUpdate().toString());
            statement.setString(10, appointment.getLastUpdateBy());
            statement.setString(11, String.valueOf(appointment.getCustomerId()));
            statement.setString(12, String.valueOf(appointment.getContactId()));
            statement.setString(13, String.valueOf(appointment.getUserId()));
            statement.setString(14, String.valueOf(appointment.getId()));
            int result = statement.executeUpdate();

            if(result == 1) return true;

        } catch (SQLException ex) {
            //TODO: Log SQLException
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        Connection connection = DBConnection.getConnection();

        try {
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate("DELETE FROM appointments WHERE Appointment_ID=" + id);

            if(result == 1) return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }
}
