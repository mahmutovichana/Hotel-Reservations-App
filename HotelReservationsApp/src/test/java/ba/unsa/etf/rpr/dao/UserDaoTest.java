package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserDaoTest {
    @Test
    public void testUserConstructor() {
        User user = new User("John", "Doe", "john.doe@example.com", 1, "johndoe");
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals(1, user.getRole());
        assertEquals("johndoe", user.getUsername());
    }

    @Test
    public void testUserSettersAndGetters() {
        User user = new User();
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setEmail("jane.doe@example.com");
        user.setRole(2);
        user.setUsername("janedoe");
        user.setPassword("password");

        assertEquals("Jane", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("jane.doe@example.com", user.getEmail());
        assertEquals(2, user.getRole());
        assertEquals("janedoe", user.getUsername());
        assertEquals("password", user.getPassword());
    }
}
