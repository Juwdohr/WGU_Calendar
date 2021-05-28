package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class Login {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text programTitle;

    @FXML
    private Label zoneIdLbl;

    @FXML
    private TextField usernameTxtfield;

    @FXML
    private PasswordField passwordTxtfield;

    @FXML
    private Button loginBtn;

    @FXML
    private Button closeBtn;

    @FXML
    private Text errorMessageTxt;

    @FXML
    void CloseApplication(MouseEvent event) {

    }

    @FXML
    void LoginUser(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert programTitle != null : "fx:id=\"programTitle\" was not injected: check your FXML file 'Login.fxml'.";
        assert zoneIdLbl != null : "fx:id=\"zoneIdLbl\" was not injected: check your FXML file 'Login.fxml'.";
        assert usernameTxtfield != null : "fx:id=\"usernameTxtfield\" was not injected: check your FXML file 'Login.fxml'.";
        assert passwordTxtfield != null : "fx:id=\"passwordTxtfield\" was not injected: check your FXML file 'Login.fxml'.";
        assert loginBtn != null : "fx:id=\"loginBtn\" was not injected: check your FXML file 'Login.fxml'.";
        assert closeBtn != null : "fx:id=\"closeBtn\" was not injected: check your FXML file 'Login.fxml'.";
        assert errorMessageTxt != null : "fx:id=\"errorMessageTxt\" was not injected: check your FXML file 'Login.fxml'.";

    }
}
