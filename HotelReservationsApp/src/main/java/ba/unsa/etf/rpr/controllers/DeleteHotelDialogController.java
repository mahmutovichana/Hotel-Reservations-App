package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Hotel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.sql.SQLException;
import java.util.List;

public class DeleteHotelDialogController {

    @FXML
    private ComboBox<Hotel> hotelsComboBox;

    @FXML
    public Button deleteButton;

    @FXML
    public Button cancelButton;

    private Hotel selectedHotel;
    private boolean okClicked = false;

    public void setHotels(List<Hotel> hotels) {
        hotelsComboBox.getItems().addAll(hotels);
    }
    /*public void setHotels(List<Hotel> hotels) {
        hotelsComboBox.setItems(FXCollections.observableArrayList(hotels));
        hotelsComboBox.setConverter(new StringConverter<Hotel>() {
            @Override
            public String toString(Hotel object) {
                return object.getName();
            }
            @Override
            public Hotel fromString(String string) {
                return null;
            }
        });
    }*/
    public boolean isOkClicked() {
        return okClicked;
    }

    public Hotel getSelectedHotel() {
        return selectedHotel;
    }

    @FXML
    private void handleDeleteHotel() throws SQLException {
        selectedHotel = hotelsComboBox.getSelectionModel().getSelectedItem();
        if (selectedHotel != null) {
            okClicked = true;
            Stage stage = (Stage) deleteButton.getScene().getWindow();
            try {
                int totalHotels = DaoFactory.hotelDao().totalHotels();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            stage.close();
        }
    }

    @FXML
    public void cancelDelete(ActionEvent actionEvent) {
        Button cancelButton = (Button) actionEvent.getSource();
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}