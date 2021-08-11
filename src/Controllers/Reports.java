package Controllers;

import Models.Appointment;
import Models.Contact;
import Models.Report;
import Utilities.Database.AppointmentDao;
import Utilities.Database.ContactDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Reports Controller
 */
public class Reports {

    @FXML
    private ComboBox<Contact> contactComboBox;

    @FXML
    private TableView<Appointment> scheduleTable;

    @FXML
    private TableColumn<Appointment, Integer> idColumn;

    @FXML
    private TableColumn<Appointment, String> titleColumn;

    @FXML
    private TableColumn<Appointment, String> typeColumn;

    @FXML
    private TableColumn<Appointment, String> descriptionColumn;

    @FXML
    private TableColumn<Appointment, LocalDateTime> startColumn;

    @FXML
    private TableColumn<Appointment, LocalDateTime> endColumn;

    @FXML
    private TableColumn<Appointment, Integer> customerColumn;

    @FXML
    private TableView<Report> typeMonthReportTable;

    @FXML
    private TableColumn<Report, String> appointmentTypeColumn;

    @FXML
    private TableColumn<Report, Month> typeMonthColumn;

    @FXML
    private TableColumn<Report, Integer> typeByMonthAmountTotalColumn;

    @FXML
    private TableView<Report> contactYearReportTable;

    @FXML
    private TableColumn<Report, String> contactColumn;

    @FXML
    private TableColumn<Report, Year> contactYearColumn;

    @FXML
    private TableColumn<Report, Integer> appointmentsByYearTotalColumn;

    /** DateTimeFormat helps format DateTime objects to MM/dd/yyyy h:mm a z (12/25/1991 10:30 AM EDT). */
    private final static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm a z");
    /** Appointments holds all appointments retrieved from Database. */
    private final ObservableList<Appointment> appointments = FXCollections.observableArrayList();
    /** Filtered Appointments holds currently displayed appointments. */
    private final FilteredList<Appointment> filteredAppointments = new FilteredList<>(appointments);
    /** Contacts holds all contacts retrieved from Database. */
    private final ObservableList<Contact> contacts = FXCollections.observableArrayList();
    /** TypeMonthReport holds all reports for the month to type report. */
    private final ObservableList<Report> typeMonthReport = FXCollections.observableArrayList();
    /** ContactYearReport holds all reports for the contact to year report */
    private final ObservableList<Report> contactYearReport = FXCollections.observableArrayList();
    /** Appointment DAO object to retrieve appointments, and reports. */
    private final AppointmentDao appointmentDao = new AppointmentDao();

    /**
     * Selects the appointments of the selected Contact to display their schedule.
     * @lambda Predicate used to quickly select the corresponding appointments to
     * the contact, using the appointments ContactID.
     */
    @FXML
    void selectContactSchedule() {
        Contact selected = contactComboBox.getSelectionModel().getSelectedItem();
        if(selected != null) filteredAppointments.setPredicate(appointment -> appointment.getContactId() == selected.getId() && appointment.getStart().toLocalDate().isEqual(LocalDate.now()));
    }

    /**
     * Initializes the Reports window.
     */
    @FXML
    void initialize() {
        initializeContacts();
        initializeAppointments();
        initializeContactComboBox();
        initializeTypeMonthReport();
        initializeContactYearReport();

        typeMonthReportTable.setItems(typeMonthReport);
        appointmentTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        typeMonthColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        typeByMonthAmountTotalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));

        contactYearReportTable.setItems(contactYearReport);
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        contactYearColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        appointmentsByYearTotalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
    }

    /**
     * Retrieves all contacts from the Database.
     */
    private void initializeContacts() {
        ContactDao contactDao = new ContactDao();
        contacts.addAll(contactDao.getAll());
    }

    /**
     * Initializes the Appointments Table.
     */
    private void initializeAppointments() {
        appointments.addAll(appointmentDao.getAll());
        scheduleTable.setItems(filteredAppointments);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        startColumn.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Appointment, LocalDateTime> call(TableColumn<Appointment, LocalDateTime> appointmentLocalDateTimeTableColumn) {
                return new TableCell<>() {
                    @Override
                    protected void updateItem(LocalDateTime item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) {
                            setText(null);
                        } else {
                            setText(item.atZone(ZoneId.systemDefault()).format(dateTimeFormat));
                        }
                    }
                };
            }
        });
        endColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        endColumn.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Appointment, LocalDateTime> call(TableColumn<Appointment, LocalDateTime> appointmentLocalDateTimeTableColumn) {
                return new TableCell<>() {
                    @Override
                    protected void updateItem(LocalDateTime item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) {
                            setText(null);
                        } else {
                            setText(item.atZone(ZoneId.systemDefault()).format(dateTimeFormat));
                        }
                    }
                };
            }
        });
        customerColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
    }

    /**
     * Initializes the Contact Combo Box.
     */
    private void initializeContactComboBox() {
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
        contactComboBox.getSelectionModel().selectFirst();
        selectContactSchedule();
    }

    /**
     * Retrieves the type to month reports.
     */
    private void initializeTypeMonthReport() {
        typeMonthReport.addAll(appointmentDao.generateTypeMonthReport());
    }

    /**
     * Retrieves the contact to year reports.
     */
    private void initializeContactYearReport() {
        contactYearReport.addAll(appointmentDao.generateContactYearReport());
    }
}
