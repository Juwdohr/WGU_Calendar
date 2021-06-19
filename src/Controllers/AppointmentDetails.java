package Controllers;

import Models.Appointment;
import Models.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.function.Consumer;

public class AppointmentDetails {

    private Appointment appointment = null;
    private Consumer<Appointment> onComplete;
    private final ObservableList<Contact> contacts = FXCollections.observableArrayList();

    @FXML
    private Label appointmentIdLabel;

    @FXML
    private TextField appointmentIdTextfField;

    @FXML
    private TextField appointmentTitleTextField;

    @FXML
    private TextField appointmentLocationTextField;

    @FXML
    private TextField appointmentTypeTextField;

    @FXML
    private ComboBox<Contact> contactComboBox;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private ComboBox<?> startTimePicker;

    @FXML
    private ComboBox<?> endTimePicker;

    @FXML
    private Label appointmentDescriptionLabel;

    @FXML
    private TextArea appointmentDescriptionTextArea;

    @FXML
    private Button submitAppointment;

    @FXML
    private Button cancelAppointment;

    @FXML
    void initialize() {
        assert appointmentIdLabel != null : "fx:id=\"appointmentIdLabel\" was not injected: check your FXML file 'AppointmentDetails.fxml'.";
        assert appointmentIdTextfField != null : "fx:id=\"appointmentIdTextfField\" was not injected: check your FXML file 'AppointmentDetails.fxml'.";
        assert appointmentTitleTextField != null : "fx:id=\"appointmentTitleTextField\" was not injected: check your FXML file 'AppointmentDetails.fxml'.";
        assert appointmentLocationTextField != null : "fx:id=\"appointmentLocationTextField\" was not injected: check your FXML file 'AppointmentDetails.fxml'.";
        assert appointmentTypeTextField != null : "fx:id=\"appointmentTypeTextField\" was not injected: check your FXML file 'AppointmentDetails.fxml'.";
        assert contactComboBox != null : "fx:id=\"contactComboBox\" was not injected: check your FXML file 'AppointmentDetails.fxml'.";
        assert startDatePicker != null : "fx:id=\"startDatePicker\" was not injected: check your FXML file 'AppointmentDetails.fxml'.";
        assert endDatePicker != null : "fx:id=\"endDatePicker\" was not injected: check your FXML file 'AppointmentDetails.fxml'.";
        assert startTimePicker != null : "fx:id=\"startTimePicker\" was not injected: check your FXML file 'AppointmentDetails.fxml'.";
        assert endTimePicker != null : "fx:id=\"startTimePicker\" was not injected: check your FXML file 'AppointmentDetails.fxml'.";
        assert appointmentDescriptionLabel != null : "fx:id=\"appointmentDescriptionLabel\" was not injected: check your FXML file 'AppointmentDetails.fxml'.";
        assert appointmentDescriptionTextArea != null : "fx:id=\"appointmentDescriptionTextArea\" was not injected: check your FXML file 'AppointmentDetails.fxml'.";
        assert submitAppointment != null : "fx:id=\"submitAppointment\" was not injected: check your FXML file 'AppointmentDetails.fxml'.";
        assert cancelAppointment != null : "fx:id=\"cancelAppointment\" was not injected: check your FXML file 'AppointmentDetails.fxml'.";

    }

    public void initializeData(Appointment appointment, Consumer<Appointment> onComplete){
        this.onComplete = onComplete;
        this.appointment = appointment;

        loadContacts();
        loadStartEndTimes();
        loadAppointments();
    }

    private final void loadContacts() {}

    private final void loadStartEndTimes() {}

    private final void loadAppointments() {
        if(appointment != null) {
            appointmentIdTextfField.setText(String.valueOf(appointment.getId()));
            appointmentTitleTextField.setText(appointment.getTitle());
            appointmentLocationTextField.setText(appointment.getLocation());
            appointmentTypeTextField.setText(appointment.getType());
            appointmentDescriptionTextArea.setText(appointment.getDescription());
        }
    }

    @FXML
    void cancel() {
        //TODO: Alerts for canceling Add/Update Appointment
        Stage stage = (Stage) cancelAppointment.getScene().getWindow();
        stage.close();
    }

    @FXML
    void submit() {
        if(appointment == null)
            appointment = new Appointment();

        //TODO: Set appointment details

        if(appointment.getCreated() == null)
            appointment.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        appointment.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));

        onComplete.accept(appointment);

        Stage stage = (Stage) submitAppointment.getScene().getWindow();
        stage.close();
    }
}
