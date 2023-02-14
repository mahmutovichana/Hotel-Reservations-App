package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The type About us page controller.
 */
public class AboutUsPageController{
    @FXML
    private ImageView goBack;

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    private User user;


    /**
     * Initialize.
     */
    public void initialize() {

        goBack.setOnMouseClicked(event -> {
            // Close the current window
            Stage stage = (Stage) goBack.getScene().getWindow();
            stage.close();
            // Open the previous window
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/homePage/HomePage.fxml"));
                HomePageController controller = new HomePageController();
                controller.setUser(user);
                fxmlLoader.setController(controller);
                Parent root = fxmlLoader.load();
                Stage homeStage = new Stage();
                homeStage.getIcons().add(new Image("images/HanaAvisTransLogoBlue.png"));
                homeStage.setScene(new Scene(root));
                homeStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
