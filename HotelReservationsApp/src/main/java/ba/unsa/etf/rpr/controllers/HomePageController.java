package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;


public class HomePageController extends Parent {
    @FXML
    public ComboBox<String> comboBox;

    @FXML
    public ImageView logOutButton;

    @FXML
    public Button aboutUsButton;

    @FXML
    public Button myProfileButton;

    @FXML
    public Label welcomeLabel = new Label();

