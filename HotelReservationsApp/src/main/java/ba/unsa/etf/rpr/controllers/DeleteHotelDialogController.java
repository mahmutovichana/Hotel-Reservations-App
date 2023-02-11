package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.HotelManager;
import ba.unsa.etf.rpr.domain.Hotel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

/**
 * The type Delete hotel dialog controller.
 */
public class DeleteHotelDialogController {

    private final HotelManager h = new HotelManager();
    @FXML
    private ComboBox<Hotel> hotelsComboBox;

    /**
     * The Delete button.
     */
    @FXML
    public Button deleteButton;

    /**
     * The Cancel button.
     */
    @FXML
    public Button cancelButton;

    private Hotel selectedHotel;
    private boolean okClicked = false;

    /**
     * Sets hotels.
     *
     * @param hotels the hotels
     */
    public void setHotels(List<Hotel> hotels) {
        hotelsComboBox.getItems().addAll(hotels);
    }

    /**
     * Sets ok clicked.
     *
     * @return the ok clicked
     */
    public boolean isOkClicked() { return okClicked; }

    /**
     * Gets selected hotel.
     *
     * @return the selected hotel
     */
    public Hotel getSelectedHotel() {
        return selectedHotel;
    }

    @FXML
    private void handleDeleteHotel(){
        selectedHotel = hotelsComboBox.getSelectionModel().getSelectedItem();
        if (selectedHotel != null) {
            okClicked = true;
            Stage stage = (Stage) deleteButton.getScene().getWindow();
            try {
                h.totalHotels();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            stage.close();
        }
    }

    /**
     * Cancel delete.
     *
     * @param actionEvent the action event
     */
    @FXML
    public void cancelDelete(ActionEvent actionEvent) {
        Button cancelButton = (Button) actionEvent.getSource();
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}