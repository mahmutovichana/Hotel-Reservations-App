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

public class AccountAdminPageController {
    @FXML
    public Button homeButton;
    @FXML
    public Button closeButton;

    @FXML
    public ImageView logOutButton;
    @FXML
    public Label nameField = new Label();
    @FXML
    public Label surnameField = new Label();
    @FXML
    public Label usernameField = new Label();
    @FXML
    public Label emailField = new Label();
    private User user;

    public AccountAdminPageController(User u){
        this.user = u;
    }

    @FXML
    private void closeButtonAction(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    @FXML
    private void closeButtonMouseExited(MouseEvent mouseEvent) {
        closeButton.getStyleClass().remove("closeButtonWhenHovered");
    }
    @FXML
    private void closeButtonMouseEntered(MouseEvent mouseEvent) {
        closeButton.getStyleClass().add("closeButtonStyle");
        closeButton.getStyleClass().add("closeButtonWhenHovered");
    }

    @FXML
    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    public User getUser() {
        return user;
    }

    public void initialize() {

        nameField.setText(user.getFirstName());
        surnameField.setText(user.getLastName());
        emailField.setText(user.getEmail());
        usernameField.setText(user.getUsername());

        logOutButton.setOnMouseClicked(event -> logOut());
        homeButton.setOnMouseClicked(event -> handleHome());

        closeButton.setOnAction(this::closeButtonAction);
        closeButton.setOnMouseEntered(this::closeButtonMouseEntered);
        closeButton.setOnMouseExited(this::closeButtonMouseExited);
    }

    private void handleHome() {
        // Close the current window
        Stage stage = (Stage) homeButton.getScene().getWindow();
        stage.close();
        // Open the home page window
        try {
            Stage myProfileStage = new Stage();
            myProfileStage.getIcons().add(new Image("images/HanaAvisTransLogoBlue.png"));
            myProfileStage.initStyle(StageStyle.TRANSPARENT);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/adminPanel/AdminPanelPage.fxml"));
            AdminPanelPageController controller = new AdminPanelPageController(user);
            fxmlLoader.setController(controller);
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            myProfileStage.setScene(scene);
            myProfileStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void logOut() {
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
