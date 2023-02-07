/*package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Hotel;
import ba.unsa.etf.rpr.exceptions.HotelException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.print.DocFlavor;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class UpdateHotelController {
    public Button cancelButton;
    public Button saveButton;
    public ComboBox hotelComboBox;
    public TextField starRatingField;
    public TextField countryField;
    public TextField cityField;
    public TextField zipCodeField;
    public TextField nameField;

    private Hotel hotel = new Hotel();
    private boolean okClicked = false;
    private ObservableList<Hotel> hotels;

    @FXML
    private void handleOk() throws HotelException {
        int selectedIndex = hotelComboBox.getSelectionModel().getSelectedIndex();
        Hotel selectedHotel = hotels.get(selectedIndex);
        selectedHotel.setName(nameField.getText());
        selectedHotel.setZipCode(Integer.parseInt(zipCodeField.getText()));
        selectedHotel.setCity(cityField.getText());
        selectedHotel.setCountry(countryField.getText());
        selectedHotel.setStarRating(Integer.parseInt(starRatingField.getText()));
        DaoFactory.hotelDao().update(selectedHotel);
        okClicked = true;
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCancel() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

    public void initialize() throws HotelException {
        List<Hotel> allHotels = DaoFactory.hotelDao().fetchHotels();
        hotels = FXCollections.observableArrayList(allHotels);
        hotelComboBox.setItems(hotels);
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

    @FXML
    private void saveHotel() throws HotelException {
            Hotel selectedHotel = (Hotel) hotelComboBox.getSelectionModel().getSelectedItem();
            selectedHotel.setName(nameField.getText());
            selectedHotel.setZipCode(Integer.parseInt(zipCodeField.getText()));
            selectedHotel.setCity(cityField.getText());
            selectedHotel.setCountry(countryField.getText());
            selectedHotel.setStarRating(Integer.parseInt(starRatingField.getText()));
            DaoFactory.hotelDao().update(selectedHotel);
            okClicked = true;
            ((Stage) saveButton.getScene().getWindow()).close();
        }

        @FXML
        private void cancelHotel() {
            ((Stage) cancelButton.getScene().getWindow()).close();
        }


        public void updateHotel(ActionEvent actionEvent) {
    }

    public void cancel(ActionEvent actionEvent) {
    }
}
*/

package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Hotel;
import ba.unsa.etf.rpr.exceptions.HotelException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class UpdateHotelController {
    public Button cancelButton;
    public Button saveButton;
    public ComboBox<Hotel> hotelComboBox;
    public TextField starRatingField;
    public TextField countryField;
    public TextField cityField;
    public TextField zipCodeField;
    public TextField nameField;

    private boolean okClicked = false;
    private ObservableList<Hotel> hotels;
    private Hotel hotel;

    @FXML
    private void handleOk() throws HotelException {
        int selectedIndex = hotelComboBox.getSelectionModel().getSelectedIndex();
        Hotel selectedHotel = hotels.get(selectedIndex);
        selectedHotel.setName(nameField.getText());
        selectedHotel.setZipCode(Integer.parseInt(zipCodeField.getText()));
        selectedHotel.setCity(cityField.getText());
        selectedHotel.setCountry(countryField.getText());
        selectedHotel.setStarRating(Integer.parseInt(starRatingField.getText()));
        DaoFactory.hotelDao().update(selectedHotel);
        okClicked = true;
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCancel() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

    public void initialize() throws HotelException {
        List<Hotel> allHotels = DaoFactory.hotelDao().fetchHotels();
        hotels = FXCollections.observableArrayList(allHotels);
        hotelComboBox.setItems(hotels);
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

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setOkClicked(boolean okClicked) {
        this.okClicked = okClicked;
    }

    public void setHotels(ObservableList<Hotel> hotels) {
        this.hotels = hotels;
    }
}

