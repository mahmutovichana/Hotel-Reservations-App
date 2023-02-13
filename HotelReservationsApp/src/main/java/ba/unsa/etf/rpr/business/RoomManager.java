package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.Dao;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Hotel;
import ba.unsa.etf.rpr.domain.Room;
import ba.unsa.etf.rpr.exceptions.HotelException;

import java.sql.SQLException;
import java.util.List;

/**
 * The type Room manager.
 */
public class RoomManager {
    /**
     * Delete.
     *
     * @param RoomId the room id
     * @throws HotelException the hotel exception
     */
    public void delete(int RoomId) throws HotelException {
        try {
            DaoFactory.roomDao().delete(RoomId);
        } catch (HotelException e) {
            if (e.getMessage().contains("FOREIGN KEY")) {
                throw new HotelException("NO");
            }
            throw e;
        }

    }

    /**
     * Add room.
     *
     * @param r the r
     * @return the room
     * @throws HotelException the hotel exception
     */
    public Room add(Room r) throws HotelException {
        return DaoFactory.roomDao().add(r);
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     * @throws HotelException the hotel exception
     */
    public Room getById(int id) throws HotelException {
        return DaoFactory.roomDao().getById(id);
    }

    /**
     * Update room.
     *
     * @param r the r
     * @return the room
     * @throws HotelException the hotel exception
     */
    public Room update(Room r) throws HotelException {
        return DaoFactory.roomDao().update(r);
    }

    /**
     * Gets all.
     *
     * @return the all
     * @throws HotelException the hotel exception
     */
    public List<Room> getAll() throws HotelException {
        return DaoFactory.roomDao().getAll();
    }

    /**
     * Total rooms int.
     *
     * @return the int
     * @throws SQLException the sql exception
     */
    public int totalRooms() throws SQLException{
        return DaoFactory.roomDao().totalRooms();
    }

    /**
     * Gets by hotel.
     *
     * @param hotel the hotel
     * @return the by hotel
     * @throws HotelException the hotel exception
     */
    public List<Room> getByHotel(Hotel hotel) throws HotelException {
        return DaoFactory.roomDao().getByHotel(hotel);
    }
}