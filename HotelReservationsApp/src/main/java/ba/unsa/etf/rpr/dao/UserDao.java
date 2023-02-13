package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.HotelException;

import java.sql.SQLException;

/**
 * Dao interface for User domain bean
 *
 * @author Hana Mahmutovic
 */
public interface UserDao extends Dao<User> {
    /**
     * Find username user.
     *
     * @param usernameField the username field
     * @return the user
     * @throws HotelException the hotel exception
     */
    User findUsername(String usernameField) throws HotelException;

    /* here we insert methods that are special only for that table, the implementation of it we do in SQL Impl file */

    /**
     * Total users int.
     *
     * @return the int
     * @throws SQLException the sql exception
     */
    public int totalUsers() throws SQLException;

}