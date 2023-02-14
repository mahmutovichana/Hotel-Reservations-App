package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.HotelManager;
import ba.unsa.etf.rpr.business.ReservationManager;
import ba.unsa.etf.rpr.business.RoomManager;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.dao.Dao;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Hotel;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.Room;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.HotelException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import static javafx.scene.paint.Color.*;

/**
 * The type Admin panel page controller.
 */
public class AdminPanelPageController {

    /**
     * Instantiates a new Admin panel page controller.
     */
    public AdminPanelPageController() {
    }

    /**
     * The Total users registered.
     */
    @FXML
    public Label totalUsersRegistered;
    /**
     * The Total hotels registered.
     */
    @FXML
    public Label totalHotelsRegistered;
    /**
     * The Total rooms registered.
     */
    @FXML
    public Label totalRoomsRegistered;
    /**
     * The Total income.
     */
    @FXML
    public Label totalIncome;

    //public Button homeButton;
    /**
     * The My profile button.
     */
    @FXML
    public Button myProfileButton;
    /**
     * The Welcome label.
     */
    @FXML
    public Label welcomeLabel;
    /**
     * The Username label.
     */
    @FXML
    public Label usernameLabel;
    /**
     * The Log out button.
     */
    @FXML
    public ImageView logOutButton;
    /**
     * The Users table.
     */
    public TableView<User> usersTable;
    /**
     * The Reservations table.
     */
    public TableView<Reservation> reservationsTable;
    /**
     * The Hotels table.
     */
    public TableView<Hotel> hotelsTable;
    /**
     * The Rooms table.
     */
    public TableView<Room> roomsTable;
    @FXML
    private Label randomQuote;

    private final ReservationManager r = new ReservationManager();
    private final HotelManager h = new HotelManager();
    private final RoomManager rm = new RoomManager();
    private final UserManager u = new UserManager();
    private final String[] quotes = {
            "Today's the day, let's make it count!",
            "Another day, another opportunity to shine!",
            "What about turning our goals into reality today?",
            "Dream big, work hard",
            "Create your own success story",
            "Make it happen, today",
            "Today's the day, let's make it extraordinary",
            "Elevate your game, today",
            "Let's make today the best one yet",
            "It's time to rock this",
            "Let's show them what we're made of today",
            "Let's make today legendary",
            "Let's turn today into a masterpiece",
            "Let's make today unforgettable",
            "Let's make today a day to remember"};
    /**
     * The Add hotel button.
     */
    @FXML
    public Button addHotelButton;
    /**
     * The Delete hotel button.
     */
    @FXML
    public Button deleteHotelButton;
    /**
     * The Update hotel button.
     */
    @FXML
    public Button updateHotelButton;
    /**
     * The Add room button.
     */
    @FXML
    public Button addRoomButton;
    /**
     * The Delete room button.
     */
    @FXML
    public Button deleteRoomButton;
    /**
     * The Update room button.
     */
    @FXML
    public Button updateRoomButton;
    @FXML
    private User user;
    @FXML
    private Button closeButton;

    @FXML
    private TableColumn<Room, Room> roomColumn;

