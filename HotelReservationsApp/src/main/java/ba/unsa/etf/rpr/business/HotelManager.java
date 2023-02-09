package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Hotel;
import ba.unsa.etf.rpr.exceptions.HotelException;

import java.util.List;

public class HotelManager {
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

    public Hotel add(Hotel h) throws HotelException {
        return DaoFactory.hotelDao().add(h);
    }

    public int getByName(String hotelName) throws HotelException {
        return DaoFactory.hotelDao().getByName(hotelName);
    }

    public Hotel getById(int id) throws HotelException {
        return DaoFactory.hotelDao().getById(id);
    }

    public Hotel update(Hotel h) throws HotelException {
        return DaoFactory.hotelDao().update(h);
    }

    public List<Hotel> getAll() throws HotelException {
        return DaoFactory.hotelDao().getAll();
    }
}