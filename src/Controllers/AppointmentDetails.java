package Controllers;

import Models.Appointment;
import Models.Contact;
import Utilities.Alerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.function.Consumer;

public class AppointmentDetails {

    private Appointment appointment = null;
    private Consumer<Appointment> onComplete;
    private final ObservableList<Contact> contacts = FXCollections.observableArrayList();
    private final ObservableList<LocalTime> timeList = FXCollections.observableArrayList();
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");

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
    private ComboBox<LocalTime> startTimePicker;

    @FXML
    private ComboBox<LocalTime> endTimePicker;

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

        contactComboBox.setItems(contacts);
        contactComboBox.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Contact> call(ListView<Contact> contactListView) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(Contact item, boolean empty) {
                        super.updateItem(item, empty);

                        if(item == null || empty) {
                            setText(null);
                        } else {
                            setText(item.getName());
                        }
                    }
                };
            }
        });

        startDatePicker.setValue(LocalDate.now());
        endDatePicker.setValue(LocalDate.now());

        LocalTime current = LocalTime.now();
        Callback timeCallBack = new Callback<ListView<LocalTime>, ListCell<LocalTime>>() {
            @Override
            public ListCell<LocalTime> call(ListView<LocalTime> stringListView) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(LocalTime item, boolean empty) {
                        super.updateItem(item, empty);

                        if(item == null) {
                            setText(null);
                        } else {
                            setText(item.format(timeFormatter));
                        }
                    }
                };
            }
        };

        startTimePicker.setItems(timeList);
        startTimePicker.setCellFactory(timeCallBack);
        // startTimePicker.getSelectionModel().select(current.plusMinutes(15).format(timeFormatter));

        endTimePicker.setItems(timeList);
        endTimePicker.setCellFactory(timeCallBack);
        // endTimePicker.getSelectionModel().select(current.plusMinutes(15).format(timeFormatter)));
    }

    public void initializeData(Appointment appointment, Consumer<Appointment> onComplete){
        this.onComplete = onComplete;
        this.appointment = appointment;

        loadContacts();
        loadStartEndTimes();
        loadAppointments();
    }

    private final void loadContacts() {}

    private final void loadStartEndTimes() {
        int[] quarterHours =  {0, 15, 30, 45};

        for(int i = 8; i < 17; i++){
            for(int j = 0; j < 4; j++) {
                timeList.add(LocalTime.of(i, quarterHours[j]));
            }
        }
    }

    private final void loadAppointments() {
        if(appointment != null) {
            appointmentTitleTextField.setText(appointment.getTitle());
            appointmentLocationTextField.setText(appointment.getDescription());
            appointmentTypeTextField.setText(appointment.getType());
            contactComboBox.getSelectionModel().select(appointment.getContactId());

            LocalDateTime startDateTime = appointment.getStart();
            startTimePicker.setValue(startDateTime.toLocalTime());
            startDatePicker.setValue(startDateTime.toLocalDate());

            LocalDateTime endDateTime = appointment.getEnd();
            endDatePicker.setValue(endDateTime.toLocalDate());
            endTimePicker.setValue(endDateTime.toLocalTime());

            appointmentDescriptionTextArea.setText(appointment.getDescription());
        }
    }

    @FXML
    void cancel() {
        if(Alerts.confirmation(Alerts.ConfirmType.CANCEL)) {
            Stage stage = (Stage) cancelAppointment.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void submit() {
        if(appointment == null)
            appointment = new Appointment();

        appointment.setTitle(appointmentTitleTextField.getText());
        appointment.setDescription(appointmentDescriptionTextArea.getText());
        appointment.setLocation(appointmentLocationTextField.getText());
        appointment.setType(appointmentTypeTextField.getText());

        LocalDate date = startDatePicker.getValue();
        LocalTime time = startTimePicker.getSelectionModel().getSelectedItem();
        appointment.setStart(LocalDateTime.of(date, time));

        date = endDatePicker.getValue();
        time = endTimePicker.getSelectionModel().getSelectedItem();
        appointment.setEnd(LocalDateTime.of(date, time));

        // appointment.setCustomerId(customerComboBox.getSelectionModel().getSelectedItem());

        appointment.setContactId(contactComboBox.getSelectionModel().getSelectedIndex());

        if(appointment.getCreated() == null)
            appointment.setCreated(LocalDateTime.now());
        appointment.setLastUpdate(LocalDateTime.now());

        onComplete.accept(appointment);

        Stage stage = (Stage) submitAppointment.getScene().getWindow();
        stage.close();
    }
}
