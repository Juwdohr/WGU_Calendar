package Utilities.Database;

import Models.Appointment;
import Models.Country;
import com.sun.prism.shader.Solid_Color_AlphaTest_Loader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.plaf.nimbus.State;
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
        appointment.setStart(results.getTimestamp("Start"));
        appointment.setEnd(results.getTimestamp("End"));
        appointment.setCreated(results.getTimestamp("Created"));
        appointment.setCreatedBy(results.getString("Created_By"));
        appointment.setLastUpdate(results.getTimestamp("Last_Update"));
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
        return false;
    }

    @Override
    public boolean update(Appointment appointment) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