    /**
     * Instantiates a new Admin panel page controller.
     *
     * @param finalUser the final user
     */
    public AdminPanelPageController(User finalUser){
        this.user = finalUser;
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

    private <T> ObservableList<T> getData(Class<T> type) throws HotelException {
        // Use a Map to store the mapping between object types and DAOs
        Map<Class<?>, Dao<?>> daoMap = new HashMap<>();
        daoMap.put(Hotel.class, DaoFactory.hotelDao());
        daoMap.put(Room.class, DaoFactory.roomDao());
        daoMap.put(User.class, DaoFactory.userDao());
        daoMap.put(Reservation.class, DaoFactory.reservationDao());

        // Use the Map to get the correct DAO for the desired object type
        Dao<?> dao = daoMap.get(type);

        // Retrieve the data from the database using the DAO
        List<T> dataList = (List<T>) dao.getAll();
        return FXCollections.observableArrayList(dataList);
    }

    private void handleAccount(ActionEvent event) {
        // Close the current window
        Stage stage = (Stage) myProfileButton.getScene().getWindow();
        stage.close();
        // Open the "my profile" page window
        try {
            Stage myProfileStage = new Stage();
            myProfileStage.getIcons().add(new Image("images/HanaAvisTransLogoBlue.png"));
            myProfileStage.initStyle(StageStyle.TRANSPARENT);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/adminPanel/AccountAdminPage.fxml"));
            AccountAdminPageController controller = new AccountAdminPageController();
            controller.setUser(user);
            fxmlLoader.setController(controller);
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            myProfileStage.setScene(scene);
            myProfileStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Inner class for storing table view, object type, and column names
    private static class TableColumnPair {
        private final TableView table;
        private final Class type;
        private final String[] columns;

        /**
         * Instantiates a new Table column pair.
         *
         * @param table   the table
         * @param type    the type
         * @param columns the columns
         */
        public TableColumnPair(TableView table, Class type, String... columns) {
            this.table = table;
            this.type = type;
            this.columns = columns;
        }

        /**
         * Gets table.
         *
         * @return the table
         */
        public TableView getTable() {
            return table;
        }

        /**
         * Gets type.
         *
         * @return the type
         */
        public Class getType() {
            return type;
        }

        /**
         * Get columns string [ ].
         *
         * @return the string [ ]
         */
        public String[] getColumns() {
            return columns;
        }
    }

    /**
     * Initialize.
     *
     * @throws HotelException the hotel exception
     * @throws SQLException   the sql exception
     */
    @FXML
    public void initialize() throws HotelException, SQLException {

        totalIncome.setText(r.totalIncome() +" $");
        totalHotelsRegistered.setText(String.valueOf(h.totalHotels()));
        totalRoomsRegistered.setText(String.valueOf(rm.totalRooms()));
        totalUsersRegistered.setText(String.valueOf(u.totalUsers()));

        usernameLabel.setText(user.getUsername());

        logOutButton.setOnMouseClicked(event -> logOut());

        // load users data from database to admin panel tab Users in usersTable
        /* professor's way of adding data from a table, it would need to be applied 3 more times, which is a bit inefficient
        usersTable.setItems(getData(User.class));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        */

        // Define a list of tables and their corresponding columns
        List<TableColumnPair> tables = new ArrayList<>();

        tables.add(new TableColumnPair(usersTable, User.class, "firstName", "lastName", "username", "email"));
        tables.add(new TableColumnPair(reservationsTable, Reservation.class, "id", "checkIn", "checkOut", "total", "adults", "children", "roomId", "username"));
        tables.add(new TableColumnPair(hotelsTable, Hotel.class, "name", "zipCode", "city", "country", "starRating", "noOfRooms"));
        tables.add(new TableColumnPair(roomsTable, Room.class, "id", "price", "status", "type", "capacity", "hasAirConditioning", "hotelId"));
        // Add more tables and columns as needed

        for (TableColumnPair pair : tables) {
            TableView table = pair.getTable();
            Class type = pair.getType();
            String[] columns = pair.getColumns();

            // Load the data from the database and set it as the items for the table
            table.setItems(getData(type));

            for (int i = 0; i < table.getColumns().size(); i++) {
                TableColumn tableColumn = (TableColumn) table.getColumns().get(i);
                tableColumn.setCellValueFactory(new PropertyValueFactory<>(columns[i]));
            }
        }

        /*ObservableList<Room> roomList = roomsTable.getItems();
        for (Room room : roomList) {
            Pane roomPane = new Pane();
            roomPane.setPrefSize(200, 100); // set the size of the pane
            Label capacityLabel = new Label("Capacity: " + room.getCapacity());
            roomPane.getChildren().addAll(capacityLabel);
            // add other labels for additional attributes as needed
            // add the roomPane to a container such as a VBox or a ScrollPane
        }*/

        /*roomColumn.setCellFactory(column -> new TableCell<Room, Room>() {
            @Override
            protected void updateItem(Room room, boolean empty) {
                super.updateItem(room, empty);
                if (empty || room == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Pane roomPane = new Pane();
                    roomPane.setPrefSize(200, 100);
                    Label capacityLabel = new Label("Capacity " + room.getCapacity());
                    Label typeLabel = new Label("Type "+ room.getType());
                    Label hotelLabel = new Label("Hotel "+ room.getHotelId().toString());
                    Label airLabel = new Label("Air conditioning "+room.getHasAirConditioning());
                    Label priceLabel = new Label("Price "+room.getPrice());
                    roomPane.getChildren().addAll(capacityLabel,typeLabel,hotelLabel,airLabel,priceLabel);
                    setGraphic(roomPane);
                }
            }
        });*/

        roomColumn.setCellFactory(column -> new TableCell<Room, Room>() {
            @Override
            protected void updateItem(Room room, boolean empty) {
                super.updateItem(room, empty);
                if (empty || room == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    VBox roomBox = new VBox(5);
                    roomBox.setPadding(new Insets(5));

                    Label capacityLabel = new Label("Capacity: " + room.getCapacity());
                    capacityLabel.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                    capacityLabel.setBackground(Background.fill(RED));

                    Label typeLabel = new Label("Type: " + room.getType());
                    typeLabel.setBorder(new Border(new BorderStroke(BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                    typeLabel.setBackground(Background.fill(BLUE));
                    typeLabel.setTextFill(WHITE);

                    Label hotelLabel = new Label("Hotel: " + room.getHotelId().toString());
                    hotelLabel.setBorder(new Border(new BorderStroke(GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                    hotelLabel.setBackground(Background.fill(GREEN));
                    hotelLabel.setTextFill(WHITE);

                    Label airLabel = new Label("Air conditioning: " + room.getHasAirConditioning());
                    airLabel.setBorder(new Border(new BorderStroke(Color.ORANGE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                    airLabel.setBackground(Background.fill(ORANGE));

                    Label priceLabel = new Label("Price: " + room.getPrice());
                    priceLabel.setBorder(new Border(new BorderStroke(Color.PURPLE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                    priceLabel.setBackground(Background.fill(PURPLE));
                    priceLabel.setTextFill(WHITE);

                    roomBox.getChildren().addAll(capacityLabel, typeLabel, hotelLabel, airLabel, priceLabel);
                    setGraphic(roomBox);
                }
            }
        });

        welcomeLabel.setText("Welcome " + user.getFirstName()); // set the text of the label to display the welcome message

        closeButton.setOnAction(this::closeButtonAction);
        closeButton.setOnMouseEntered(this::closeButtonMouseEntered);
        closeButton.setOnMouseExited(this::closeButtonMouseExited);

        Random rand = new Random();
        int randomIndex = rand.nextInt(quotes.length);
        randomQuote.setText(quotes[randomIndex]);

        addHotelButton.setOnMouseClicked(this::handleAddHotel);
        updateHotelButton.setOnMouseClicked(this::handleUpdateHotel);
        deleteHotelButton.setOnAction(this::handleDeleteHotel);

        addRoomButton.setOnMouseClicked(this::handleAddRoom);
        updateRoomButton.setOnMouseClicked(this::handleUpdateRoom);
        deleteRoomButton.setOnAction(this::handleDeleteRoom);

        myProfileButton.setOnAction(this::handleAccount);
    }
    @FXML
    private void closeButtonAction(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    @FXML
    private void closeButtonMouseExited(MouseEvent mouseEvent){ closeButton.getStyleClass().remove("closeButtonWhenHovered"); }
    @FXML
    private void closeButtonMouseEntered(MouseEvent mouseEvent) {
        closeButton.getStyleClass().add("closeButtonStyle");
        closeButton.getStyleClass().add("closeButtonWhenHovered");
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

    private void handleAddHotel(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/adminPanel/AddHotelDialog.fxml"));
            GridPane page = loader.load();
            AddHotelDialogController controller = loader.getController();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Hotel");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(hotelsTable.getScene().getWindow());
            dialogStage.getIcons().add(new Image("images/HanaAvisTransLogoBlue.png"));
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            Hotel hotel = new Hotel();
            controller.setHotel(hotel);
            dialogStage.showAndWait();
            if (controller.isOkClicked()) {
                h.add(hotel);
                hotelsTable.setItems(getData(Hotel.class));
                totalHotelsRegistered.setText(String.valueOf(h.totalHotels()));
            }
        } catch (IOException | HotelException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleUpdateHotel(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/adminPanel/UpdateHotelDialog.fxml"));
            VBox page = loader.load();
            UpdateHotelController controller = loader.getController();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Update Hotel");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(hotelsTable.getScene().getWindow());
            dialogStage.getIcons().add(new Image("images/HanaAvisTransLogoBlue.png"));
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            Hotel hotel = new Hotel();
            controller.setHotel(hotel);
            dialogStage.showAndWait();
            if (controller.isOkClicked()) {
                h.update(hotel);
                hotelsTable.setItems(getData(Hotel.class));
                totalHotelsRegistered.setText(String.valueOf(h.totalHotels()));
            }
        } catch (IOException | HotelException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void handleDeleteHotel(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/adminPanel/DeleteHotelDialog.fxml"));
            GridPane page = loader.load();
            DeleteHotelDialogController controller = loader.getController();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Delete Hotel");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(hotelsTable.getScene().getWindow());
            dialogStage.getIcons().add(new Image("images/HanaAvisTransLogoBlue.png"));
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            controller.setHotels(h.getAll());
            dialogStage.showAndWait();
            if (controller.isOkClicked()) {
                h.delete(controller.getSelectedHotel().getId());
                hotelsTable.setItems(getData(Hotel.class));
                totalHotelsRegistered.setText(String.valueOf(h.totalHotels()));
            }
        } catch (IOException | HotelException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleAddRoom(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/adminPanel/AddRoomDialog.fxml"));
            GridPane page = loader.load();
            AddRoomDialogController controller = loader.getController();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Room");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(roomsTable.getScene().getWindow());
            dialogStage.getIcons().add(new Image("images/HanaAvisTransLogoBlue.png"));
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            Room room = new Room();
            controller.setRoom(room);
            dialogStage.showAndWait();
            if (controller.isOkClicked()){
                rm.add(room);
                roomsTable.setItems(getData(Room.class));
                totalRoomsRegistered.setText(String.valueOf(rm.totalRooms()));
            }
        } catch (IOException | HotelException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void handleDeleteRoom(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/adminPanel/DeleteRoomDialog.fxml"));
            GridPane page = loader.load();
            DeleteRoomDialogController controller = loader.getController();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Delete Room");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(roomsTable.getScene().getWindow());
            dialogStage.getIcons().add(new Image("images/HanaAvisTransLogoBlue.png"));
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            controller.setRooms(rm.getAll());
            dialogStage.showAndWait();
            if (controller.isOkClicked()) {
                rm.delete(controller.getSelectedRoom().getId());
                roomsTable.setItems(getData(Room.class));
                totalRoomsRegistered.setText(String.valueOf(rm.totalRooms()));
            }
        } catch (IOException | HotelException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleUpdateRoom(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/adminPanel/UpdateRoomDialog.fxml"));
            GridPane page = loader.load();
            UpdateRoomController controller = loader.getController();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Update Room");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(roomsTable.getScene().getWindow());
            dialogStage.getIcons().add(new Image("images/HanaAvisTransLogoBlue.png"));
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            Room room = new Room();
            controller.setRoom(room);
            dialogStage.showAndWait();
            if (controller.isOkClicked()) {
                roomsTable.setItems(getData(Room.class));
                totalRoomsRegistered.setText(String.valueOf(rm.totalRooms()));
            }
        } catch (IOException | HotelException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
