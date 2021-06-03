package Utilities.Database;


import Models.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.lang.ref.PhantomReference;
import java.sql.*;
import java.util.Optional;

public class CustomerDao implements DAO<Customer>{

    public Customer extractFromResults(ResultSet results) throws SQLException {
        Customer customer = new Customer();

        customer.setCustomerId(results.getInt("Customer_ID"));
        customer.setCustomerName(results.getString("Customer_Name"));
        customer.setAddress(results.getString("Address"));
        customer.setDivisionId(results.getInt("Division_ID"));
        customer.setPostalCode(results.getString("Postal_Code"));
        customer.setPhone(results.getString("Phone"));
        customer.setCreated(results.getDate("Create_Date"));
        customer.setCreatedBy(results.getString("Created_By"));
        customer.setLastUpdated(results.getDate("Last_Update"));
        customer.setLastUpdatedBy(results.getString("Last_Updated_By"));

        return customer;
    }

    @Override
    public Optional<Customer> get(int id) {
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM customers WHERE Customer_ID=" + id);

            if(results.next()) {
                return Optional.of(extractFromResults(results));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public ObservableList<Customer> getAll() {
        Connection connection = DBConnection.getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM customers");

            ObservableList<Customer> customerList = FXCollections.observableArrayList();

            while(results.next()) {
                Customer customer = extractFromResults(results);
                customerList.add(customer);
            }

            return customerList;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean insert(Customer customer) {
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO customers VALUE (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, customer.getCustomerName());
            statement.setString(2, customer.getAddress());
            statement.setString(3, customer.getPostalCode());
            statement.setString(4, customer.getPhone());
            statement.setString(5, customer.getCreated().toString());
            statement.setString(6, customer.getCreatedBy());
            statement.setString(7, customer.getLastUpdated().toString());
            statement.setString(8, customer.getLastUpdatedBy());
            statement.setString(9, String.valueOf(customer.getDivisionId()));

            int result = statement.executeUpdate();

            if (result == 1) return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Customer customer) {
        Connection connection = DBConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE customers SET Customer_Name=?,  Address=?, Division_ID=?, Postal_Code=?, Phone=?, Create_Date=?, Created_By=?, Last_Update=?, Last_Updated_By=? WHERE Customer_ID=?");

            statement.setString(1, customer.getCustomerName());
            statement.setString(2, customer.getAddress());
            statement.setString(3, customer.getPostalCode());
            statement.setString(4, customer.getPhone());
            statement.setString(5, customer.getCreated().toString());
            statement.setString(6, customer.getCreatedBy());
            statement.setString(7, customer.getLastUpdated().toString());
            statement.setString(8, customer.getLastUpdatedBy());
            statement.setString(9, String.valueOf(customer.getDivisionId()));
            statement.setString(10, String.valueOf(customer.getCustomerId()));

            int result = statement.executeUpdate();

            if(result == 1) return true;

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
            int result = statement.executeUpdate("DELETE FROM customers WHERE Customer_ID=" + id);

            if(result == 1) return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }
}