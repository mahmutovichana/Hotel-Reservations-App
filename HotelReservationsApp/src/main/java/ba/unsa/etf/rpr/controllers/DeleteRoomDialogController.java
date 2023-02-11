package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.RoomManager;
import ba.unsa.etf.rpr.domain.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

/**
 * The type Delete room dialog controller.
 */
public class DeleteRoomDialogController {

    private final RoomManager rm = new RoomManager();
    @FXML
    private ComboBox<Room> roomsComboBox;

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

    private Room selectedRoom;
    private boolean okClicked = false;

    /**
     * Sets rooms.
     *
     * @param rooms the rooms
     */
    public void setRooms(List<Room> rooms) {
        roomsComboBox.getItems().addAll(rooms);
    }

    /**
     * Sets ok clicked.
     *
     * @return the ok clicked
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Gets selected room.
     *
     * @return the selected room
     */
    public Room getSelectedRoom() {
        return selectedRoom;
    }

    @FXML
    private void handleDeleteRoom(){
        selectedRoom = roomsComboBox.getSelectionModel().getSelectedItem();
        if (selectedRoom != null) {
            okClicked = true;
            Stage stage = (Stage) deleteButton.getScene().getWindow();
            try {
                rm.totalRooms();
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