package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.UserDaoSQLImpl;
import ba.unsa.etf.rpr.domain.User;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Objects;
import java.util.Properties;
import java.util.regex.Pattern;

public class SignUpController extends Component {

    public TextField name;
    public Label badName;
    public TextField surname;
    public TextField email;

    public TextField username;

    public PasswordField password;
    public Button signUpButton;
    public Label badSurname;
    public Label badEmail;
    public Label badUsername;
    public Label badPassword;

    public void initialize(){
        // Add an event listener to the name field
        name.textProperty().addListener((observable, oldValue, newValue) -> {
            // Clear the error message when the name field is changed
            badName.setText("");
        });
        // Add an event listener to the surname field
        surname.textProperty().addListener((observable, oldValue, newValue) -> {
            // Clear the error message when the surname field is changed
            badSurname.setText("");
        });
        // Add an event listener to the username field
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            // Clear the error message when the username field is changed
            badUsername.setText("");
        });
        // Add an event listener to the password field
        password.textProperty().addListener((observable, oldValue, newValue) -> {
            // Clear the error message when the password field is changed
            badPassword.setText("");
        });
        // Add an event listener to the email field
        email.textProperty().addListener((observable, oldValue, newValue) -> {
            // Clear the error message when the email field is changed
            badEmail.setText("");
        });
    }
}
