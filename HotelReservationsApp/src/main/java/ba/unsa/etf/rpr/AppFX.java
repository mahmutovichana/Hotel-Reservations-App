package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

/**
 * The type App fx.
 */
public class AppFX extends Application
{
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        // Here you can work with args - command line parameters
        Application.launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/main/Main.fxml")));
        primaryStage.getIcons().add(new Image("images/HanaAvisTransLogoBlue.png"));
        /*
            primaryStage.setTitle("Hana Avis");
            primaryStage.setWidth(300); primaryStage.setHeight(300);
            primaryStage.setScene(new Scene(root, 300, 275));
            - we don't technically need this because we set our scene to be transparent, but I left it, so I can
            remember how the syntax is to set the title in the title bar, same thing for the dimension of the app window
        */
        //root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/stylesheets/styles.css")).toExternalForm());
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/stylesheets/styles.css");
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setResizable(false);
        primaryStage.show();
    }}