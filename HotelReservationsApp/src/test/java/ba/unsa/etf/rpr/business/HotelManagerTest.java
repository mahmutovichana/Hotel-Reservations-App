package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.domain.Hotel;
import ba.unsa.etf.rpr.exceptions.HotelException;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class HotelManagerTest {

    @Test
    void deleteTest() {
        HotelManager hotelManager = new HotelManager();
        try {
            hotelManager.delete(1);
        } catch (HotelException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    void addTest() {
        HotelManager hotelManager = new HotelManager();
        try {
            Hotel hotel = new Hotel("Test", 12345, "Pariz", "Republika Francuska", 5);
            hotelManager.add(hotel);
        } catch (HotelException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    void getByIdTest() {
        HotelManager hotelManager = new HotelManager();
        try {
            Hotel hotel = hotelManager.getById(8);
            assertNotNull(hotel);
        } catch (HotelException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    void updateTest() {
        HotelManager hotelManager = new HotelManager();
        try {
            Hotel hotel = new Hotel("Test", 123456, "Pariz", "Republika Francuska", 5);
            hotel = hotelManager.update(hotel);
            assertNotNull(hotel);
        } catch (HotelException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    void getAllTest() {
        HotelManager hotelManager = new HotelManager();
        try {
            List<Hotel> hotels = hotelManager.getAll();
            assertNotNull(hotels);
        } catch (HotelException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
}