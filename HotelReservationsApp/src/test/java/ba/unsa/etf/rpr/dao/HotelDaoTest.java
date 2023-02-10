package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.business.HotelManager;
import ba.unsa.etf.rpr.domain.Hotel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HotelDaoTest {

    private HotelManager hotelManager;
    private HotelDao hotelDao;

    @BeforeEach
    void setUp() {
        hotelDao = mock(HotelDao.class);
        hotelManager = new HotelManager();
    }

    @Test
    void hotelConstructorTest() {
        Hotel hotel = new Hotel("Hilton", 10001, "New York", "USA", 5);
        assertEquals("Hilton", hotel.getName());
        assertEquals(10001, hotel.getZipCode());
        assertEquals("New York", hotel.getCity());
        assertEquals("USA", hotel.getCountry());
        assertEquals(5, hotel.getStarRating());
    }


    @Test
    void addTest() throws Exception {
        Hotel hotel = new Hotel("Hilton", 10001, "New York", "USA", 5);
        when(hotelDao.add(hotel)).thenReturn(hotel);
        assertEquals(hotel, hotelManager.add(hotel));
    }

    @Test
    void updateTest() throws Exception {
        Hotel hotel = new Hotel("Hilton", 10001, "New York", "USA", 5);
        when(hotelDao.update(hotel)).thenReturn(hotel);
        assertEquals(hotel, hotelManager.update(hotel));
    }
}
