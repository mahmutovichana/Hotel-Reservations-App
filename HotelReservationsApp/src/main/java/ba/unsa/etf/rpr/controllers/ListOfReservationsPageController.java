package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.HotelManager;
import ba.unsa.etf.rpr.business.ReservationManager;
import ba.unsa.etf.rpr.business.RoomManager;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import static org.apache.commons.lang3.StringUtils.valueOf;

/**
 * The type List of reservations page controller.
 */
public class ListOfReservationsPageController {

    /**
     * The Root.
     */
    public Pane root;
    /**
     * The About us button.
     */
    @FXML
    public Button aboutUsButton;

    /**
     * The Home button.
     */
    @FXML
    public Button homeButton;

    /**
     * The Log out button.
     */
    @FXML
    public ImageView logOutButton;
    /**
     * The My profile button.
     */
    @FXML
    public Button myProfileButton;
    /**
     * The Close button.
     */
    @FXML
    public Button closeButton;

    private User user;

    /**
     * Instantiates a new List of reservations page controller.
     *
     * @param u the u
     */
    public ListOfReservationsPageController(User u){this.user=u;}

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
     * The My reservations table.
     */
    @FXML
    public TableView<Reservation> myReservationsTable;
    /**
     * The Room type column.
     */
    @FXML
    public TableColumn<Reservation, String> roomTypeColumn;
    /**
     * The Check in column.
     */
    @FXML
    public TableColumn<Reservation, Date> checkInColumn;
    /**
     * The Check out column.
     */
    @FXML
    public TableColumn<Reservation, Date> checkOutColumn;
    /**
     * The Adults column.
     */
    @FXML
    public TableColumn<Reservation, Integer> adultsColumn;
    /**
     * The Children column.
     */
    @FXML
    public TableColumn<Reservation, Integer> childrenColumn;
    /**
     * The Total column.
     */
    @FXML
    public TableColumn<Reservation, Integer> totalColumn;
    /**
     * The Hotel name column.
     */
    @FXML
    public TableColumn<Reservation, String> hotelNameColumn;

    private final ReservationManager r = new ReservationManager();
    private final RoomManager rm = new RoomManager();
    private final HotelManager h = new HotelManager();

    /**
     * Refresh table.
     */
    void refreshTable(){
        try {
            myReservationsTable.setItems(FXCollections.observableList(r.getAllForUser(user)));
            myReservationsTable.refresh();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Initialize.
     */
    public void initialize() {

        roomTypeColumn.setCellValueFactory(param -> new SimpleStringProperty((valueOf(param.getValue().getRoomId().getType().toCharArray()))));
        checkInColumn.setCellValueFactory(new PropertyValueFactory<>("checkIn"));
        checkOutColumn.setCellValueFactory(new PropertyValueFactory<>("checkOut"));
        adultsColumn.setCellValueFactory(new PropertyValueFactory<>("adults"));
        childrenColumn.setCellValueFactory(new PropertyValueFactory<>("children"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        hotelNameColumn.setCellValueFactory(param -> new SimpleStringProperty((valueOf(param.getValue().getRoomId().getHotelId().getName().toCharArray()))));
        refreshTable();

        logOutButton.setOnMouseClicked(event -> logOut());

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
    private void closeButtonMouseExited(MouseEvent mouseEvent) {
        closeButton.getStyleClass().remove("closeButtonWhenHovered");
    }
    @FXML
    private void closeButtonMouseEntered(MouseEvent mouseEvent) {
        closeButton.getStyleClass().add("closeButtonStyle");
        closeButton.getStyleClass().add("closeButtonWhenHovered");
    }
}