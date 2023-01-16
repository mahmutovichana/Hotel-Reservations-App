package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.HotelException;

/**
 * Dao interface for User domain bean
 *
 * @author Hana Mahmutovic
 */
public interface UserDao extends Dao<User> {
    User findUsername(String usernameField) throws HotelException;

    /* here we insert methods that are special only for that table, the implementation of it we do in SQL Impl file */

}