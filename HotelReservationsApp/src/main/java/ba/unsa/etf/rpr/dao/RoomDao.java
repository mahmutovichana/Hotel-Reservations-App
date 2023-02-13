package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Hotel;
import ba.unsa.etf.rpr.domain.Room;
import ba.unsa.etf.rpr.exceptions.HotelException;

import java.sql.SQLException;
import java.util.List;

/**
 * Dao interface for Room domain bean
 *
 * @author Hana Mahmutovic
 */
public interface RoomDao extends Dao<Room>{

    /**
     * Total rooms int.
     *
     * @return the int
     * @throws SQLException the sql exception
     */
    /* here we insert methods that are special only for that table, the implementation of it we do in SQL Impl file */
    public int totalRooms() throws SQLException;

    /**
     * Gets by hotel.
     *
     * @param hotel the hotel
     * @return the by hotel
     * @throws HotelException the hotel exception
     */
    public List<Room> getByHotel(Hotel hotel) throws HotelException;
}
