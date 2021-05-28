package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import Models.User;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Calendar {

    private User user;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem newAppointmentMenuItem;

    @FXML
    private MenuItem newCustomerMenuItem;

    @FXML
    private MenuItem logoutMenuItem;

    @FXML
    private MenuItem editAppointmentMenuItem;

    @FXML
    private MenuItem editCustomerMenuItem;

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
    private GridPane calendarGrid;

    @FXML
    private ListView<?> customerListView;

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
        assert newAppointmentMenuItem != null : "fx:id=\"newAppointmentMenuItem\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert newCustomerMenuItem != null : "fx:id=\"newCustomerMenuItem\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert logoutMenuItem != null : "fx:id=\"logoutMenuItem\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert editAppointmentMenuItem != null : "fx:id=\"editAppointmentMenuItem\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert editCustomerMenuItem != null : "fx:id=\"editCustomerMenuItem\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert aboutMenuItem != null : "fx:id=\"aboutMenuItem\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert usernameLbl != null : "fx:id=\"usernameLbl\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert addMenuButton != null : "fx:id=\"addMenuButton\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert addAppointmentMenuItem != null : "fx:id=\"addAppointmentMenuItem\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert addCustomerMenuItem != null : "fx:id=\"addCustomerMenuItem\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert updateMenuButton != null : "fx:id=\"updateMenuButton\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert updateAppointmentMenuItem != null : "fx:id=\"updateAppointmentMenuItem\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert updateCustomerMenuItem != null : "fx:id=\"updateCustomerMenuItem\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert calendarGrid != null : "fx:id=\"calendarGrid\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert customerListView != null : "fx:id=\"customerListView\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert loadingLbl != null : "fx:id=\"loadingLbl\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert x3 != null : "fx:id=\"x3\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert x4 != null : "fx:id=\"x4\" was not injected: check your FXML file 'Calendar.fxml'.";
        assert errorLabel != null : "fx:id=\"errorLabel\" was not injected: check your FXML file 'Calendar.fxml'.";
    }

    //Used to pass in user, and gather other data
    public void initializeData(User user) {
        this.user = user;
        usernameLbl.setText(this.user.getUsername());
    }
}



