package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.RoomManager;
import ba.unsa.etf.rpr.domain.Hotel;
import ba.unsa.etf.rpr.domain.Room;
import ba.unsa.etf.rpr.exceptions.HotelException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Update room controller.
 */
public class UpdateRoomController {
    /**
     * The Type combo box.
     */
    public ComboBox<String> typeComboBox;
    /**
     * The Capacity field.
     */
    public TextField capacityField;
    /**
     * The Yes radio button.
     */
    public RadioButton yesRadioButton;
    /**
     * The No radio button.
     */
    public RadioButton noRadioButton;
    /**
     * The Price field.
     */
    public TextField priceField;
    /**
     * The Hotel combo box.
     */
    public ComboBox<Hotel> hotelComboBox;
    /**
     * The Update button.
     */
    public Button updateButton;
    /**
     * The Cancel button.
     */
    public Button cancelButton;
    /**
     * The Room combo box.
     */
    public ComboBox<Room> roomComboBox;

    private boolean okClicked = false;

    /**
     * Gets room.
     *
     * @return the room
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Sets room.
     *
     * @param room the room
     */
    public void setRoom(Room room) {
        this.room = room;
    }
    private List<Room> rooms = new ArrayList<>();
    private Room room;
    private final RoomManager rm = new RoomManager();
    @FXML
    private void handleOk() throws HotelException {
        Room selectedRoom = roomComboBox.getSelectionModel().getSelectedItem();
        selectedRoom.setHotelId(hotelComboBox.getValue());
        selectedRoom.setType(typeComboBox.getValue());
        selectedRoom.setPrice(Integer.parseInt(priceField.getText()));
        selectedRoom.setHasAirConditioning(yesRadioButton.isSelected() ? 1 : 0);
        selectedRoom.setCapacity(Integer.parseInt(capacityField.getText()));
        rm.update(selectedRoom);
        okClicked = true;
        ((Stage) updateButton.getScene().getWindow()).close();
    }

    @FXML
    private void handleCancel() {((Stage) cancelButton.getScene().getWindow()).close(); }


    /**
     * Initialize.
     *
     * @throws HotelException the hotel exception
     * @throws SQLException   the sql exception
     */
    public void initialize() throws HotelException, SQLException {

        ToggleGroup toggleGroup = new ToggleGroup();
        yesRadioButton.setToggleGroup(toggleGroup);
        noRadioButton.setToggleGroup(toggleGroup);

        List<Room> allRooms = rm.getAll();
        rooms = FXCollections.observableArrayList(allRooms);
        roomComboBox.setItems((ObservableList<Room>) rooms);
        roomComboBox.getSelectionModel().selectFirst();

        roomComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                typeComboBox.setValue(newValue.getType());
                capacityField.setText(String.valueOf(newValue.getCapacity()));
                if (newValue.getHasAirConditioning() == 1) {
                    yesRadioButton.setSelected(true); noRadioButton.setSelected(false);
                } else {
                    noRadioButton.setSelected(true); yesRadioButton.setSelected(false);
                }
                priceField.setText(String.valueOf(newValue.getPrice()));
                hotelComboBox.setValue(newValue.getHotelId());
            }
        });
    }

    /**
     * Is ok clicked boolean.
     *
     * @return the boolean
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Sets room.
     *
     * @param room the room
     */
    public void setHotel(Room room) {
        this.room = room;
    }
}

