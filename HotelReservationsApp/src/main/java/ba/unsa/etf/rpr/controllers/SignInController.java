package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.HotelException;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

/**
 * The controller class for the sign-in window of the application.
 * It handles the user input and interaction with the sign-in window, and attempts to sign the user in by
 * checking their input against the database.
 *
 * @author Hana Mahmutović
 */

public class SignInController {
    public TextField usernameField;
    public PasswordField passwordField;
    public Button signInButton;
    @FXML
    public Label badUsernameIN;
    @FXML
    public Label badPasswordIN;
    @FXML
    public Label errorLabel;

    private UserManager u = new UserManager();
    /**
     * This method is called by the JavaFX framework when the FXML file is loaded.
     * It is used to set up the controller and initialize any instance variables or UI elements.
     */
    @FXML
    public void initialize() {
        usernameField.setOnAction(this::moveToNextTextField);
        passwordField.setOnAction(this::moveToTheSignIn);
        /* I will implement this later to work fully
        passwordEyeButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            passwordField.setText(passwordField.getText());
            passwordField.setVisible(!passwordField.isVisible());
        });
        */
    }

    /**
     * This method is called when the user presses the Enter key while the focus is on the username field.
     * It sets the focus on the password field.
     * @param event an ActionEvent object that represents the action event that caused the method to be called.
     *      *                It contains information about the event, such as the source of the event and the type of event.
     *      *                In this case, the event is triggered by the user pressing the Enter key in the username field.
     */
    @FXML
    public void moveToNextTextField(ActionEvent event) {
        passwordField.requestFocus();
    }

    /**
     * This method is called when the user presses the Enter key while the focus is on the password field.
     * It enables and shows the sign-in button and fires a click event on it.
     * @param event an ActionEvent object that represents the action event that caused the method to be called.
     *                It contains information about the event, such as the source of the event and the type of event.
     *                In this case, the event is triggered by the user pressing the Enter key in the password field.
     */
    @FXML // currently not working as expected but will fix it later
    public void moveToTheSignIn(ActionEvent event){
        signInButton.setDisable(false); // enable the button
        signInButton.setVisible(true); // show the button
        signInButton.fire();
    }

    /**
     * This method is called when the user clicks the sign-in button.
     * It retrieves the user's input from the username and password fields,
     * and attempts to sign the user in by checking their input against the database.
     * If the login is successful, it transfers the user to a new window
     * or displays an error message if the login was unsuccessful.
     */
    @FXML
    private void handleLogin() throws HotelException {

        String username = usernameField.getText();
        String password = passwordField.getText();

        // Establish a connection to the database
        boolean loginSuccessful;
        User user = new User();

        // Validate the input
        if (!username.trim().isEmpty()) {
            // Display an error message
            badUsernameIN.setText("");
        }else badUsernameIN.setText("Username cannot be empty.");

        if(!password.trim().isEmpty()){
            // Display an error message
            badPasswordIN.setText("");
            loginSuccessful=true;
        }else{
            badPasswordIN.setText("Password cannot be empty");
            loginSuccessful=false;
        }

        if(loginSuccessful){
            // Check the input against the database
            user = u.findUsername(username);

            errorLabel.setAlignment(Pos.CENTER);
            if (user!=null) {
                if(Objects.equals(user.getPassword(), password)){
                    try {
                        // Load the popup box FXML file
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main/PopupBox.fxml"));
                        Parent root = loader.load();
                        // Get the controller for the popup box
                        PopupBoxController popupBoxController = loader.getController();
                        // Set the message to be displayed in the popup box
                        popupBoxController.setMessage("You have successfully logged in!");
                        // Create the scene for the stage
                        Scene scene = new Scene(root);
                        // Create the stage for the popup box
                        Stage stage = new Stage();
                        stage.initStyle(StageStyle.TRANSPARENT);
                        stage.setScene(scene);
                        stage.show();
                        // Create the timeline for the animation
                        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1.5), ev -> stage.close()));
                        timeline.play();

                        FadeTransition ft = new FadeTransition(Duration.seconds(1), root);
                        ft.setFromValue(1);
                        ft.setToValue(0);
                        ft.setOnFinished(e -> stage.close());
                        ft.play();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } }else {
                // Display an error message
                errorLabel.setText("Invalid username or password!");
                return;
            }
        }

        // Login for Admin
        if(loginSuccessful && Objects.requireNonNull(user).getRole()==1){
            // Transfer to the new window after a delay
            User finalUser = user;
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
                // Create the new window
                Stage stage = new Stage();
                stage.setTitle("Admin Panel");
                stage.getIcons().add(new Image("images/HanaAvisTransLogoBlue.png"));
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/adminPanel/AdminPanelPage.fxml"));
                    AdminPanelPageController controller = new AdminPanelPageController(finalUser);
                    stage.initStyle(StageStyle.UNDECORATED);
                    fxmlLoader.setController(controller);
                    stage.setScene(new Scene(fxmlLoader.load()));
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                stage.show();
                // Close the login window
                signInButton.getScene().getWindow().hide();
            }));
            timeline.play();
        }
        // Login for regular User
        else if(loginSuccessful){
            // Transfer to the new window after a delay
            User finalUser = user;
            System.out.println(finalUser.getFirstName());
            System.out.println(finalUser.getUsername());
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
                // Create the new window
                Stage stage = new Stage();
                stage.setTitle("Home Page");
                stage.getIcons().add(new Image("images/HanaAvisTransLogoBlue.png"));
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/homePage/HomePage.fxml"));
                    HomePageController controller = new HomePageController(finalUser);
                    fxmlLoader.setController(controller);
                    stage.setScene(new Scene(fxmlLoader.load()));
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                stage.show();
                // Close the login window
                signInButton.getScene().getWindow().hide();
            }));
            timeline.play();
        }
    }

}
