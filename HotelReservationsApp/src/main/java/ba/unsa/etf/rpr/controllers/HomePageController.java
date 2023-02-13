package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.HotelManager;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * The type Home page controller.
 */
public class HomePageController{

    /**
     * The Root.
     */
    @FXML
    public Pane root;
    /**
     * The Log out button.
     */
    @FXML
    public ImageView logOutButton;
    /**
     * The About us button.
     */
    @FXML
    public Button aboutUsButton;
    /**
     * The My profile button.
     */
    @FXML
    public Button myProfileButton;

    /**
     * The Reservations button.
     */
    @FXML
    public Button reservationsButton;
    /**
     * The Welcome label.
     */
    @FXML
    public Label welcomeLabel = new Label();
    /**
     * The Scroller.
     */
    @FXML
    public ScrollPane scroller;

    private final HotelManager h = new HotelManager();

    private boolean isChildWindowOpen = false;

    private void openHotelListForCity(String city) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/homePage/HotelListPage.fxml"));
            HotelListController controller = new HotelListController();
            System.out.println("saljem u openHotelListForCity grad: "+city);
            controller.setCity(city);
            controller.setUser(user);
            loader.setController(controller);
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.getIcons().add(new Image("images/HanaAvisTransLogoBlue.png"));
            stage.setScene(new Scene(root));
            System.out.println("cekam da udje u hotelListController");
            //stage.setOnCloseRequest(event -> isChildWindowOpen = false);
            stage.show();
            System.out.println("cekam...");
            isChildWindowOpen = true;
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }

    }

    /**
     * The City map.
     */
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

    /**
     * City divs.
     *
     * @param cityImageMap the city image map
     */
    public void CityDivs(Map<String, String> cityImageMap) {
        Set<String> cities = h.fetchCities();
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
                    new LinearGradient(0, 1.4, 0, 0, true, javafx.scene.paint.CycleMethod.NO_CYCLE,
                            new Stop(0, Color.BLACK), new Stop(1, Color.TRANSPARENT)));
            rect.setArcHeight(10);
            rect.setArcWidth(10);

            Label cityName = new Label(city);
            cityName.setTextFill(Color.WHITE);
            cityName.setFont(Font.font(null, FontWeight.BOLD, 20));
            cityName.setAlignment(Pos.CENTER);

            StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(imageView, rect, cityName);
            stackPane.setAlignment(Pos.CENTER);

            stackPane.setOnMouseClicked(event -> {System.out.println(city+"usla u ovo openHotel..");
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

    /**
     * Instantiates a new Home page controller.
     *
     * @param finalUser the final user
     */
    public HomePageController(User finalUser){
        this.user = finalUser;
    }

    /**
     * Instantiates a new Home page controller.
     */
    public HomePageController() {
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
                AboutUsPageController controller = new AboutUsPageController();
                controller.setUser(user);
                fxmlLoader.setController(controller);
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
        });

        reservationsButton.setOnAction(event -> {
            // Close the current window
            Stage stage = (Stage) reservationsButton.getScene().getWindow();
            stage.close();
            // Open the "my reservations" page window
            try {
                Stage reservationsStage = new Stage();
                reservationsStage.getIcons().add(new Image("images/HanaAvisTransLogoBlue.png"));
                reservationsStage.initStyle(StageStyle.TRANSPARENT);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/homePage/ListOfReservationsPage.fxml"));
                ListOfReservationsPageController controller = new ListOfReservationsPageController(user);
                fxmlLoader.setController(controller);
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root);
                scene.setFill(Color.TRANSPARENT);
                reservationsStage.setScene(scene);
                reservationsStage.show();
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
}
