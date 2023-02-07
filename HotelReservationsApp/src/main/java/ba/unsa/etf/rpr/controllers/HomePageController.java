package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class HomePageController{

    @FXML
    public Pane root;
    @FXML
    public ImageView logOutButton;
    @FXML
    public Button aboutUsButton;
    @FXML
    public Button myProfileButton;
    @FXML
    public Label welcomeLabel = new Label();
    @FXML
    public ScrollPane scroller;

    private void openHotelListForCity(String city) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/homePage/HotelListPage.fxml"));
            Parent root = loader.load();
            HotelListController controller = loader.getController();
            controller.setCity(city);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }

    }

    public final Map<String, String> cityMap = new HashMap<String, String>() {{
        put("Sarajevo", "/images/cities/sarajevo.jpg");
        put("Mostar", "/images/cities/mostar.jpg");
        put("Neum","/images/cities/neum.jpg");
        put("Milano","/images/cities/milano.jpg");
        put("Barcelona","/images/cities/barcelona.png");
        put("Rim","/images/cities/rim.jpg");
        put("London","/images/cities/london.jpg");
        put("Pariz","/images/cities/pariz.jpg");
        put("Berlin","/images/cities/berlin.jpg");
        put("New York","/images/cities/newYork.jpg");
        put("Las Vegas","/images/cities/lasVegas.jpg");
        put("Venecija","/images/cities/venecija.jpg");
    }};

    public void CityDivs(Map<String, String> cityImageMap) {
        Set<String> cities = DaoFactory.hotelDao().fetchCities();
        GridPane cityPane = new GridPane();
        cityPane.setHgap(20);
        cityPane.setVgap(20);
        cityPane.setPadding(new Insets(10));
        cityPane.setAlignment(Pos.CENTER);
        int row = 0;
        int column = 0;
        for (String city : cities) {

            String imageUrl = cityImageMap.getOrDefault(city, "/images/cities/sanDiego.jpg");
            Image image = new Image(imageUrl);
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(170);
            imageView.setFitWidth(250);

            Rectangle rect = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight(),
                    new LinearGradient(0, 1.3, 0, 0, true, javafx.scene.paint.CycleMethod.NO_CYCLE,
                            new Stop[]{new Stop(0, Color.BLACK), new Stop(1, Color.TRANSPARENT)}));
            rect.setArcHeight(10);
            rect.setArcWidth(10);

            Label cityName = new Label(city);
            cityName.setTextFill(Color.WHITE);
            cityName.setFont(Font.font(null, FontWeight.BOLD, 20));
            cityName.setAlignment(Pos.CENTER);

            StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(imageView, rect, cityName);
            stackPane.setAlignment(Pos.CENTER);

            imageView.setOnMouseClicked(event -> {
                    openHotelListForCity(city);
            });

            cityPane.add(stackPane, column, row);
            column++;
            if (column == 3) {
                column = 0;
                row++;
            }
        }
        scroller.setContent(cityPane);
    }
    private User user = new User();

    public HomePageController(User finalUser){
        this.user = finalUser;
    }
    public HomePageController() {
    }
    @FXML
    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    public User getUser() {
        return user;
    }

    @FXML
    public void initialize() {

        welcomeLabel.setText("Welcome " + user.getFirstName()); // set the text of the label to display the welcome message

        CityDivs(cityMap);

        logOutButton.setOnMouseClicked(event -> logOut());

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

        myProfileButton.setOnAction(event -> {
            // Close the current window
            Stage stage = (Stage) myProfileButton.getScene().getWindow();
            stage.close();
            // Open the "my profile" page window
            try {
                Stage myProfileStage = new Stage();
                myProfileStage.getIcons().add(new Image("images/HanaAvisTransLogoBlue.png"));
                myProfileStage.initStyle(StageStyle.TRANSPARENT);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/homePage/MyProfilePage.fxml"));
                MyProfilePageController controller = new MyProfilePageController(user);
                fxmlLoader.setController(controller);
                Parent root = fxmlLoader.load();

                Scene scene = new Scene(root);
                scene.setFill(Color.TRANSPARENT);
                myProfileStage.setScene(scene);
                myProfileStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });}

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
