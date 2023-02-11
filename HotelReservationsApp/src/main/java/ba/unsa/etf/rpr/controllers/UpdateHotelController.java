package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.HotelManager;
import ba.unsa.etf.rpr.dao.AbstractDao;
import ba.unsa.etf.rpr.domain.Hotel;
import ba.unsa.etf.rpr.exceptions.HotelException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Update hotel controller.
 */
public class UpdateHotelController {
    /**
     * The Cancel button.
     */
    public Button cancelButton;
    /**
     * The Save button.
     */
    public Button saveButton;
    /**
     * The Hotel combo box.
     */
    public ComboBox<Hotel> hotelComboBox;
    /**
     * The Star rating field.
     */
    public TextField starRatingField;
    /**
     * The Country field.
     */
    public TextField countryField;
    /**
     * The City field.
     */
    public TextField cityField;
    /**
     * The Zip code field.
     */
    public TextField zipCodeField;
    /**
     * The Name field.
     */
    public TextField nameField;

    private boolean okClicked = false;
    private List<Hotel> hotels = new ArrayList<>();
    private Hotel hotel;

    private final HotelManager h = new HotelManager();

    @FXML
    private void handleOk() throws HotelException {
        Hotel selectedHotel = hotelComboBox.getSelectionModel().getSelectedItem();
        selectedHotel.setName(nameField.getText());
        selectedHotel.setZipCode(Integer.parseInt(zipCodeField.getText()));
        selectedHotel.setCity(cityField.getText());
        selectedHotel.setCountry(countryField.getText());
        selectedHotel.setStarRating(Integer.parseInt(starRatingField.getText()));
        h.update(selectedHotel);
        System.out.println(selectedHotel.getName());
        System.out.println(h.getById(8).toString());
        okClicked = true;
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCancel() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

    /**
     * Initialize.
     *
     * @throws HotelException the hotel exception
     */
    public void initialize() throws HotelException, SQLException {
        if(AbstractDao.getConnection().isClosed()) System.out.println("zatvorena");
        List<Hotel> allHotels = h.getAll();
        hotels = FXCollections.observableArrayList(allHotels);
        hotelComboBox.setItems((ObservableList<Hotel>) hotels);
        hotelComboBox.getSelectionModel().selectFirst();

        hotelComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                nameField.setText(newValue.getName());
                zipCodeField.setText(String.valueOf(newValue.getZipCode()));
                cityField.setText(newValue.getCity());
                countryField.setText(newValue.getCountry());
                starRatingField.setText(String.valueOf(newValue.getStarRating()));
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
     * Sets hotel.
     *
     * @param hotel the hotel
     */
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}

