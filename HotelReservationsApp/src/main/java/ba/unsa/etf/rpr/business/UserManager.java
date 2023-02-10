package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.HotelException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class UserManager {
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

    public User add(User r) throws HotelException {
        return DaoFactory.userDao().add(r);
    }

    public User getById(int id) throws HotelException {
        return DaoFactory.userDao().getById(id);
    }

    public User update(User r) throws HotelException {
        return DaoFactory.userDao().update(r);
    }

    public List<User> getAll() throws HotelException {
        return DaoFactory.userDao().getAll();
    }
    public User findUsername(String usernameField) throws HotelException{
        return DaoFactory.userDao().findUsername(usernameField);
    }

    private static final String HASHING_ALGORITHM = "SHA-256";

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
}