package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.Dao;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Hotel;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.Room;
import ba.unsa.etf.rpr.domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminPanelPageController {

    @FXML
    public javafx.scene.control.Label welcomeLabel;

    @FXML
    public ImageView logOutButton;
    public TableView<User> usersTable;
    public TableColumn<Object, Object> firstNameColumn;
    public TableColumn<Object, Object> lastNameColumn;
    public TableColumn<Object, Object> usernameColumn;
    public TableColumn<Object, Object> emailColumn;
    public TableView<Reservation> reservationsTable;
    public TableColumn reservationIdColumn;
    public TableColumn checkInColumn;
    public TableColumn checkOutColumn;
    public TableColumn totalColumn;
    public TableColumn adultsColumn;
    public TableColumn childrenColumn;
    public TableColumn roomIdColumn;
    public TableColumn username2Column;
    public TableView hotelsTable;
    public TableColumn name2Column;
    public TableColumn zipCodeColumn;
    public TableColumn cityColumn;
    public TableColumn countryColumn;
    public TableColumn starRatingColumn;
    public TableView roomsTable;
    public TableColumn statusColumn;
    public TableColumn typeColumn;
    public TableColumn capacityColumn;
    public TableColumn airCondColumn;
    public TableColumn hotelNameColumn;

    @FXML
    private User user;

    public AdminPanelPageController(){
        new User();
    }

    @FXML
    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    public User getUser() {
        return user;
    }



    private <T> ObservableList<T> getData(Class<T> type) {
        // Use a Map to store the mapping between object types and DAOs
        Map<Class<?>, Dao<?>> daoMap = new HashMap<>();
        daoMap.put(Hotel.class, DaoFactory.hotelDao());
        daoMap.put(Room.class, DaoFactory.roomDao());
        daoMap.put(User.class, DaoFactory.userDao());
        daoMap.put(Reservation.class, DaoFactory.reservationDao());

        // Use the Map to get the correct DAO for the desired object type
        Dao<T> dao;
        dao = (Dao<T>) daoMap.get(type);

        // Retrieve the data from the database using the DAO
        List<T> dataList = dao.getAll();
        return FXCollections.observableArrayList(dataList);
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
    public void initialize() {
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
        tables.add(new TableColumnPair(reservationsTable, Reservation.class, "reservationId", "checkIn", "checkOut", "total", "adults", "children", "roomId", "username"));
        tables.add(new TableColumnPair(hotelsTable, Hotel.class, "name", "zipCode", "city", "country", "starRating"));
        tables.add(new TableColumnPair(roomsTable, Room.class, "roomId", "status", "type", "capacity", "hasAirConditioning", "hotelId"));
        // Add more tables and columns as needed

        // Iterate over the list of tables and columns
        for (TableColumnPair pair : tables) {
            TableView table = pair.getTable();
            Class type = pair.getType();
            String[] columns = pair.getColumns();

            // Load the data from the database and set it as the items for the table
            table.setItems(getData(type));

            for (int i = 0; i < table.getColumns().size(); i++) {
                TableColumn tableColumn = (TableColumn) table.getColumns().get(i);
                String column = columns[i];
                tableColumn.setCellValueFactory(new PropertyValueFactory<>(column));
            }

        }
        //welcomeLabel.setText("Welcome " + user.getUsername()); // set the text of the label to display the welcome message
    }




}