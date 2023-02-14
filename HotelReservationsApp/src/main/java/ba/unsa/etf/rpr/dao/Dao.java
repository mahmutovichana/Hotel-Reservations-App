package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.exceptions.HotelException;

import java.sql.SQLException;
import java.util.List;

/**
 * Root interface for all DAO classes
 *
 * @param <T> the type parameter
 * @author Hana Mahmutović
 */
public interface Dao<T> {

    /**
     * get entity from database base on ID
     *
     * @param id primary key of entity
     * @return Entity from database
     * @throws HotelException the hotel exception
     */
    T getById(int id) throws HotelException;

    /**
     * Saves entity into database
     *
     * @param item bean for saving to database
     * @return saved item with id field populated
     * @throws HotelException the hotel exception
     */
    T add(T item) throws HotelException;

    /**
     * Fully updates entity in database based on id (primary) match.
     *
     * @param item - bean to be updated. id must be populated
     * @return updated version of bean
     * @throws HotelException the hotel exception
     */
    T update(T item) throws HotelException;

    /**
     * Hard delete of item from database with given id
     *
     * @param id - primary key of entity
     * @throws HotelException the hotel exception
     */
    void delete(int id) throws HotelException;

    /**
     * Lists all entities from database. WARNING: Very slow operation because it reads all records.
     *
     * @return List of entities from database
     * @throws HotelException the hotel exception
     */
    List<T> getAll() throws HotelException;
}
