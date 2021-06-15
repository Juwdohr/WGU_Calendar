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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Calendar {

    private final static Locale CURRENTLOCALE = Locale.getDefault();
    private final static ResourceBundle MESSAGES = ResourceBundle.getBundle("Resource/MessageBundle", CURRENTLOCALE);
    private final static Logger LOGGER = Logger.getLogger(Calendar.class.getName());
    private User user;
    private final ObservableList<Customer> customers = FXCollections.observableArrayList();
    private final ObservableList<Appointment> appointments = FXCollections.observableArrayList();
    private final CustomerDao customerDao = new CustomerDao();

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
    private RadioButton weekViewRadio;

    @FXML
    private ToggleGroup CalendarDuration;

    @FXML
    private RadioButton monthViewRadio;

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
    private TableView<Appointment> appointmentTableView;

    @FXML
    private TableColumn<Appointment, Integer> appointmentId;

    @FXML
    private TableColumn<Appointment, String> appointmentTitle;

    @FXML
    private TableColumn<Appointment, String> appointmentDescription;

    @FXML
    private TableColumn<Appointment, String> appointmentLocation;

    @FXML
    private TableColumn<Appointment, String> appointmentContact;

    @FXML
    private TableColumn<Appointment, String> appointmentType;

    @FXML
    private TableColumn<Appointment, Timestamp> appointmentStartDateTime;

    @FXML
    private TableColumn<Appointment, Timestamp> appointmentEndDateTime;

    @FXML
    private TableColumn<Appointment, Integer> appointmentCustomerId;

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
        LOGGER.log(Level.FINE, "Started Loading Data");

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
        assert weekViewRadio != null : "fx:id=\"weekViewRadio\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert CalendarDuration != null : "fx:id=\"CalendarDuration\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert monthViewRadio != null : "fx:id=\"monthViewRadio\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert addMenuButton != null : "fx:id=\"addMenuButton\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert addAppointmentMenuItem != null : "fx:id=\"addAppointmentMenuItem\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert addCustomerMenuItem != null : "fx:id=\"addCustomerMenuItem\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert updateMenuButton != null : "fx:id=\"updateMenuButton\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert updateAppointmentMenuItem != null : "fx:id=\"updateAppointmentMenuItem\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert updateCustomerMenuItem != null : "fx:id=\"updateCustomerMenuItem\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert appointmentId != null : "fx:id=\"appointmentId\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert appointmentTitle != null : "fx:id=\"appointmentTitle\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert appointmentDescription != null : "fx:id=\"appointmentDescription\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert appointmentLocation != null : "fx:id=\"appointmentLocation\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert appointmentContact != null : "fx:id=\"appointmentContact\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert appointmentType != null : "fx:id=\"appointmentType\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert appointmentStartDateTime != null : "fx:id=\"appointmentStartDateTime\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert appointmentEndDateTime != null : "fx:id=\"appointmentEndDateTime\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert appointmentCustomerId != null : "fx:id=\"appointmentCustomerId\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert customerListView != null : "fx:id=\"customerListView\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert loadingLbl != null : "fx:id=\"loadingLbl\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert x3 != null : "fx:id=\"x3\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert x4 != null : "fx:id=\"x4\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert errorLabel != null : "fx:id=\"errorLabel\" was not injected: check your FXML file 'Calendar.fxml'.";

        appointmentTableView.setItems(appointments);
        appointmentId.setCellValueFactory(new PropertyValueFactory<>("id"));
        appointmentTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        appointmentDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointmentLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        appointmentContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        appointmentType.setCellValueFactory(new PropertyValueFactory<>("type"));
        appointmentStartDateTime.setCellValueFactory(new PropertyValueFactory<>("start"));
        appointmentEndDateTime.setCellValueFactory(new PropertyValueFactory<>("end"));
        appointmentCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));

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
            result.setLastUpdatedBy(user.getUsername());
            result.setCreatedBy(user.getUsername());
            if(customerDao.insert(result)) {
                customers.add(result);
            }
        };
        controller.initializeData(null, onComplete);

        Stage newStage = new Stage();
        newStage.setScene(new Scene(newRoot));
        newStage.setTitle(MESSAGES.getString("Title") + " : " + MESSAGES.getString("AddCustomer"));
        newStage.setResizable(false);
        newStage.initStyle(StageStyle.DECORATED);

        newStage.show();
    }

    @FXML
    void addAppointment() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../Views/AppointmentDetails.fxml"));
        Parent newRoot = loader.load();

        AppointmentDetails controller = loader.getController();
        Consumer<Appointment> onComplete = result -> {
            result.setCreatedBy(user.getUsername());
            result.setLastUpdateBy(user.getUsername());
            //if(appointmentDao.insert(result)) {
            appointments.add(result);
            //}
        };

        controller.initializeData(null, onComplete);

        Stage newStage = new Stage();
        newStage.setScene(new Scene(newRoot));
        newStage.setTitle(MESSAGES.getString("Title") + " : " + MESSAGES.getString("AddAppointment"));
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
                result.setLastUpdatedBy(user.getUsername());
                result.setCreatedBy(user.getUsername());

                if(customerDao.update(result)){
                    customers.set(customers.indexOf(customer), result);
                }
            };

            controller.initializeData(customer, onComplete);

            Stage newStage = new Stage();
            newStage.setScene(new Scene(newRoot));
            newStage.setTitle(MESSAGES.getString("Title") + " : " + MESSAGES.getString("UpdateCustomer"));
            newStage.setResizable(false);
            newStage.initStyle(StageStyle.DECORATED);

            newStage.show();
        } else {
            //TODO: Alert user that a customer must be selected
            LOGGER.log(Level.INFO, "Customer not selected. Customer must be selected.");
        }
    }

    @FXML
    void updateAppointment() throws IOException {
        Appointment appointment = appointmentTableView.getSelectionModel().getSelectedItem();
        if(appointment != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../Views/AppointmentDetails.fxml"));
            Parent newRoot = loader.load();

            AppointmentDetails controller = loader.getController();
            Consumer<Appointment> onComplete = result -> {
                result.setCreatedBy(user.getUsername());
                result.setLastUpdateBy(user.getUsername());
                //if(appointmentDao.update(result)) {
                    appointments.set(appointments.indexOf(appointment), result);
                //}
            };

            controller.initializeData(appointment, onComplete);

            Stage newStage = new Stage();
            newStage.setScene(new Scene(newRoot));
            newStage.setTitle(MESSAGES.getString("Title") + " : " + MESSAGES.getString("UpdateAppointment"));
            newStage.setResizable(false);
            newStage.initStyle(StageStyle.DECORATED);

            newStage.show();
        } else {
            // TODO: Alert User to Select an appiontment from the table
            LOGGER.log(Level.INFO, "Appointment not selected. Appointment must be selected.");
        }
    }

    private void loadCustomers() {
        loadingLbl.setText(MESSAGES.getString("LoadCustomers"));
        LOGGER.log(Level.FINE, "Started Loading Customers");

        ObservableList<Customer> customerList = customerDao.getAll();
        customers.addAll(customerList);

        LOGGER.log(Level.FINE, "Finished Loading Customers");
    }

    private void loadAppointments() {
        loadingLbl.setText(MESSAGES.getString("LoadAppointments"));
        LOGGER.log(Level.FINE, "Started Loading Appointments");

        // TODO: Load appointments from Database
        // ObservableList<Appointment> appointmentList = appointmentDao.getAll();
        // appointments.addAll(appointmentList);

        LOGGER.log(Level.FINE, "Finished Loading Appointments");
    }

    //Used to pass in user, and gather other data
    public void initializeData(User user) {
        // TODO: internationalize laodingLbl
        this.user = user;
        usernameLbl.setText(this.user.getUsername());

        loadCustomers();
        loadAppointments();

        LOGGER.log(Level.FINE, "Finished Loading Data");
        loadingLbl.setText("");
    }
}



