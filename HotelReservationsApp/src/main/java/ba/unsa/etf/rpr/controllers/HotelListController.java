package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.HotelManager;
import ba.unsa.etf.rpr.domain.Hotel;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.HotelException;
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
import java.util.Arrays;
import java.util.List;

/**
 * The type Hotel list controller.
 */
public class HotelListController {
    /**
     * The My profile button.
     */
    public Button myProfileButton;
    /**
     * The About us button.
     */
    public Button aboutUsButton;
    /**
     * The Home button.
     */
    public Button homeButton;
    /**
     * The Reservations button.
     */
    public Button reservationsButton;
    /**
     * The Log out button.
     */
    public ImageView logOutButton;
    /**
     * The Scroller.
     */
    public ScrollPane scroller;
    /**
     * The Root.
     */
    public Pane root;
    /**
     * The Go back.
     */
    public ImageView goBack;
    private String city;

    private final HotelManager h = new HotelManager();
    @FXML
    private Label cityLabel;

    private User user = new User();

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
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
        if (cityLabel != null) {
            cityLabel.setText(city);
        }
    }

    public GridPane hotelPane = new GridPane();

    private void openRoomListForHotel(String hotelName) {
        // Close the current window
        Stage stage = (Stage) hotelPane.getScene().getWindow();
        stage.close();
        // Open the "my reservations" page window
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/homePage/RoomListPage.fxml"));
            RoomListController controller = new RoomListController();
            controller.setHotel(hotelName);
            controller.setUser(user);
            loader.setController(controller);
            Parent root = loader.load();
            Stage stage2 = new Stage();
            stage2.getIcons().add(new Image("images/HanaAvisTransLogoBlue.png"));
            stage2.setScene(new Scene(root));
            stage2.setResizable(false);
            stage2.show();
            stage.hide();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        } catch (HotelException e) {
            throw new RuntimeException(e);
        }
    }

    private final List<String> hotelImages = Arrays.asList(
            "/images/hotels/1.jpg",
            "/images/hotels/2.jpg",
            "/images/hotels/3.jpg",
            "/images/hotels/4.jpg",
            "/images/hotels/5.jpg",
            "/images/hotels/6.jpg",
            "/images/hotels/7.jpg",
            "/images/hotels/8.jpg",
            "/images/hotels/9.jpg",
            "/images/hotels/10.jpg",
            "/images/hotels/11.jpg",
            "/images/hotels/12.jpg",
            "/images/hotels/13.jpg"
    );

    /**
     * Hotel divs.
     *
     * @param hotelImages the hotel images
     */
    public void HotelDivs(List<String> hotelImages) {
        List<Hotel> hotels = h.fetchHotelsByCity(city);
        hotelPane.setHgap(20);
        hotelPane.setVgap(20);
        hotelPane.setPadding(new Insets(10));
        hotelPane.setAlignment(Pos.CENTER);
        int row = 0;
        int column = 0;
        System.out.println(Arrays.toString(hotels.toArray()));
        for (Hotel hotel : hotels) {

            int randomIndex = (int) (Math.random() * hotelImages.size());
            String imageUrl = hotelImages.get(randomIndex);
            ImageView imageView = new ImageView(new Image(imageUrl));
            imageView.setFitHeight(170);
            imageView.setFitWidth(250);
            Rectangle rect = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight(),
                    new LinearGradient(0, 1.4, 0, 0, true, javafx.scene.paint.CycleMethod.NO_CYCLE,
                            new Stop(0, Color.BLACK), new Stop(1, Color.TRANSPARENT)));
            rect.setArcHeight(10);
            rect.setArcWidth(10);

            Label hotelName = new Label(hotel.getName());
            hotelName.setTextFill(Color.WHITE);
            hotelName.setFont(Font.font(null, FontWeight.BOLD, 20));
            hotelName.setAlignment(Pos.CENTER);

            StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(imageView, rect, hotelName);
            stackPane.setAlignment(Pos.CENTER);

            stackPane.setOnMouseClicked(event -> openRoomListForHotel(hotel.getName()));
            hotelPane.add(stackPane, column, row);
            column++;
            if (column == 3) {
                column = 0;
                row++;
            }
        }
        System.out.println("izaslo");
        scroller.setContent(hotelPane);
    }

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {

        HotelDivs(hotelImages);

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
                aboutUsStage.setResizable(false);
                aboutUsStage.show();
                stage.hide();
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
                myProfileStage.setResizable(false);
                myProfileStage.show();
                stage.hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

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
                stage.setResizable(false);
                homeStage.getIcons().add(new Image("images/HanaAvisTransLogoBlue.png"));
                homeStage.setScene(new Scene(root));
                homeStage.show();
                stage.hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        homeButton.setOnAction(event -> {
            // Close the current window
            Stage stage = (Stage) homeButton.getScene().getWindow();
            stage.close();
            // Open the about us page window
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/homePage/HomePage.fxml"));
                HomePageController controller = new HomePageController();
                controller.setUser(user);
                fxmlLoader.setController(controller);
                Parent root = fxmlLoader.load();
                Stage stage2 = new Stage();
                stage2.getIcons().add(new Image("images/HanaAvisTransLogoBlue.png"));
                stage2.initStyle(StageStyle.TRANSPARENT);
                Scene scene = new Scene(root);
                scene.setFill(Color.TRANSPARENT);
                stage2.setScene(scene);
                stage2.show();
                stage.hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        reservationsButton.setOnAction(event -> {
            // Close the current window
            Stage stage = (Stage) reservationsButton.getScene().getWindow();
            stage.close();
            // Open the about us page window
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/homePage/AboutUsPage.fxml"));
                ListOfReservationsPageController controller = new ListOfReservationsPageController(user);
                fxmlLoader.setController(controller);
                Parent root = fxmlLoader.load();
                Stage stage2 = new Stage();
                stage2.getIcons().add(new Image("images/HanaAvisTransLogoBlue.png"));
                stage2.initStyle(StageStyle.TRANSPARENT);
                Scene scene = new Scene(root);
                scene.setFill(Color.TRANSPARENT);
                stage2.setScene(scene);
                stage2.setResizable(false);
                stage2.show();
                stage.hide();
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