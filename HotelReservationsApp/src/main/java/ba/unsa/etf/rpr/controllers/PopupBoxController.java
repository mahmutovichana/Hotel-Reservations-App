package ba.unsa.etf.rpr.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PopupBoxController {
    @FXML
    private Label messageLabel;

    @FXML
    public void setMessage(String message) {
        messageLabel.setText(message);
    }
}
