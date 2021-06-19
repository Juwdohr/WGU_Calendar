package Controllers;

import Models.User;
import Utilities.Alerts;
import Utilities.Database.UserDaoImpl;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.PageLayout;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.time.ZoneId;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login {

    private final static Locale CURRENTLOCALE = Locale.getDefault();
    private final static Logger LOGGER = Logger.getLogger(Calendar.class.getName());
    private final static ResourceBundle MESSAGES = ResourceBundle.getBundle("Resource/MessageBundle", CURRENTLOCALE);

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
    void CloseApplication() {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void LoginUser() throws IOException {
        try {
            //TODO: Log successful login
            User user = getUser(usernameTxtfield.getText(), passwordTxtfield.getText());
            LOGGER.log(Level.INFO, user.getUsername() + " logged in successfully");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../Views/Calendar.fxml"));
            Parent newRoot = loader.load();

            Calendar controller = loader.getController();
            controller.initializeData(user);

            Stage newStage = new Stage();
            newStage.setScene(new Scene(newRoot));
            newStage.setTitle("Calendar");
            newStage.setResizable(true);
            newStage.initStyle(StageStyle.DECORATED);
            newStage.setOnCloseRequest(windowEvent -> Platform.exit());

            Stage currentStage = (Stage) loginBtn.getScene().getWindow();
            currentStage.close();

            newStage.show();
        } catch (NoSuchElementException ex) {
            LOGGER.log(Level.INFO, usernameTxtfield.getText() + " attempted to login.");
            errorMessageTxt.setText(MESSAGES.getString("LoginError"));
        }

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
        errorMessageTxt.setText("");
        programTitle.setText(MESSAGES.getString("Title"));
        usernameTxtfield.setPromptText(MESSAGES.getString("UsernamePrompt"));
        passwordTxtfield.setPromptText(MESSAGES.getString("PasswordPrompt"));
        zoneIdLbl.setText(ZoneId.systemDefault().toString());

        //Allows a user press the enter key to login to application.
        usernameTxtfield.addEventHandler(KeyEvent.KEY_PRESSED, ev-> {
            if(ev.getCode() == KeyCode.ENTER) {
                try{
                    LoginUser();
                } catch(IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        passwordTxtfield.addEventHandler(KeyEvent.KEY_PRESSED, ev-> {
            if(ev.getCode() == KeyCode.ENTER) {
                try{
                    LoginUser();
                } catch(IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        loginBtn.addEventHandler(KeyEvent.KEY_PRESSED, ev-> {
            if(ev.getCode() == KeyCode.ENTER) {
                try{
                    LoginUser();
                } catch(IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private User getUser(String username, String password) throws NoSuchElementException {
        UserDaoImpl userDao = new UserDaoImpl();
        Optional<User> user = userDao.getUserByUserNameAndPassword(username, password);

        return user.orElseThrow();
    }
}