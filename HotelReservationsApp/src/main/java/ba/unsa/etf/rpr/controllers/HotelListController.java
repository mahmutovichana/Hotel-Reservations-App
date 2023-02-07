package ba.unsa.etf.rpr.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HotelListController {
    private String city;

    @FXML
    private Label cityLabel;

    public void setCity(String city) {
        this.city = city;
        if (cityLabel != null) {
            cityLabel.setText(city);
        }
    }
}
