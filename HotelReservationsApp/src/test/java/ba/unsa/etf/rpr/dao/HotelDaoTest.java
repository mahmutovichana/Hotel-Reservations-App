package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.business.HotelManager;
import ba.unsa.etf.rpr.domain.Hotel;
import ba.unsa.etf.rpr.exceptions.HotelException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

class HotelDaoTest {

    private HotelManager hotelManager;
    @Mock
    private HotelDao hotelDao;

    public Hotel hotel = new Hotel("Mocky", 10001, "New York", "USA", 5);

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        hotelManager = new HotelManager();
    }

    @Test
    void hotelConstructorTest() {
        assertEquals("Mocky", hotel.getName());
        assertEquals(10001, hotel.getZipCode());
        assertEquals("New York", hotel.getCity());
        assertEquals("USA", hotel.getCountry());
        assertEquals(5, hotel.getStarRating());
    }

    @Test
    public void addTest() throws HotelException {
        hotelDao.add(hotel);
        verify(hotelDao).add(hotel);
    }


    @Test
    void updateTest() throws Exception {
        hotel.setName("Hotel Mocky");
        hotelDao.update(hotel);
        verify(hotelDao).update(hotel);
    }
}
