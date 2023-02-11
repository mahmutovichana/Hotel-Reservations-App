package ba.unsa.etf.rpr.business;

import org.junit.jupiter.api.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PasswordHashTest {
    @Test
    public void testHashPassword() throws NoSuchAlgorithmException {
        String password = "testPassword";
        String hashedPassword = UserManager.hashPassword(password);
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        String expectedHash = hexString.toString();
        assertEquals(expectedHash, hashedPassword);
    }

}
