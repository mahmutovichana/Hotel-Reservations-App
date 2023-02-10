package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Hotel;
import ba.unsa.etf.rpr.domain.Room;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RoomDaoTest {
    @Test
    public void testGettersAndSetters() {
        Room room = new Room();
        room.setId(1);
        room.setType("Single");
        room.setCapacity(2);
        room.setHasAirConditioning(1);
        room.setStatus(0);
        room.setHotelId(new Hotel("Hilton", 10001, "New York", "USA", 5));
        room.setPrice(100);

        assertEquals(1, room.getId());
        assertEquals("Single", room.getType());
        assertEquals(2, room.getCapacity());
        assertEquals(1, room.getHasAirConditioning());
        assertEquals(0, room.getStatus());
        assertNotNull(room.getHotelId());
        assertEquals(100, room.getPrice());
    }

    @Test
    public void testEquals() {
        Room room1 = new Room("Single", 2, 1, 0, new Hotel("Hilton", 10001, "New York", "USA", 5), 100);
        Room room2 = new Room("Single", 2, 1, 0, new Hotel("Hilton", 10001, "New York", "USA", 5), 100);

        assertEquals(room1, room2);
    }

    @Test
    public void testHashCode() {
        Room room1 = new Room("Single", 2, 1, 0, new Hotel(), 100);
        Room room2 = new Room("Single", 2, 1, 0, new Hotel(), 100);

        assertEquals(room1.hashCode(), room2.hashCode());
    }
}
