package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Hotel;
import ba.unsa.etf.rpr.exceptions.HotelException;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * Dao interface for Hotel domain bean
 *
 * @author Hana Mahmutovic
 */
public interface HotelDao extends Dao<Hotel>{

    /**
     * Gets by name.
     *
     * @param hotelName the hotel name
     * @return the by name
     */
    /* here we insert methods that are special only for that table, the implementation of it we do in SQL Impl file */
    int getByName(String hotelName);

    /**
     * Fetch cities set.
     *
     * @return the set
     */
    Set<String> fetchCities();

    /**
     * Fetch hotels by city list.
     *
     * @param city the city
     * @return the list
     */
    List<Hotel> fetchHotelsByCity(String city);

    /**
     * Fetch hotels list.
     *
     * @return the list
     */
    List<String> fetchHotelNames();

    /**
     * Total hotels int.
     *
     * @return the int
     * @throws SQLException the sql exception
     */
    int totalHotels() throws SQLException;

    List<Hotel> NoOfRooms() throws HotelException;
}
