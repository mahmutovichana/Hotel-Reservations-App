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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * The type My profile page controller.
 */
public class MyProfilePageController {

    /**
     * The Root.
     */
    public Pane root;
    /**
     * The About us button.
     */
    public Button aboutUsButton;
    /**
     * The Log out button.
     */
    public ImageView logOutButton;
    /**
     * The Reservations button.
     */
    public Button reservationsButton;
    private User user;

    /**
     * Instantiates a new My profile page controller.
     *
     * @param u the u
     */
    public MyProfilePageController(User u){this.user=u;}

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
     * The Username label.
     */
    @FXML
    public Label usernameLabel = new Label();

    /**
     * The First name label.
     */
    @FXML
    public Label firstNameLabel = new Label();

    /**
     * The Last name label.
     */
    @FXML
    public Label lastNameLabel = new Label();

    /**
     * The Email label.
     */
    @FXML
    public Label emailLabel = new Label();

    /**
     * The Close button.
     */
    @FXML
    public Button closeButton;

    /**
     * Initialize.
     */
    public void initialize() {

        logOutButton.setOnMouseClicked(event -> logOut());

        firstNameLabel.setText(user.getFirstName());
        lastNameLabel.setText(user.getLastName());
        emailLabel.setText(user.getEmail());
        usernameLabel.setText(user.getUsername());

        closeButton.setOnAction(this::closeButtonAction);
        closeButton.setOnMouseEntered(this::closeButtonMouseEntered);
        closeButton.setOnMouseExited(this::closeButtonMouseExited);

        aboutUsButton.setOnAction(event -> {
            // Close the current window
            Stage stage = (Stage) aboutUsButton.getScene().getWindow();
            stage.close();
            // Open the about us page window
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/homePage/AboutUsPage.fxml"));
                Parent root = fxmlLoader.load();
                Stage aboutUsStage = new Stage();
                aboutUsStage.getIcons().add(new Image("images/HanaAvisTransLogoBlue.png"));
                aboutUsStage.initStyle(StageStyle.TRANSPARENT);
                Scene scene = new Scene(root);
                scene.setFill(Color.TRANSPARENT);
                aboutUsStage.setScene(scene);
                aboutUsStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        reservationsButton.setOnAction(event -> {
            // Close the current window
            Stage stage = (Stage) reservationsButton.getScene().getWindow();
            stage.close();
            // Open the "my profile" page window
            try {
                Stage myProfileStage = new Stage();
                myProfileStage.getIcons().add(new Image("images/HanaAvisTransLogoBlue.png"));
                myProfileStage.initStyle(StageStyle.TRANSPARENT);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/homePage/ListOfReservationsPage.fxml"));
                ListOfReservationsPageController controller = new ListOfReservationsPageController(user);
                fxmlLoader.setController(controller);
                Parent root = fxmlLoader.load();

                Scene scene = new Scene(root);
                scene.setFill(Color.TRANSPARENT);
                myProfileStage.setScene(scene);
                myProfileStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
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

    @FXML
    private void closeButtonAction(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    @FXML
    private void closeButtonMouseExited(MouseEvent mouseEvent){closeButton.getStyleClass().remove("closeButtonWhenHovered");}
    @FXML
    private void closeButtonMouseEntered(MouseEvent mouseEvent) {
        closeButton.getStyleClass().add("closeButtonStyle");
        closeButton.getStyleClass().add("closeButtonWhenHovered");
    }
}
