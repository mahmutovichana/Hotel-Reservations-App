package ba.unsa.etf.rpr.dao;

import java.util.List;

/**
 * Root interface for all DAO classes
 *
 * @author Hana Mahmutovic
 */
public interface Dao<T> {

    /**
     * get entity from database based on id
     *
     * @param id primary key of entity
     * @return Entity from database
     */
    T getById(int id);

    /**
     * get entity from database based on username
     *
     * @param username primary key of entity
     * @return Entity from database
     */
    T getByUsername(String username);

    /**
     * Saves entity into database
     * @param object bean for saving to database
     * @return saved item with id field populated
     */
    T add(T object);

    /**
     * Fully updates entity in database based on id (primary) match.
     * @param object - bean to be updated. id must be populated
     * @return updated version of bean
     */
    T update(T object);

    /**
     * Hard delete of item from database with given id
     * @param object - primary key of entity
     */
    void delete(T object);

    /**
     * Lists all entities from database. WARNING: Very slow operation because it reads all records.
     * @return List of entities from database
     */
    List<T> getAll();
}
