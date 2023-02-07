package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Hotel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class AddHotelDialogController {

    public Button saveButton;
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

    private Hotel hotel=new Hotel();
    private boolean okClicked = false;

    public AddHotelDialogController() {
    }

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
    private void saveHotel() throws SQLException {
        hotel.setName(nameField.getText());
        hotel.setZipCode(Integer.parseInt(zipCodeField.getText()));
        hotel.setCity(cityField.getText());
        hotel.setCountry(countryField.getText());
        hotel.setStarRating(Integer.parseInt(starRatingField.getText()));
        okClicked = true;
        int totalHotels = DaoFactory.hotelDao().totalHotels();
        ((Stage) saveButton.getScene().getWindow()).close();
    }
    @FXML
    private void cancelHotel() {
        ((Stage) cancelButton.getScene().getWindow()).close();
    }


    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
        nameField.setText(hotel.getName());
        zipCodeField.setText(Integer.toString(hotel.getZipCode()));
        cityField.setText(hotel.getCity());
        countryField.setText(hotel.getCountry());
        starRatingField.setText(Integer.toString(hotel.getStarRating()));
    }
    public boolean isOkClicked() {
        return okClicked;
    }

}
