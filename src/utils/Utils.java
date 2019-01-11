package utils;

import client.server.remote.interfaces.UserAccountHandler;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Optional;
import java.util.regex.Matcher;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class Utils {

    static UserAccountHandler userAccountHandler;

    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public static void switchWindow(Parent root) throws IOException {
        Stage login = new Stage();
        Scene scene = new Scene(root);
        login.setScene(scene);
          login.initStyle(StageStyle.UNIFIED);
        login.show();
    }

    public static boolean showRequestDialouge(String playerName) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(playerName);

        String s = playerName + "request to play with you ";

        alert.setContentText(s);

        Optional<ButtonType> result = alert.showAndWait();

        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validateEmail(String emailStr) {
        Matcher matcher = Constants.VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static boolean validateName(String emailStr) {
        Matcher matcher = Constants.VALID_Name_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static UserAccountHandler establishConnection() throws RemoteException, NotBoundException {
        if (userAccountHandler == null) {
            Registry registry = LocateRegistry.getRegistry(Constants.SERVER_IP_ADDRESS, Constants.PORT);
            userAccountHandler = (UserAccountHandler) registry.lookup(Constants.ACCOUNT_SERVICE);
        }
        return userAccountHandler;
    }

    public static String getIpAddress() throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        // System.out.println("IP Address:- " + inetAddress.getHostAddress());
        return inetAddress.getHostAddress();
    }

}
