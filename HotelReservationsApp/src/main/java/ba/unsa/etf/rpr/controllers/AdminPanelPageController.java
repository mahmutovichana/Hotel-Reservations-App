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



}
