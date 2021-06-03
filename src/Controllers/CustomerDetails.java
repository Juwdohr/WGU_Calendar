package Controllers;

import Models.Country;
import Models.Customer;
import Models.Division;
import Utilities.Database.CountryDao;
import Utilities.Database.DivisionsDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.sql.Date;
import java.time.LocalDate;
import java.util.function.Consumer;

public class CustomerDetails {

    @FXML
    private Text customerDetailsTitle;

    @FXML
    private Label customerIdLbl;

    @FXML
    private TextField customerIdTextField;

    @FXML
    private TextField customerNameTextField;

    @FXML
    private TextField customerAddressTextField;

    @FXML
    private ComboBox<Country> countryComboBox;

    @FXML
    private ComboBox<Division> stateProvinceComboBox;

    @FXML
    private TextField postalCodeTextField;

    @FXML
    private Button submitBtn;

    @FXML
    private Button cancelBtn;

    private Customer customer = null;
    private Consumer<Customer> onComplete;
    private ObservableList<Country> countries = FXCollections.observableArrayList();
    private ObservableList<Division> divisions = FXCollections.observableArrayList();

    @FXML
    void addCustomer() {
        if (customer == null)
            customer = new Customer();

        customer.setCustomerName(customerNameTextField.getText());
        customer.setAddress(customerAddressTextField.getText());
        customer.setDivisionId(stateProvinceComboBox.getValue().getDivisionId());
        customer.setPostalCode(postalCodeTextField.getText());
        customer.setCreated(Date.valueOf(LocalDate.now()));
        customer.setLastUpdated(Date.valueOf(LocalDate.now()));

        onComplete.accept(customer);

        Stage stage = (Stage) submitBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void close(ActionEvent event)  {
        //TODO: Alert for closing scene.
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {
        assert customerDetailsTitle != null : "fx:id=\"customerDetailsTitle\" was not injected: check your FXML file 'CustomerDetails.fxml'.";
        assert customerIdLbl != null : "fx:id=\"customerIdLbl\" was not injected: check your FXML file 'CustomerDetails.fxml'.";
        assert customerIdTextField != null : "fx:id=\"customerIdTextField\" was not injected: check your FXML file 'CustomerDetails.fxml'.";
        assert customerNameTextField != null : "fx:id=\"customerNameTextField\" was not injected: check your FXML file 'CustomerDetails.fxml'.";
        assert customerAddressTextField != null : "fx:id=\"customerAddressTextField\" was not injected: check your FXML file 'CustomerDetails.fxml'.";
        assert countryComboBox != null : "fx:id=\"countryComboBox\" was not injected: check your FXML file 'CustomerDetails.fxml'.";
        assert stateProvinceComboBox != null : "fx:id=\"stateProvinceComboBox\" was not injected: check your FXML file 'CustomerDetails.fxml'.";
        assert postalCodeTextField != null : "fx:id=\"postalCodeTextField\" was not injected: check your FXML file 'CustomerDetails.fxml'.";
        assert submitBtn != null : "fx:id=\"submitBtn\" was not injected: check your FXML file 'CustomerDetails.fxml'.";
        assert cancelBtn != null : "fx:id=\"cancelBtn\" was not injected: check your FXML file 'CustomerDetails.fxml'.";

        stateProvinceComboBox.setItems(divisions);
        stateProvinceComboBox.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Division> call(ListView<Division> listView) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(Division item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                        } else {
                            setText(item.getDivisionName());
                        }
                    }
                };
            }
        });
        stateProvinceComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Division item, boolean empty) {
                super.updateItem(item, empty);

                if(item != null) {
                    setText(item.getDivisionName());
                } else {
                    setText(null);
                }
            }
        });


        countryComboBox.setItems(countries);
        countryComboBox.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Country> call(ListView<Country> listView) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(Country item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                        } else {
                            setText(item.getCountryName());
                        }
                    }
                };
            }
        });
        countryComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Country item, boolean empty) {
                super.updateItem(item, empty);

                if(item != null) {
                    setText(item.getCountryName());
                } else {
                    setText(null);
                }
            }
        });
        countryComboBox.setOnAction(event -> {
            int countryId = countryComboBox.getSelectionModel().getSelectedItem().getCountryId();
            ObservableList<Division> filteredDivisions = FXCollections.observableArrayList();

            for(Division division : divisions) {
                if(division.getCountryId() == countryId) {
                    filteredDivisions.add(division);
                }
            }

            stateProvinceComboBox.setItems(filteredDivisions);
        });
    }

    public void initializeData(Customer customer, Consumer<Customer> onComplete) {
        this.customer = customer;
        this.onComplete = onComplete;

        loadCountries();
        loadDivisions();
        loadCustomer();
    }

    private void loadCountries() {
        CountryDao countryDao = new CountryDao();
        ObservableList<Country> countryList = countryDao.getAll();
        countries.addAll(countryList);
    }

    private void loadDivisions() {
        DivisionsDao divisionDao = new DivisionsDao();
        ObservableList<Division> divisionList = divisionDao.getAll();
        divisions.addAll(divisionList);
    }

    private void loadCustomer() {
        if (customer != null ) {
            customerIdTextField.setText(String.valueOf(customer.getCustomerId()));
            customerNameTextField.setText(customer.getCustomerName());
            customerAddressTextField.setText(customer.getAddress());
            postalCodeTextField.setText(customer.getPostalCode());

            for (Division division: divisions) {
                if(division.getDivisionId() != customer.getDivisionId()) continue;
                System.out.println("DivisionID: " + division.getDivisionId() + ", CustomerID: " + customer.getCustomerId());
                stateProvinceComboBox.setValue(division);

                for(Country country:countries) {
                    if (country.getCountryId() != division.getCountryId()) continue;
                    System.out.println("DivisionID: " + division.getCountryId() + ", CountryID: " + country.getCountryId());
                    countryComboBox.setValue(country);
                }
            }
        }
    }
}