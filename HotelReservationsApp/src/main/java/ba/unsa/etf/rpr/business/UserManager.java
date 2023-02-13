package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.HotelException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

/**
 * The type User manager.
 */
public class UserManager {
    /**
     * Delete.
     *
     * @param UserId the user id
     * @throws HotelException the hotel exception
     */
    public void delete(int UserId) throws HotelException {
        try {
            DaoFactory.userDao().delete(UserId);
        } catch (HotelException e) {
            if (e.getMessage().contains("FOREIGN KEY")) {
                throw new HotelException("NO");
            }
            throw e;
        }

    }

    /**
     * Add user.
     *
     * @param r the r
     * @return the user
     * @throws HotelException the hotel exception
     */
    public User add(User r) throws HotelException {
        return DaoFactory.userDao().add(r);
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     * @throws HotelException the hotel exception
     */
    public User getById(int id) throws HotelException {
        return DaoFactory.userDao().getById(id);
    }

    /**
     * Update user.
     *
     * @param r the r
     * @return the user
     * @throws HotelException the hotel exception
     */
    public User update(User r) throws HotelException {
        return DaoFactory.userDao().update(r);
    }

    /**
     * Gets all.
     *
     * @return the all
     * @throws HotelException the hotel exception
     */
    public List<User> getAll() throws HotelException {
        return DaoFactory.userDao().getAll();
    }

    /**
     * Find username user.
     *
     * @param usernameField the username field
     * @return the user
     * @throws HotelException the hotel exception
     */
    public User findUsername(String usernameField) throws HotelException{
        return DaoFactory.userDao().findUsername(usernameField);
    }

    private static final String HASHING_ALGORITHM = "SHA-256";

    /**
     * Hash password string.
     *
     * @param password the password
     * @return the string
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(HASHING_ALGORITHM);
        messageDigest.update(password.getBytes());

        byte[] hashedPassword = messageDigest.digest();

        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : hashedPassword) {
            stringBuilder.append(String.format("%02x", b));
        }
        return stringBuilder.toString();
    }

    /**
     * Total users int.
     *
     * @return the int
     * @throws SQLException the sql exception
     */
    public int totalUsers() throws SQLException{
        return DaoFactory.userDao().totalUsers();
    }
}