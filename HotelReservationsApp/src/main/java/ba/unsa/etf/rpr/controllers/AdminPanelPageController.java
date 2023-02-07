package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.Dao;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Hotel;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.Room;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.HotelException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class AdminPanelPageController {

    @FXML
    public Label totalUsersRegistered;
    @FXML
    public Label totalHotelsRegistered;
    @FXML
    public Label totalRoomsRegistered;
    @FXML
    public Label totalIncome;
    @FXML
    public Label mostReservedHotel;

    public Button homeButton;
    @FXML
    public Button myProfileButton;
    @FXML
    public Label welcomeLabel;
    @FXML
    public Label usernameLabel;
    @FXML
    public ImageView logOutButton;
    public TableView<User> usersTable;
    public TableView<Reservation> reservationsTable;
    public TableView<Hotel> hotelsTable;
    public TableView<Room> roomsTable;
    @FXML
    private Label randomQuote;
    private final String[] quotes = { "Today's the day, let's make it count!",
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
            "Let's make today a day to remember" };
    @FXML
    public Button addHotelButton;
    @FXML
    public Button deleteHotelButton;
    @FXML
    public Button updateHotelButton;
    @FXML
    public Button addRoomButton;
    @FXML
    public Button deleteRoomButton;
    @FXML
    public Button updateRoomButton;
    @FXML
    private User user;
    @FXML
    private Button closeButton;

    public AdminPanelPageController(User finalUser){
        this.user = finalUser;
    }

    @FXML
    public void setUser(User user) {
        this.user = user;
    }

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
        Dao<T> dao = (Dao<T>) daoMap.get(type);

        // Retrieve the data from the database using the DAO
        List<T> dataList = dao.getAll();
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
            AccountAdminPageController controller = new AccountAdminPageController(user);
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

        public TableColumnPair(TableView table, Class type, String... columns) {
            this.table = table;
            this.type = type;
            this.columns = columns;
        }
        public TableView getTable() {
            return table;
        }
        public Class getType() {
            return type;
        }
        public String[] getColumns() {
            return columns;
        }
    }

    @FXML
    public void initialize() throws HotelException, SQLException {

        int result = DaoFactory.reservationDao().totalIncome();
        totalIncome.setText(result +" $");

        //mostReservedHotel.setText(bestHotel);

        int totalHotels = DaoFactory.hotelDao().totalHotels();
        totalHotelsRegistered.setText(String.valueOf(totalHotels));

        int totalRooms = DaoFactory.roomDao().totalRooms();
        totalRoomsRegistered.setText(String.valueOf(totalRooms));

        int totalUsers = DaoFactory.userDao().totalUsers();
        totalUsersRegistered.setText(String.valueOf(totalUsers));

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
        tables.add(new TableColumnPair(hotelsTable, Hotel.class, "name", "zipCode", "city", "country", "starRating"));
        tables.add(new TableColumnPair(roomsTable, Room.class, "id", "status", "type", "capacity", "hasAirConditioning", "hotelId"));
        // Add more tables and columns as needed


        for (TableColumnPair pair : tables) {
            TableView table = pair.getTable();
            Class type = pair.getType();
            String[] columns = pair.getColumns();

            // Load the data from the database and set it as the items for the table
            table.setItems(getData(type));

            for (int i = 0; i < table.getColumns().size(); i++) {
                TableColumn tableColumn = (TableColumn) table.getColumns().get(i);

                if (columns[i].equals("name") && type.equals(Room.class)) {
                    tableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Room, String>, ObservableValue<String>>() {
                        @Override
                        public ObservableValue<String> call(TableColumn.CellDataFeatures<Room, String> cellData) {
                            Room room = cellData.getValue();
                            Hotel hotel = room.getHotelId();
                            String hotelName = hotel.getName();
                            return new SimpleStringProperty(hotelName);
                        }
                    });
                } else {
                    tableColumn.setCellValueFactory(new PropertyValueFactory<>(columns[i]));
                }
            }
        }

        welcomeLabel.setText("Welcome " + user.getFirstName()); // set the text of the label to display the welcome message

        closeButton.setOnAction(this::closeButtonAction);
        closeButton.setOnMouseEntered(this::closeButtonMouseEntered);
        closeButton.setOnMouseExited(this::closeButtonMouseExited);

        Random rand = new Random();
        int randomIndex = rand.nextInt(quotes.length);
        randomQuote.setText(quotes[randomIndex]);

        addHotelButton.setOnMouseClicked(this::handleAddHotel);
        deleteHotelButton.setOnAction(this::handleDeleteHotel);

        addRoomButton.setOnMouseClicked(this::handleAddRoom);
        deleteRoomButton.setOnAction(this::handleDeleteRoom);

        myProfileButton.setOnAction(this::handleAccount);
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
                DaoFactory.hotelDao().add(hotel);
                hotelsTable.setItems(getData(Hotel.class));
                int totalHotels = DaoFactory.hotelDao().totalHotels();
                totalHotelsRegistered.setText(String.valueOf(totalHotels));
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

            controller.setHotels(DaoFactory.hotelDao().getAll());

            dialogStage.showAndWait();

            if (controller.isOkClicked()) {
                DaoFactory.hotelDao().delete(controller.getSelectedHotel().getId());
                hotelsTable.setItems(getData(Hotel.class));
                int totalHotels = DaoFactory.hotelDao().totalHotels();
                totalHotelsRegistered.setText(String.valueOf(totalHotels));
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

            if (controller.isOkClicked()) {
                DaoFactory.roomDao().add(room);
                roomsTable.setItems(getData(Room.class));
                int totalRooms = DaoFactory.roomDao().totalRooms();
                totalRoomsRegistered.setText(String.valueOf(totalRooms));
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
            dialogStage.setTitle("Delete Hotel");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(roomsTable.getScene().getWindow());
            dialogStage.getIcons().add(new Image("images/HanaAvisTransLogoBlue.png"));
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            controller.setRooms(DaoFactory.roomDao().getAll());

            dialogStage.showAndWait();

            if (controller.isOkClicked()) {
                DaoFactory.roomDao().delete(controller.getSelectedRoom().getId());
                roomsTable.setItems(getData(Room.class));
                int totalRooms = DaoFactory.roomDao().totalRooms();
                totalRoomsRegistered.setText(String.valueOf(totalRooms));
            }
        } catch (IOException | HotelException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
