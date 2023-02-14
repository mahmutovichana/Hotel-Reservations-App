package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Hotel;
import ba.unsa.etf.rpr.exceptions.HotelException;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * The type Hotel manager.
 */
public class HotelManager {
    /**
     * Delete.
     *
     * @param hotelId the hotel id
     * @throws HotelException the hotel exception
     */
    public void delete(int hotelId) throws HotelException {
        try {
            DaoFactory.hotelDao().delete(hotelId);
        } catch (HotelException e) {
            if (e.getMessage().contains("FOREIGN KEY")) {
                throw new HotelException("NO");
            }
            throw e;
        }

    }

    /**
     * Add hotel.
     *
     * @param h the h
     * @return the hotel
     * @throws HotelException the hotel exception
     */
    public Hotel add(Hotel h) throws HotelException {
        return DaoFactory.hotelDao().add(h);
    }

    /**
     * Gets by name.
     *
     * @param hotelName the hotel name
     * @return the by name
     * @throws HotelException the hotel exception
     */
    public int getByName(String hotelName) throws HotelException {
        return DaoFactory.hotelDao().getByName(hotelName);
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     * @throws HotelException the hotel exception
     */
    public Hotel getById(int id) throws HotelException {
        return DaoFactory.hotelDao().getById(id);
    }

    /**
     * Update hotel.
     *
     * @param h the h
     * @return the hotel
     * @throws HotelException the hotel exception
     */
    public Hotel update(Hotel h) throws HotelException {
        return DaoFactory.hotelDao().update(h);
    }

    /**
     * Gets all.
     *
     * @return the all
     * @throws HotelException the hotel exception
     */
    public List<Hotel> getAll() throws HotelException {
        return DaoFactory.hotelDao().getAll();
    }

    /**
     * Fetch hotel name list.
     *
     * @return the list
     */
    public List<String> fetchHotelNames() {
        return DaoFactory.hotelDao().fetchHotelNames();
    }

    /**
     * Fetch cities set.
     *
     * @return the set
     */
    public Set<String> fetchCities() {
        return DaoFactory.hotelDao().fetchCities();
    }

    /**
     * Fetch hotels by city list.
     *
     * @param city the city
     * @return the list
     */
    public List<Hotel> fetchHotelsByCity(String city) {
        return DaoFactory.hotelDao().fetchHotelsByCity(city);
    }

    /**
     * Total hotels int.
     *
     * @return the int
     * @throws SQLException the sql exception
     */
    public int totalHotels() throws SQLException{
        return DaoFactory.hotelDao().totalHotels();
    }

    public List<Hotel> NoOfRooms() throws SQLException, HotelException{
        return DaoFactory.hotelDao().NoOfRooms();
    }
}