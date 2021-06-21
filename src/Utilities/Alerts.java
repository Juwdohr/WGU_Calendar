package Utilities;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class Alerts {

    private final static Locale CURRENTLOCALE = Locale.getDefault();
    private final static ResourceBundle MESSAGES = ResourceBundle.getBundle("Resource/MessageBundle", CURRENTLOCALE);

    private static Alert alert;
    public enum ConfirmType {EXIT, CANCEL, DELETE}

    public static boolean confirmation(ConfirmType confirmType) {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        String title = "confirmTitle" + confirmType.toString();
        String message = "confirmMessage" + confirmType.toString();

        alert.setHeaderText(MESSAGES.getString(title));
        alert.setContentText(MESSAGES.getString(message));
        Optional<ButtonType> result = alert.showAndWait();

        return (result.isPresent() && result.get() == ButtonType.OK);
    }

    public static boolean customerConfirmation(String title, String message) {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(title);
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();

        return (result.isPresent() && result.get() == ButtonType.OK);
    }

    public static void error(String errorMessage) {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(MESSAGES.getString("errorTitle"));
        alert.setContentText(errorMessage);
        alert.show();
    }

    public static void information(String infoMessage) {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(infoMessage);
        alert.show();
    }
}
