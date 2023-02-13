package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.Room;
import ba.unsa.etf.rpr.domain.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ReservationDaoTest {
    @Test
    void gettersAndSettersTest() {
        LocalDate checkIn = LocalDate.of(2020, Month.FEBRUARY, 1);
        LocalDate checkOut = LocalDate.of(2020, Month.FEBRUARY, 14);
        Reservation reservation = new Reservation();
        reservation.setId(1);
        reservation.setCheckIn(checkIn);
        reservation.setCheckOut(checkOut);
        reservation.setTotal(100);
        reservation.setAdults(2);
        reservation.setChildren(1);
        reservation.setRoomId(new Room());
        reservation.setUsername(new User());

        assertEquals(1, reservation.getId());
        assertEquals(LocalDate.of(2020, Month.FEBRUARY, 1), reservation.getCheckIn());
        assertEquals(LocalDate.of(2020, Month.FEBRUARY, 14), reservation.getCheckOut());
        assertEquals(100, reservation.getTotal());
        assertEquals(2, reservation.getAdults());
        assertEquals(1, reservation.getChildren());
        assertNotNull(reservation.getRoomId());
        assertNotNull(reservation.getUsername());
    }

}

