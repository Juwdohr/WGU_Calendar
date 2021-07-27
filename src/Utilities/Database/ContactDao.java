package Utilities.Database;

import Models.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class ContactDao implements DAO<Contact>{
    private Contact extractFromResults(ResultSet results) throws SQLException {
        return new Contact(
                results.getInt("Contact_ID"),
                results.getString("Contact_Name"),
                results.getString("Email")
        );
    }

    @Override
    public Optional<Contact> get(int id) {
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("GET * FROM contacts WHERE Contact_ID=" + id);

            if(results.next()) {
                return Optional.of(extractFromResults(results));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public ObservableList<Contact> getAll() {
        try(Connection connection = DBConnection.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM contacts");

            ObservableList<Contact> contacts = FXCollections.observableArrayList();

            while(results.next()) {
                contacts.add(extractFromResults(results));
            }

            return contacts;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return FXCollections.observableArrayList();
    }

    @Override
    public boolean insert(Contact contact) {
        return false;
    }

    @Override
    public boolean update(Contact contact) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
