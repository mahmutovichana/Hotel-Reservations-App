package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Room;
import ba.unsa.etf.rpr.exceptions.HotelException;

import java.util.List;

public class RoomManager {
    public void delete(int RoomId) throws HotelException {
        try {
            DaoFactory.roomDao().delete(RoomId);
        } catch (HotelException e) {
            if (e.getMessage().contains("FOREIGN KEY")) {
                throw new HotelException("NO");
            }
            throw e;
        }

    }

    public Room add(Room r) throws HotelException {
        return DaoFactory.roomDao().add(r);
    }

    public Room getById(int id) throws HotelException {
        return DaoFactory.roomDao().getById(id);
    }

    public Room update(Room r) throws HotelException {
        return DaoFactory.roomDao().update(r);
    }

    public List<Room> getAll() throws HotelException {
        return DaoFactory.roomDao().getAll();
    }
}