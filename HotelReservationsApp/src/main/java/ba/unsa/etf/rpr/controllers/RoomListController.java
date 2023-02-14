package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.HotelManager;
import ba.unsa.etf.rpr.business.ReservationManager;
import ba.unsa.etf.rpr.business.RoomManager;
import ba.unsa.etf.rpr.domain.Hotel;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.Room;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.HotelException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

/**
 * The type Room list controller.
 */
public class RoomListController {

    /**
     * The Hotel.
     */
    public Hotel hotel = new Hotel();
    /**
     * The Root.
     */
    public Pane root;
    /**
     * The My profile button.
     */
    @FXML
    public Button myProfileButton;
    /**
     * The About us button.
     */
    @FXML
    public Button aboutUsButton;
    /**
     * The Log out button.
     */
    @FXML
    public ImageView logOutButton;
    /**
     * The Close button.
     */
    @FXML
    public Button closeButton;
    /**
     * The Rooms table.
     */
    public TableView<Room> roomsTable;
    /**
     * The Room type column.
     */
    public TableColumn<Object, Object> roomTypeColumn;
    /**
     * The Capacity column.
     */
    public TableColumn<Object, Object> capacityColumn;
    /**
     * The Status column.
     */
    public TableColumn<Object, Object> statusColumn;
    /**
     * The Price column.
     */
    public TableColumn<Object, Object> priceColumn;
    /**
     * The Air conditioning column.
     */
    public TableColumn<Object, Object> airConditioningColumn;
    /**
     * The Adults field.
     */
    public TextField adultsField;
    /**
     * The Children field.
     */
    public TextField childrenField;
    /**
     * The Total label.
     */
    @FXML
    public Label totalLabel;
    @FXML
    private Button bookButton = new Button();
    /**
     * The Reservations button.
     */
    @FXML
    public Button reservationsButton;
    /**
     * The Home button.
     */
    @FXML
    public Button homeButton;
    /**
     * The Check in.
     */
    @FXML
    public DatePicker checkIn;
    /**
     * The Check out.
     */
    @FXML
    public DatePicker checkOut;
    /**
     * The Alert label.
     */
    @FXML
    public Label alertLabel;

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }
    private User user = new User();
    private final RoomManager rm = new RoomManager();
    private final HotelManager h = new HotelManager();
    private final ReservationManager r = new ReservationManager();

    /**
     * Sets hotel.
     *
     * @param hotelName the hotel name
     * @throws HotelException the hotel exception
     */
    public void setHotel(String hotelName) throws HotelException{
        this.hotel = h.getById(h.getByName(hotelName));
        System.out.println(hotel.getName());
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Refresh table.
     */
    void refreshTable() {
        try {
            roomsTable.setItems(FXCollections.observableList(rm.getByHotel(hotel)));
            roomsTable.refresh();
        } catch (HotelException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * List rooms in hotel list.
     *
     * @param hotel the hotel
     * @return the list
     */
    public List<Room> listRoomsInHotel(Hotel hotel) {
        try {
            return rm.getByHotel(hotel);
        } catch (HotelException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Initialize.
     */
    @FXML
    public void initialize(){

        List<Room> rooms = listRoomsInHotel(hotel);
        roomsTable.setItems(FXCollections.observableList(rooms));

        roomTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        airConditioningColumn.setCellValueFactory(new PropertyValueFactory<>("hasAirConditioning"));
        refreshTable();

        bookButton.setOnMouseClicked(event -> bookRoom());

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
                myProfileStage.show();
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
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/homePage/ListOfReservationsPage.fxml"));
                ListOfReservationsPageController controller = new ListOfReservationsPageController(user);
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
                stage2.setResizable(false);
                stage2.setScene(scene);
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
    private void bookRoom(){
        try {
            Room selectedRoom = roomsTable.getSelectionModel().getSelectedItem();

            if (selectedRoom == null) {
                alertLabel.setText("Please select a room");
                return;
            }
            if (selectedRoom.getStatus()==0) {
                alertLabel.setText("This room is already booked");
                return;
            }

            System.out.println(checkIn.getValue()+" "+checkOut.getValue());
            if (checkIn.getValue()==null || checkOut.getValue()==null || !(Date.valueOf(checkOut.getValue()).toLocalDate()).isAfter(Date.valueOf(checkIn.getValue()).toLocalDate())) {
                alertLabel.setText("Invalid check-in and check-out dates.");
                return;
            }

            int checkInEpochDays = (int) checkIn.getValue().toEpochDay();
            int checkOutEpochDays = (int) checkOut.getValue().toEpochDay();
            int numNights = checkOutEpochDays - checkInEpochDays;
            if(numNights<1){
                alertLabel.setText("Please select a check-out date that is after the check-in date and allows for at least one night stay");
                return;
            }

            String adultsString = adultsField.getText();
            String childrenString = childrenField.getText();

            if (!adultsString.matches("^[1-9][0-9]*$") || !childrenString.matches("^[0-9]*$")) {
                alertLabel.setText("Adults field must be a positive integer and children field a non-negative integer.");
                return;
            }

            int adults = Integer.parseInt(adultsString);
            int children = Integer.parseInt(childrenString);
            int total = adults + children;
            if (total > selectedRoom.getCapacity()) {
                alertLabel.setText("Total number of guests exceeds room capacity.");
                return;
            }
            int totalPrice = numNights*(selectedRoom.getPrice() * adults + children * (selectedRoom.getPrice() / 2));
            totalLabel.setText(totalPrice +" $");

            System.out.println("prije rezervacije dosla");
            /*Reservation reservation = new Reservation(,
                    checkIn.getValue(),
                    checkOut.getValue(),
                    total,
                    adults,
                    children,
                    selectedRoom,
                    user);*/
            Reservation reservation = new Reservation();
            reservation.setCheckIn(checkIn.getValue());
            reservation.setCheckOut(checkOut.getValue());
            reservation.setTotal(totalPrice); reservation.setAdults(adults); reservation.setChildren(children);
            reservation.setUsername(user); reservation.setRoomId(selectedRoom);
            System.out.println("poslije rez dosla");
            r.add(reservation);
            selectedRoom.setStatus(0);
            // Save the reservation to database
            alertLabel.setText("Room successfully booked!");
            alertLabel.setTextFill(Paint.valueOf("green"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
