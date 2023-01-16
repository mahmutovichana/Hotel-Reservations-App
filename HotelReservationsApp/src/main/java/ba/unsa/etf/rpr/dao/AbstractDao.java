package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;
import ba.unsa.etf.rpr.exceptions.HotelException;

import java.sql.*;
import java.util.*;

/**
 * Abstract class that implements core DAO CRUD methods for every entity
 *
 * @author Hana Mahmutović
 */
public abstract class AbstractDao<T extends Idable> implements Dao<T>{
    private static Connection connection = null;
    private String tableName;

    public AbstractDao(String tableName) {
        this.tableName = tableName;
        if(connection==null) createConnection();
    }

    private static void createConnection(){
        if(AbstractDao.connection==null) {
            try {
                Properties p = new Properties();
                p.load(ClassLoader.getSystemResource("db.properties").openStream());
                String url = p.getProperty("server");
                String username = p.getProperty("username");
                String password = p.getProperty("password");
                AbstractDao.connection = DriverManager.getConnection(url, username, password);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
    }

    public static Connection getConnection(){
        return AbstractDao.connection;
    }

    /**
     * For singleton pattern, we have only one connection on the database which will be closed automatically when our program ends
     * But if we want to close connection manually, then we will call this method which should be called from finally block
     */

    public static void closeConnection() {
        System.out.println("pozvana metoda za zatvaranje konekcije");
        if(connection!=null) {
            try {
                connection.close();
            } catch (SQLException e) {
                //throw new RuntimeException(e);
                e.printStackTrace();
                System.out.println("REMOVE CONNECTION METHOD ERROR: Unable to close connection on database");
            }
        }
    }


    /**
     * Method for mapping ResultSet into Object
     * @param rs - result set from database
     * @return a Bean object for specific table
     * @throws HotelException in case of error with db
     */
    public abstract T row2object(ResultSet rs) throws HotelException;

    /**
     * Method for mapping Object into Map
     * @param object - a bean object for specific table
     * @return key, value sorted map of object
     */
    public abstract Map<String, Object> object2row(T object);

    public T getById(int id) throws HotelException {
        return executeQueryUnique("SELECT * FROM "+this.tableName+" WHERE id = ?", new Object[]{id});
    }

    public List<T> getAll() throws HotelException {
        return executeQuery("SELECT * FROM "+ tableName, null);
    }


}