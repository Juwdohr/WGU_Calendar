package Controllers;

import Models.Appointment;
import Models.Customer;
import Models.User;
import Utilities.Database.CustomerDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.function.Consumer;

public class Calendar {

    private User currentUser;
    private DatePicker datePicker = new DatePicker();
    private final ObservableList<Customer> customers = FXCollections.observableArrayList();
    private final ObservableList<Appointment> appointments = FXCollections.observableArrayList();

    @FXML
    private Menu fileMenu;

    @FXML
    private Menu addMenu;

    @FXML
    private MenuItem newAppointmentMenuItem;

    @FXML
    private MenuItem newCustomerMenuItem;

    @FXML
    private MenuItem logoutMenuItem;

    @FXML
    private Menu editMenu;

    @FXML
    private MenuItem editAppointmentMenuItem;

    @FXML
    private MenuItem editCustomerMenuItem;

    @FXML
    private Menu helpMenu;

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    private Label usernameLbl;

    @FXML
    private MenuButton addMenuButton;

    @FXML
    private MenuItem addAppointmentMenuItem;

    @FXML
    private MenuItem addCustomerMenuItem;

    @FXML
    private MenuButton updateMenuButton;

    @FXML
    private MenuItem updateAppointmentMenuItem;

    @FXML
    private MenuItem updateCustomerMenuItem;

    @FXML
    private StackPane calendar = new StackPane();

    @FXML
    private ListView<Customer> customerListView;

    @FXML
    private Label loadingLbl;

    @FXML
    private Font x3;

    @FXML
    private Color x4;

    @FXML
    private Label errorLabel;

    @FXML
    void initialize() {
        assert fileMenu != null : "fx:id=\"fileMenu\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert addMenu != null : "fx:id=\"addMenu\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert newAppointmentMenuItem != null : "fx:id=\"newAppointmentMenuItem\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert newCustomerMenuItem != null : "fx:id=\"newCustomerMenuItem\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert logoutMenuItem != null : "fx:id=\"logoutMenuItem\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert editMenu != null : "fx:id=\"editMenu\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert editAppointmentMenuItem != null : "fx:id=\"editAppointmentMenuItem\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert editCustomerMenuItem != null : "fx:id=\"editCustomerMenuItem\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert helpMenu != null : "fx:id=\"helpMenu\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert aboutMenuItem != null : "fx:id=\"aboutMenuItem\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert usernameLbl != null : "fx:id=\"usernameLbl\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert addMenuButton != null : "fx:id=\"addMenuButton\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert addAppointmentMenuItem != null : "fx:id=\"addAppointmentMenuItem\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert addCustomerMenuItem != null : "fx:id=\"addCustomerMenuItem\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert updateMenuButton != null : "fx:id=\"updateMenuButton\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert updateAppointmentMenuItem != null : "fx:id=\"updateAppointmentMenuItem\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert updateCustomerMenuItem != null : "fx:id=\"updateCustomerMenuItem\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert calendar != null : "fx:id=\"calendar\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert customerListView != null : "fx:id=\"customerListView\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert loadingLbl != null : "fx:id=\"loadingLbl\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert x3 != null : "fx:id=\"x3\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert x4 != null : "fx:id=\"x4\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert errorLabel != null : "fx:id=\"errorLabel\" was not injected: check your FXML file 'Calendar.fxml'.";
        customerListView.setItems(customers);
        customerListView.setCellFactory(param -> new ListCell<>(){
            @Override
            protected void updateItem(Customer customer, boolean empty) {
                super.updateItem(customer, empty);

                if(empty || customer==null || customer.getCustomerName() == null) {
                    setText(null);
                } else {
                    setText(customer.getCustomerName());
                }
            }
        });
    }

    @FXML
    void addCustomer() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../Views/CustomerDetails.fxml"));
        Parent newRoot = loader.load();

        CustomerDetails controller = loader.getController();
        Consumer<Customer> onComplete = result -> {
            result.setLastUpdatedBy(currentUser.getUsername());
            result.setCreatedBy(currentUser.getUsername());
            result.setCustomerId(customers.size());
            customers.add(result);
        };
        controller.initializeData(null, onComplete);

        Stage newStage = new Stage();
        newStage.setScene(new Scene(newRoot));
        newStage.setTitle("Calendar: New Customer");
        newStage.setResizable(false);
        newStage.initStyle(StageStyle.DECORATED);

        newStage.show();
    }

    @FXML
    void updateCustomer() throws IOException {
        Customer customer = customerListView.getSelectionModel().getSelectedItem();
        if(customer != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../Views/CustomerDetails.fxml"));
            Parent newRoot = loader.load();

            CustomerDetails controller = loader.getController();
            Consumer<Customer> onComplete = result -> {
                result.setLastUpdatedBy(currentUser.getUsername());
                result.setCreatedBy(currentUser.getUsername());
                //TODO: update customer in Datbase.
                customers.set(customers.indexOf(customer), result);
            };

            controller.initializeData(customer, onComplete);

            Stage newStage = new Stage();
            newStage.setScene(new Scene(newRoot));
            newStage.setTitle("Calendar: New Customer");
            newStage.setResizable(false);
            newStage.initStyle(StageStyle.DECORATED);

            newStage.show();
        }
        else {
            //TODO: Alert merchant that a customer must be selected
        }
    }

    private void loadCustomers() {
        loadingLbl.setText("Loading Customers...");

        CustomerDao customerDao = new CustomerDao();
        ObservableList<Customer> customerList = customerDao.getAll();

        customers.addAll(customerList);

        loadingLbl.setText("");
    }

    //Used to pass in user, and gather other data
    public void initializeData(User user) {
        //TODO: internationalize laodingLbl
        loadingLbl.setText("Loading...");
        this.currentUser = user;
        loadCustomers();
        usernameLbl.setText(currentUser.getUsername());
        calendar.getChildren().addAll(datePicker);
        loadingLbl.setText("");
    }
}



