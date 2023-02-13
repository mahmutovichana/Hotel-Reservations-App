package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * The type Account admin page controller.
 */
public class AccountAdminPageController {
    /**
     * The Home button.
     */
    @FXML
    public Button homeButton;
    /**
     * The Close button.
     */
    @FXML
    public Button closeButton;

    /**
     * The Log out button.
     */
    @FXML
    public ImageView logOutButton;
    /**
     * The Name field.
     */
    @FXML
    public Label nameField = new Label();
    /**
     * The Surname field.
     */
    @FXML
    public Label surnameField = new Label();
    /**
     * The Username field.
     */
    @FXML
    public Label usernameField = new Label();
    /**
     * The Email field.
     */
    @FXML
    public Label emailField = new Label();

    private User user;

    /**
     * Instantiates a new Account admin page controller.
     *
     * @param u the u
     */
    public AccountAdminPageController(User u){
        this.user = u;
    }

    /**
     * Instantiates a new Account admin page controller.
     */
    public AccountAdminPageController(){}

    @FXML
    private void closeButtonAction(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    @FXML
    private void closeButtonMouseExited(MouseEvent mouseEvent){ closeButton.getStyleClass().remove("closeButtonWhenHovered"); }
    @FXML
    private void closeButtonMouseEntered(MouseEvent mouseEvent) {
        closeButton.getStyleClass().add("closeButtonStyle");
        closeButton.getStyleClass().add("closeButtonWhenHovered");
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    @FXML
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    @FXML
    public User getUser() {
        return user;
    }

    /**
     * Initialize.
     */
    public void initialize() {

        nameField.setText(user.getFirstName());
        surnameField.setText(user.getLastName());
        emailField.setText(user.getEmail());
        usernameField.setText(user.getUsername());

        logOutButton.setOnMouseClicked(this::logOut);
        homeButton.setOnMouseClicked(this::handleHome);

        closeButton.setOnAction(this::closeButtonAction);
        closeButton.setOnMouseEntered(this::closeButtonMouseEntered);
        closeButton.setOnMouseExited(this::closeButtonMouseExited);
    }

    /**
     * Handle home.
     *
     * @param mouseEvent the mouse event
     */
    public void handleHome(MouseEvent mouseEvent) {
        // Close the current window
        Stage stage = (Stage) homeButton.getScene().getWindow();
        stage.close();
        // Open the home page window
        try {
            Stage myProfileStage = new Stage();
            myProfileStage.getIcons().add(new Image("images/HanaAvisTransLogoBlue.png"));
            myProfileStage.initStyle(StageStyle.TRANSPARENT);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/adminPanel/AdminPanelPage.fxml"));
            AdminPanelPageController controller = new AdminPanelPageController();
            controller.setUser(user);
            fxmlLoader.setController(controller);
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            myProfileStage.setScene(scene);
            myProfileStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void logOut(MouseEvent event) {
        // Close the current window
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        stage.close();
        // Open the login window
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/main/Main.fxml"));
            Parent root = fxmlLoader.load();
            Stage loginStage = new Stage();
            loginStage.getIcons().add(new Image("images/HanaAvisTransLogoBlue.png"));
            loginStage.setScene(new Scene(root, Color.TRANSPARENT));
            loginStage.initStyle(StageStyle.TRANSPARENT);
            loginStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
