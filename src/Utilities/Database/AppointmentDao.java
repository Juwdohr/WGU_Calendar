package Utilities.Database;

import Models.Appointment;
import Models.Report;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class AppointmentDao implements DAO<Appointment>{

    private static Appointment extractFromResults(ResultSet results) throws SQLException {
        return new Appointment(
                results.getInt("Appointment_ID"),
                results.getString("Title"),
                results.getString("Description"),
                results.getString("Location"),
                results.getString("Type"),
                results.getTimestamp("Start").toLocalDateTime(),
                results.getTimestamp("End").toLocalDateTime(),
                results.getTimestamp("Create_Date").toLocalDateTime(),
                results.getString("Created_By"),
                results.getTimestamp("Last_Update").toLocalDateTime(),
                results.getString("Last_Updated_By"),
                results.getInt("Customer_ID"),
                results.getString("Customer_Name"),
                results.getInt("Contact_ID"),
                results.getString("Contact_Name"),
                results.getInt("User_ID"),
                results.getString("User_Name")
        );
    }

    @Override
    public Optional<Appointment> get(int id) {
        try(Connection connection = DBConnection.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT appointments.*, User_Name, Contact_Name, Customer_Name FROM appointments, customers, contacts, users WHERE appointments.Customer_ID=customers.Customer_ID and appointments.Contact_ID=contacts.Contact_ID and appointments.User_ID=users.User_ID and appointments.Appointments_ID=" + id);

            if(results.next()) {
                return Optional.of(extractFromResults(results));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public ObservableList<Appointment> getAll() {
        try(Connection connection = DBConnection.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT appointments.*, User_Name, Contact_Name, Customer_Name FROM appointments, customers, contacts, users WHERE appointments.Customer_ID=customers.Customer_ID and appointments.Contact_ID=contacts.Contact_ID and appointments.User_ID=users.User_ID");

            ObservableList<Appointment> appointments = FXCollections.observableArrayList();

            while(results.next()) {
                appointments.add(extractFromResults(results));
            }

            return appointments;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return FXCollections.observableArrayList();
    }

    @Override
    public boolean insert(Appointment appointment) {
        try(Connection connection = DBConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement("INSERT INTO appointments VALUE(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, appointment.getTitle());
            statement.setString(2, appointment.getDescription());
            statement.setString(3, appointment.getLocation());
            statement.setString(4, appointment.getType());
            statement.setTimestamp(5, Timestamp.valueOf(appointment.getStart()));
            statement.setTimestamp(6, Timestamp.valueOf(appointment.getEnd()));
            statement.setTimestamp(7, Timestamp.valueOf(appointment.getCreated()));
            statement.setString(8, appointment.getCreatedBy());
            statement.setTimestamp(9, Timestamp.valueOf(appointment.getLastUpdate()));
            statement.setString(10, appointment.getLastUpdateBy());
            statement.setString(11, String.valueOf(appointment.getCustomerID()));
            statement.setString(12, String.valueOf(appointment.getUserId()));
            statement.setString(13, String.valueOf(appointment.getContactId()));

            int result = statement.executeUpdate();
            ResultSet results = statement.getGeneratedKeys();

            if(result == 1 & results.next()){
                appointment.setId(results.getInt(1));
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(Appointment appointment){
        try(Connection connection = DBConnection.getConnection()) {

            PreparedStatement statement = connection.prepareStatement("UPDATE appointments SET Title=?, Description=?, Location=?, Type=?, Start=?, End=?, Create_Date=?, Created_By=?, Last_Update=?, Last_Updated_By=?, Customer_ID=?, Contact_ID=?, User_ID=? WHERE Appointment_ID=?");
            statement.setString(1, appointment.getTitle());
            statement.setString(2, appointment.getDescription());
            statement.setString(3, appointment.getLocation());
            statement.setString(4, appointment.getType());
            statement.setTimestamp(5, Timestamp.valueOf(appointment.getStart()));
            statement.setTimestamp(6, Timestamp.valueOf(appointment.getEnd()));
            statement.setTimestamp(7, Timestamp.valueOf(appointment.getCreated()));
            statement.setString(8, appointment.getCreatedBy());
            statement.setTimestamp(9, Timestamp.valueOf(appointment.getLastUpdate()));
            statement.setString(10, appointment.getLastUpdateBy());
            statement.setString(11, String.valueOf(appointment.getCustomer()));
            statement.setString(12, String.valueOf(appointment.getContact()));
            statement.setString(13, String.valueOf(appointment.getUser()));
            statement.setString(14, String.valueOf(appointment.getId()));
            int result = statement.executeUpdate();

            if(result == 1) return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        try(Connection connection = DBConnection.getConnection()) {
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate("DELETE FROM appointments WHERE Appointment_ID=" + id);

            if(result == 1) return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public boolean deleteAll(int customerId) {
        try(Connection connection = DBConnection.getConnection()) {
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate("DELETE FROM appointments WHERE Customer_ID=" + customerId);
            System.out.println(result);

            if(result > 0) return true;
        } catch(SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public ArrayList generateTypeMonthReport() {
        try(Connection connection = DBConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("Select month(start) as month, type, count(*) as total from appointments GROUP BY month, type;");

            ArrayList<Report> typeMonthReport = new ArrayList<>();

            while(results.next()) {
                Report row = new Report(results.getLong("month"), results.getString("type"), results.getLong("total"));
                typeMonthReport.add(row);
            }

            return typeMonthReport;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return new ArrayList<>();
    }

    public ArrayList generateContactYearReport() {
        try(Connection connection = DBConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("Select year(start) as year, contacts.Contact_Name as Contact, count(*) as total from appointments JOIN contacts on contacts.Contact_ID=appointments.Contact_ID GROUP BY year, contact;");

            ArrayList<Report> typeMonthReport = new ArrayList<>();

            while(results.next()) {
                Report row = new Report(results.getLong("year"), results.getString("Contact"), results.getLong("total"));
                typeMonthReport.add(row);
            }

            return typeMonthReport;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return new ArrayList<>();
    }
}
