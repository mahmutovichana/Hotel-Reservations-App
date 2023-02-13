package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Reservation;

import java.sql.SQLException;

/**
 * Dao interface for Reservation domain bean
 *
 * @author Hana Mahmutovic
 */
public interface ReservationDao extends Dao<Reservation> {

    /**
     * Total income int.
     *
     * @return the int
     * @throws SQLException the sql exception
     */
    /* here we insert methods that are special only for that table, the implementation of it we do in SQL Impl file */
    public int totalIncome() throws SQLException;
}
