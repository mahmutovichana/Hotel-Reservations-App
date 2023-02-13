package ba.unsa.etf.rpr.controllers;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * controller for the main page which opens first when the app loads
 *
 * @author Hana Mahmutovic
 */
public class MainController implements Initializable {

    @FXML
    private VBox vbox;
    private Parent fxml;
    @FXML
    private Button closeButton;
    @FXML
    private Button minimizeButton;

    /**
     * This code defines the initialize() method, which is a special method in JavaFX that is called automatically when an FXML file is loaded. It is used to initialize the controller class that is associated with the FXML file, and is often used to set up event handlers and initialize variables.
     * The initialize() method starts by creating an instance of the TranslateTransition class and setting its target to the vbox node and its duration to 1 second. It then sets the target position for the vbox node along the x-axis to be 20 times its current position, using the setToX() method. The play() method is then called to start the animation.
     * The setOnFinished() method is used to specify an action that should be performed when the animation has finished. In this case, the action is to load a new FXML file called "SignIn.fxml" and replace the current content of the vbox node with the content of the loaded FXML file.
     * The try and catch block is used to handle any potential exceptions that might be thrown while loading the FXML file. If an exception is thrown, the message "Can't load file" is printed to the console.
     * The initialize() method is annotated with the @Override annotation, which indicates that it is meant to override a method with the same signature in a superclass or interface. This is not necessary for the initialize() method to work, but it can help to prevent errors and improve readability.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(vbox.getLayoutX() * 20);
        t.play();
        t.setOnFinished((e)->{
            try{
                fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/main/SignIn.fxml")));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            }catch(IOException ex){
                ex.printStackTrace();
                System.out.println("Can't load file");
            }
        });

        minimizeButton.setOnAction(event -> {
            Stage stage = (Stage) minimizeButton.getScene().getWindow();
            stage.setIconified(true);
        });
    }

    /**
     * This code defines an event handler method for an action event in a JavaFX application. The action event is triggered when a user interacts with a GUI element, such as clicking a button.
     * The method starts by creating an instance of the TranslateTransition class and setting its target to the vbox node and its duration to 1 second. It then sets the target position for the vbox node along the x-axis to be 20 times its current position, using the setToX() method. The play() method is then called to start the animation.
     * The setOnFinished() method is used to specify an action that should be performed when the animation has finished. In this case, the action is to load a new FXML file called "SignIn.fxml" and replace the current content of the vbox node with the content of the loaded FXML file.
     * The try and catch block is used to handle any potential exceptions that might be thrown while loading the FXML file. If an exception is thrown, the message "Can't load file" is printed to the console.
     * This event handler method is annotated with the @FXML annotation, which indicates that it can be used to handle events in the GUI of the JavaFX application. It is typically called from an event handler method in the controller class of the application, which is responsible for handling user input and updating the GUI as needed.
     */
    @FXML
    private void open_signin(){
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(vbox.getLayoutX() * 20);
        t.play();
        t.setOnFinished((e) ->{
            try{
                fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/main/SignIn.fxml")));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            }catch(IOException ex){
                System.out.println("Can't load file");
            }
        });
    }

    /**
     * This code defines an event handler method for an action event in a JavaFX application. The action event is triggered when a user interacts with a GUI element, such as clicking a button.
     * The method starts by creating an instance of the TranslateTransition class and setting its target to the vbox node and its duration to 1 second. It then sets the target position for the vbox node along the x-axis to be 0, using the setToX() method. The play() method is then called to start the animation.
     * The setOnFinished() method is used to specify an action that should be performed when the animation has finished. In this case, the action is to load a new FXML file called "SignUp.fxml" and replace the current content of the vbox node with the content of the loaded FXML file.
     * The try and catch block is used to handle any potential exceptions that might be thrown while loading the FXML file. If an exception is thrown, the message "Can't load file" is printed to the console.
     * This event handler method is annotated with the @FXML annotation, which indicates that it can be used to handle events in the GUI of the JavaFX application. It is typically called from an event handler method in the controller class of the application, which is responsible for handling user input and updating the GUI as needed.
     */
    @FXML
    private void open_signup(){
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(0);
        t.play();
        t.setOnFinished((e) ->{
            try{
                fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/main/SignUp.fxml")));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            }catch(IOException ex){
                System.out.println("Can't load file");
            }
        });
    }
    @FXML
    private void closeButtonAction(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void closeButtonMouseEntered() {
        closeButton.getStyleClass().add("closeButtonStyle");
        closeButton.getStyleClass().add("closeButtonWhenHovered");
    }

    @FXML
    private void closeButtonMouseExited() {
        closeButton.getStyleClass().remove("closeButtonWhenHovered");
    }
}