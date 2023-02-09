package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Hotel;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * Dao interface for Hotel domain bean
 *
 * @author Hana Mahmutovic
 */
public interface HotelDao extends Dao<Hotel>{

    /* here we insert methods that are special only for that table, the implementation of it we do in SQL Impl file */
    int getByName(String hotelName);

    Set<String> fetchCities();

    List<Hotel> fetchHotelsByCity(String city);
    List<Hotel> fetchHotels();

    int totalHotels() throws SQLException;
}
