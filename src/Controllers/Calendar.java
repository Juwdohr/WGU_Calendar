package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Calendar {

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

}
