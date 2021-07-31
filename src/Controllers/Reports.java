package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Reports {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<?> contactComboBox;

    @FXML
    private TableView<?> scheduleTable;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TableColumn<?, ?> titleColumn;

    @FXML
    private TableColumn<?, ?> typeColumn;

    @FXML
    private TableColumn<?, ?> descriptionColumn;

    @FXML
    private TableColumn<?, ?> startColumn;

    @FXML
    private TableColumn<?, ?> endColumn;

    @FXML
    private TableColumn<?, ?> customerColumn;

    @FXML
    private TableView<?> typeMonthReportTable;

    @FXML
    private TableColumn<?, ?> appointmentTypeColumn;

    @FXML
    private TableColumn<?, ?> typeMonthColumn;

    @FXML
    private TableColumn<?, ?> typeByMonthAmountTotalColumn;

    @FXML
    private TableView<?> contactYearReportTable;

    @FXML
    private TableColumn<?, ?> contactColumn;

    @FXML
    private TableColumn<?, ?> yearColumn;

    @FXML
    private TableColumn<?, ?> appointmentsByYearTotalColumn;

    @FXML
    void selectContactSchedule(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert contactComboBox != null : "fx:id=\"contactComboBox\" was not injected: check your FXML file 'Reports.fxml'.";
        assert scheduleTable != null : "fx:id=\"scheduleTable\" was not injected: check your FXML file 'Reports.fxml'.";
        assert idColumn != null : "fx:id=\"idColumn\" was not injected: check your FXML file 'Reports.fxml'.";
        assert titleColumn != null : "fx:id=\"titleColumn\" was not injected: check your FXML file 'Reports.fxml'.";
        assert typeColumn != null : "fx:id=\"typeColumn\" was not injected: check your FXML file 'Reports.fxml'.";
        assert descriptionColumn != null : "fx:id=\"descriptionColumn\" was not injected: check your FXML file 'Reports.fxml'.";
        assert startColumn != null : "fx:id=\"startColumn\" was not injected: check your FXML file 'Reports.fxml'.";
        assert endColumn != null : "fx:id=\"endColumn\" was not injected: check your FXML file 'Reports.fxml'.";
        assert customerColumn != null : "fx:id=\"customerColumn\" was not injected: check your FXML file 'Reports.fxml'.";
        assert typeMonthReportTable != null : "fx:id=\"typeMonthReportTable\" was not injected: check your FXML file 'Reports.fxml'.";
        assert appointmentTypeColumn != null : "fx:id=\"appointmentTypeColumn\" was not injected: check your FXML file 'Reports.fxml'.";
        assert typeMonthColumn != null : "fx:id=\"typeMonthColumn\" was not injected: check your FXML file 'Reports.fxml'.";
        assert typeByMonthAmountTotalColumn != null : "fx:id=\"typeByMonthAmountTotalColumn\" was not injected: check your FXML file 'Reports.fxml'.";
        assert contactYearReportTable != null : "fx:id=\"contactYearReportTable\" was not injected: check your FXML file 'Reports.fxml'.";
        assert contactColumn != null : "fx:id=\"contactColumn\" was not injected: check your FXML file 'Reports.fxml'.";
        assert yearColumn != null : "fx:id=\"yearColumn\" was not injected: check your FXML file 'Reports.fxml'.";
        assert appointmentsByYearTotalColumn != null : "fx:id=\"appointmentsByYearTotalColumn\" was not injected: check your FXML file 'Reports.fxml'.";

    }
}
