package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Room;

import java.sql.SQLException;

/**
 * Dao interface for Room domain bean
 *
 * @author Hana Mahmutovic
 */
public interface RoomDao extends Dao<Room>{

    /* here we insert methods that are special only for that table, the implementation of it we do in SQL Impl file */
    public int totalRooms() throws SQLException;
}
