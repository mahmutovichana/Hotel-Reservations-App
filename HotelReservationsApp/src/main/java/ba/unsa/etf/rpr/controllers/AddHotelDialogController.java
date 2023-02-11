package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Hotel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * The type Add hotel dialog controller.
 */
public class AddHotelDialogController {

    /**
     * The Save button.
     */
    public Button saveButton;
    /**
     * The Cancel button.
     */
    public Button cancelButton;
    @FXML
    private TextField nameField;
    @FXML
    private TextField zipCodeField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField countryField;
    @FXML
    private TextField starRatingField;

    private Hotel hotel = new Hotel();
    private boolean okClicked = false;

    /**
     * Instantiates a new Add hotel dialog controller.
     */
    public AddHotelDialogController() {
    }

    /**
     * Instantiates a new Add hotel dialog controller.
     *
     * @param hotel the hotel
     */
    public AddHotelDialogController(Hotel hotel) {
        this.hotel = hotel;
    }

    @FXML
    private void initialize() {
        nameField.setText(hotel.getName());
        zipCodeField.setText(String.valueOf(hotel.getZipCode()));
        cityField.setText(hotel.getCity());
        countryField.setText(hotel.getCountry());
        starRatingField.setText(String.valueOf(hotel.getStarRating()));
    }

    @FXML
    private void saveHotel(){
        hotel.setName(nameField.getText());
        hotel.setZipCode(Integer.parseInt(zipCodeField.getText()));
        hotel.setCity(cityField.getText());
        hotel.setCountry(countryField.getText());
        hotel.setStarRating(Integer.parseInt(starRatingField.getText()));
        okClicked = true;
        ((Stage) saveButton.getScene().getWindow()).close();
    }
    @FXML
    private void cancelHotel() {
        ((Stage) cancelButton.getScene().getWindow()).close();
    }

    /**
     * Sets hotel.
     *
     * @param hotel the hotel
     */
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
        nameField.setText(hotel.getName());
        zipCodeField.setText(Integer.toString(hotel.getZipCode()));
        cityField.setText(hotel.getCity());
        countryField.setText(hotel.getCountry());
        starRatingField.setText(Integer.toString(hotel.getStarRating()));
    }

    /**
     * Is ok clicked boolean.
     *
     * @return the boolean
     */
    public boolean isOkClicked() {
        return okClicked;
    }

}
