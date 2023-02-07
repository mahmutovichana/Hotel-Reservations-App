package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.cli.*;
import java.util.Objects;

public class App
{
    public static void main(String[] args) {
        // Here you can work with args - command line parameters
        CommandLineParser commandLineParser = new DefaultParser();
        //Application.launch(args);
    }/*
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/main/Main.fxml")));
        primaryStage.getIcons().add(new Image("images/HanaAvisTransLogoBlue.png"));

            primaryStage.setTitle("Hana Avis");
            primaryStage.setWidth(300); primaryStage.setHeight(300);
            primaryStage.setScene(new Scene(root, 300, 275));
            - we don't technically need this because we set our scene to be transparent, but I left it, so I can
            remember how the syntax is to set the title in the title bar, same thing for the dimension of the app window

        //root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/stylesheets/styles.css")).toExternalForm());
        Scene scene = new Scene(root);
        Font.loadFont(getClass().getResourceAsStream("/resources/fonts/Fontspace.ttf"), 14);
        scene.getStylesheets().add("/stylesheets/styles.css");
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();

    }*/
}