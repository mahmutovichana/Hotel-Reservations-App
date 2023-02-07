package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.RoomManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Hotel;
import ba.unsa.etf.rpr.domain.Room;
import ba.unsa.etf.rpr.exceptions.HotelException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.List;

public class AddRoomDialogController {

    private final RoomManager rm = new RoomManager();
    @FXML
    private ComboBox<String> typeBox = new ComboBox<>();
    @FXML
    private TextField capacityField;
    @FXML
    private RadioButton yesRadioButton;
    @FXML
    private RadioButton noRadioButton;
    @FXML
    private TextField priceField;
    @FXML
    private ComboBox<String> hotelComboBox;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;

    private Room room = new Room();

    public Room getRoom() {
        return room;
    }
    public void setRoom(Room r) {
        this.room = r;
    }

    private final boolean okClicked = false;

    public boolean isOkClicked() {
        return okClicked;
    }

    List<Hotel> hotelList = DaoFactory.hotelDao().fetchHotels();

    public void setHotelList() {
        hotelComboBox.getItems().clear();
        for (Hotel hotel : hotelList) {
            hotelComboBox.getItems().add(hotel.getName());
        }
    }

    @FXML
    public void initialize() {

        ToggleGroup toggleGroup = new ToggleGroup();
        yesRadioButton.setToggleGroup(toggleGroup);
        noRadioButton.setToggleGroup(toggleGroup);

        // Call the setHotelList method
        setHotelList();
    }


    @FXML
    public void saveRoom(ActionEvent actionEvent) throws HotelException {
        String type = typeBox.getValue();
        int capacity = Integer.parseInt(capacityField.getText());
        int hasAirConditioning;
        if(yesRadioButton.isSelected()) hasAirConditioning=1; else hasAirConditioning=0;
        int price = Integer.parseInt(priceField.getText());
        String selectedHotelName = hotelComboBox.getValue();
        Hotel selectedHotel = null;
        for (Hotel hotel : hotelList) {
            if (hotel.getName().equals(selectedHotelName)) {
                selectedHotel = hotel;
                break;
            }
        }
        room = new Room(type, capacity, hasAirConditioning, 1, selectedHotel, price);
        rm.add(room);
        // close the dialog
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void cancelRoom() {
        room = null;
        // close the dialog
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
