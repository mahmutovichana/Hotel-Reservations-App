package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Room;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.sql.SQLException;
import java.util.List;

public class DeleteRoomDialogController {

    @FXML
    private ComboBox<Room> roomsComboBox;

    @FXML
    public Button deleteButton;

    @FXML
    public Button cancelButton;

    private Room selectedRoom;
    private boolean okClicked = false;

    public void setRooms(List<Room> rooms) {
        roomsComboBox.getItems().addAll(rooms);
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

    public Room getSelectedRoom() {
        return selectedRoom;
    }

    @FXML
    private void handleDeleteRoom() throws SQLException {
        selectedRoom = roomsComboBox.getSelectionModel().getSelectedItem();
        if (selectedRoom != null) {
            okClicked = true;
            Stage stage = (Stage) deleteButton.getScene().getWindow();
            try {
                int totalRooms = DaoFactory.roomDao().totalRooms();
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