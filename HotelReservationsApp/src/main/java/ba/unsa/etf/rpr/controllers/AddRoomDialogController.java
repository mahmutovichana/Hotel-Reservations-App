package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.HotelManager;
import ba.unsa.etf.rpr.domain.Hotel;
import ba.unsa.etf.rpr.domain.Room;
import ba.unsa.etf.rpr.exceptions.HotelException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.List;

/**
 * The type Add room dialog controller.
 */
public class AddRoomDialogController {


    /**
     * The Type combo box.
     */
    public ComboBox typeComboBox;
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
    /**
     * The Save button.
     */
    public Button saveButton;
    /**
     * The Cancel button.
     */
    public Button cancelButton;

    private Room room = new Room();

    /**
     * Instantiates a new Add room dialog controller.
     *
     * @throws HotelException the hotel exception
     */
    public AddRoomDialogController() throws HotelException {
    }

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
     * @param r the r
     */
    public void setRoom(Room r) {
        this.room = r;
    }

    private boolean okClicked = false;

    /**
     * Is ok clicked boolean.
     *
     * @return the boolean
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    private final HotelManager h = new HotelManager();
    /**
     * The Hotel list.
     */
    List<Hotel> hotelList = h.getAll();

    /**
     * Sets hotel list.
     */
    public void setHotelList() {
        hotelComboBox.getItems().clear();
        for (Hotel hotel : hotelList) {
            hotelComboBox.getItems().add(hotel.getName());
        }
    }

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {

        ToggleGroup toggleGroup = new ToggleGroup();
        yesRadioButton.setToggleGroup(toggleGroup);
        noRadioButton.setToggleGroup(toggleGroup);

        // get the list of hotels to choose from to set the room owner
        setHotelList();
    }

    /**
     * Save room.
     */
    @FXML
    public void saveRoom(){
        room.setType((String) typeComboBox.getValue());
        room.setPrice(Integer.parseInt(priceField.getText()));
        room.setCapacity(Integer.parseInt(capacityField.getText()));
        room.setHasAirConditioning(yesRadioButton.isSelected() ? 1 : 0);
        room.setStatus(1);
        String selectedHotelName = hotelComboBox.getValue();
        Hotel selectedHotel = hotelList.stream()
                .filter(hotel -> hotel.getName().equals(selectedHotelName))
                .findFirst()
                .orElse(null);
        room.setHotelId(selectedHotel);
        System.out.println(typeComboBox.getValue());
        System.out.println(room.toString());
        okClicked = true;
        // close the dialog
        ((Stage) saveButton.getScene().getWindow()).close();
    }

    /**
     * Cancel room.
     */
    @FXML
    public void cancelRoom() {
        ((Stage) cancelButton.getScene().getWindow()).close();
    }
}
