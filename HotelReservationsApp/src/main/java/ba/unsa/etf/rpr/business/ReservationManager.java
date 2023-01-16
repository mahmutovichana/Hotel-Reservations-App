package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.exceptions.HotelException;

import java.util.List;

public class ReservationManager {
    public void delete(int ReservationId) throws HotelException {
        try {
            DaoFactory.reservationDao().delete(ReservationId);
        } catch (HotelException e) {
            if (e.getMessage().contains("FOREIGN KEY")) {
                throw new HotelException("NO");
            }
            throw e;
        }

    }

    public Reservation add(Reservation r) throws HotelException {
        return DaoFactory.reservationDao().add(r);
    }

    public Reservation getById(int id) throws HotelException {
        return DaoFactory.reservationDao().getById(id);
    }

    public Reservation update(Reservation r) throws HotelException {
        return DaoFactory.reservationDao().update(r);
    }

    public List<Reservation> getAll() throws HotelException {
        return DaoFactory.reservationDao().getAll();
    }
}