package Controllers;

import Models.Appointment;
import Models.Contact;
import Models.Customer;
import Utilities.Alerts;
import Utilities.Database.ContactDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;

public class AppointmentDetails {

    private Appointment appointment = null;
    private Consumer<Appointment> onComplete;
    private final ObservableList<Customer> customers = FXCollections.observableArrayList();
    private final ObservableList<Contact> contacts = FXCollections.observableArrayList();
    private final ObservableList<LocalTime> startTimeList = FXCollections.observableArrayList();
    private final ObservableList<LocalTime> endTimeList = FXCollections.observableArrayList();
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");

    @FXML
    private TextField appointmentTitleTextField;

    @FXML
    private TextField appointmentLocationTextField;

    @FXML
    private TextField appointmentTypeTextField;

    @FXML
    private ComboBox<Contact> contactComboBox;

    @FXML
    private ComboBox<Customer> customerComboBox;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private ComboBox<LocalTime> startTimePicker;

    @FXML
    private ComboBox<LocalTime> endTimePicker;

    @FXML
    private TextArea appointmentDescriptionTextArea;

    @FXML
    private Button submitAppointment;

    @FXML
    private Button cancelAppointment;

    @FXML
    void initialize() {
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
        contactComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Contact item, boolean empty) {
                super.updateItem(item, empty);

                if(item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });

        customerComboBox.setItems(customers);
        customerComboBox.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Customer> call(ListView<Customer> contactListView) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(Customer item, boolean empty) {
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
        customerComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Customer item, boolean empty) {
                super.updateItem(item, empty);

                if(item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });

        startDatePicker.setValue(LocalDate.now());
        endDatePicker.setValue(LocalDate.now());

        startTimePicker.setItems(startTimeList);
        startTimePicker.setCellFactory(new Callback<>() {
            @Override
            public ListCell<LocalTime> call(ListView<LocalTime> stringListView) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(LocalTime item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null) {
                            setText(null);
                        } else {
                            setText(item.format(timeFormatter));
                        }
                    }
                };
            }
        });
        startTimePicker.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(LocalTime item, boolean empty) {
                super.updateItem(item, empty);

                if(item == null || empty) {
                    setText(null);
                } else {
                    setText(item.format(timeFormatter));
                }
            }
        });

        endTimePicker.setItems(endTimeList);
        endTimePicker.setCellFactory(new Callback<>() {
            @Override
            public ListCell<LocalTime> call(ListView<LocalTime> stringListView) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(LocalTime item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null) {
                            setText(null);
                        } else {
                            setText(item.format(timeFormatter));
                        }
                    }
                };
            }
        });
        endTimePicker.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(LocalTime item, boolean empty) {
                super.updateItem(item, empty);

                if(item == null || empty) {
                    setText(null);
                } else {
                    setText(item.format(timeFormatter));
                }
            }
        });

    }

    public void initializeData(Appointment appointment, ObservableList<Customer> customers, Consumer<Appointment> onComplete){
        this.onComplete = onComplete;
        this.customers.addAll(customers);

        loadContacts();
        loadStartEndTimes();
        setNewAppointmentTimes();
        loadAppointment(appointment);
    }

    private void loadContacts() {
        ContactDao contactDao = new ContactDao();
        contacts.addAll(contactDao.getAll());
    }

    private void loadStartEndTimes() {
        int[] quarterHours =  {0, 15, 30, 45, 0};

        for(int i = 8; i < 22; i++){
            for(int j = 0; j < 4; j++) {
                startTimeList.add(LocalDateTime.of(
                        LocalDate.now(),
                        LocalTime.of(i, quarterHours[j])
                ).atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("America/New_York")).toLocalTime());
                endTimeList.add(LocalDateTime.of(
                        LocalDate.now(),
                        LocalTime.of(i, quarterHours[j])
                ).atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("America/New_York")).toLocalTime());
            }
        }
        endTimeList.add(LocalDateTime.of(
                LocalDate.now(),
                LocalTime.of(22,0)
        ).atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("America/New_York")).toLocalTime());
    }

    private void setNewAppointmentTimes() {
        LocalTime current = LocalTime.now();
        startTimePicker.getSelectionModel().select(0);
        endTimePicker.getSelectionModel().select(1);
        for (LocalTime time: startTimeList){
            if(current.isBefore(time)){
                startTimePicker.getSelectionModel().select(time);
                endTimePicker.getSelectionModel().select(endTimeList.indexOf(time.plusMinutes(15)));
                break;
            }
        }
    }

    private void loadAppointment(Appointment appointment) {
        if(appointment == null) return;
        // Set Current Details
        appointmentTitleTextField.setText(appointment.getTitle());
        appointmentLocationTextField.setText(appointment.getDescription());
        appointmentTypeTextField.setText(appointment.getType());
        appointmentDescriptionTextArea.setText(appointment.getDescription());

        // Set Current Attendees
         contactComboBox.getSelectionModel().select(contacts.stream().filter(contact -> contact.getId() == appointment.getContactId()).findAny().orElse(null));
         customerComboBox.getSelectionModel().select(customers.stream().filter(customer -> customer.getId() == appointment.getCustomerId()).findAny().orElse(null));

        // Set current Date/Time Selectors
        // Start
        startDatePicker.setValue(appointment.getStart().toLocalDate());
        startTimePicker.getSelectionModel().select(appointment.getStart().toLocalTime());

        // End
        endDatePicker.setValue(appointment.getEnd().toLocalDate());
        endTimePicker.getSelectionModel().select(appointment.getEnd().toLocalTime());
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
        onComplete.accept(extractAppointmentDetails());

        Stage stage = (Stage) submitAppointment.getScene().getWindow();
        stage.close();
    }

    private Appointment extractAppointmentDetails() {
        if(appointment == null) appointment = new Appointment();

        // Set Appointment Details
        appointment.setTitle(appointmentTitleTextField.getText());
        appointment.setDescription(appointmentDescriptionTextArea.getText());
        appointment.setLocation(appointmentLocationTextField.getText());
        appointment.setType(appointmentTypeTextField.getText());

        // Set Appointment Times
        appointment.setStart(LocalDateTime.of(startDatePicker.getValue(), startTimePicker.getSelectionModel().getSelectedItem()));
        appointment.setEnd(LocalDateTime.of(endDatePicker.getValue(), endTimePicker.getSelectionModel().getSelectedItem()));

        // Set Attendees
        appointment.setCustomerId(customerComboBox.getSelectionModel().getSelectedItem().getId());
        appointment.setCustomer(customerComboBox.getSelectionModel().getSelectedItem().getName());
        appointment.setContactId(contactComboBox.getSelectionModel().getSelectedItem().getId());
        appointment.setContact(contactComboBox.getSelectionModel().getSelectedItem().getName());

        // Set Appointment Creation and Updated values.
        if(appointment.getCreated() == null)
            appointment.setCreated(LocalDateTime.now());
        appointment.setLastUpdate(LocalDateTime.now());

        return appointment;
    }
}
