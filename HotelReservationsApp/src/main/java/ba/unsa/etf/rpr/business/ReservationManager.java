package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.exceptions.HotelException;

import java.util.List;

/**
 * The type Reservation manager.
 */
public class ReservationManager {
    /**
     * Delete.
     *
     * @param ReservationId the reservation id
     * @throws HotelException the hotel exception
     */
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

    /**
     * Add reservation.
     *
     * @param r the r
     * @return the reservation
     * @throws HotelException the hotel exception
     */
    public Reservation add(Reservation r) throws HotelException {
        return DaoFactory.reservationDao().add(r);
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     * @throws HotelException the hotel exception
     */
    public Reservation getById(int id) throws HotelException {
        return DaoFactory.reservationDao().getById(id);
    }

    /**
     * Update reservation.
     *
     * @param r the r
     * @return the reservation
     * @throws HotelException the hotel exception
     */
    public Reservation update(Reservation r) throws HotelException {
        return DaoFactory.reservationDao().update(r);
    }

    /**
     * Gets all.
     *
     * @return the all
     * @throws HotelException the hotel exception
     */
    public List<Reservation> getAll() throws HotelException {
        return DaoFactory.reservationDao().getAll();
    }
}