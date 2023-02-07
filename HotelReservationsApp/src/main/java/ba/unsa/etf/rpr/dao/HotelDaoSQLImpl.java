package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Hotel;
import ba.unsa.etf.rpr.exceptions.HotelException;

import java.sql.*;
import java.util.*;

/**
 * MySQL Implementation of DAO
 * @author Hana Mahmutović
 */
public class HotelDaoSQLImpl extends AbstractDao<Hotel> implements HotelDao {

    private static HotelDaoSQLImpl instance = null;
    private HotelDaoSQLImpl() {
        super("HOTELS");
    }

    /**
     * @author Hana Mahmutović
     * @return QuoteDaoSQLImpl
     * We don't need more than one object for CRUD operations on table 'quotes' so we will use Singleton
     * This method will call private constructor in instance==null and then return that instance
     */
    public static HotelDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new HotelDaoSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    @Override
    public Hotel row2object(ResultSet rs) throws HotelException{
        try {
            Hotel hotel = new Hotel();
            hotel.setId(rs.getInt("id"));
            hotel.setName(rs.getString("name"));
            hotel.setZipCode(rs.getInt("zipCode"));
            hotel.setCity(rs.getString("city"));
            hotel.setCountry(rs.getString("country"));
            hotel.setStarRating(rs.getInt("starRating"));
            return hotel;
        } catch (Exception e) {
            throw new HotelException(e.getMessage(), e);
        }
    }

    /**
     * @param object - object to be mapped
     * @return map representation of object
     */
    @Override
    public Map<String, Object> object2row(Hotel object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("name", object.getName());
        item.put("zipCode", object.getZipCode());
        item.put("city", object.getCity());
        item.put("country", object.getCountry());
        item.put("starRating",object.getStarRating());
        return item;
    }

    @Override
    public List<String> getAllNames() {
        return null;
    }

    @Override
    public Set<String> fetchCities() {
            Set<String> cities = new HashSet<>();
            try (Connection connection = AbstractDao.getConnection()) {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT DISTINCT city FROM HOTELS");
                while (resultSet.next()) {
                    cities.add(resultSet.getString("city"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return cities;
        }

    @Override
    public List<Hotel> fetchHotelsByCity(String city) {
        List<Hotel> hotels = new ArrayList<>();
        // Connect to the database
        try (Connection connection = AbstractDao.getConnection()) {
            // Prepare a statement to execute the query
            String query = "SELECT * FROM HOTELS WHERE city = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, city);
            // Execute the query and get the result set
            ResultSet resultSet = statement.executeQuery();
            // Iterate over the result set and add each hotel to the list
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int zipCode = resultSet.getInt("zipCode");
                String cityy = resultSet.getString("city");
                String country = resultSet.getString("country");
                int starRating = resultSet.getInt("starRating");
                Hotel hotel = new Hotel(id, name, zipCode, cityy, country, starRating);
                hotels.add(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Return the list of hotels
        return hotels;
    }

    @Override
    public List<Hotel> fetchHotels() {
        List<Hotel> hotels = new ArrayList<>();
        // Connect to the database
        try (Connection connection = AbstractDao.getConnection()) {
            // Prepare a statement to execute the query
            String query = "SELECT * FROM HOTELS";
            PreparedStatement statement = connection.prepareStatement(query);
            // Execute the query and get the result set
            ResultSet resultSet = statement.executeQuery();
            // Iterate over the result set and add each hotel to the list
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int zipCode = resultSet.getInt("zipCode");
                String cityy = resultSet.getString("city");
                String country = resultSet.getString("country");
                int starRating = resultSet.getInt("starRating");
                Hotel hotel = new Hotel(id, name, zipCode, cityy, country, starRating);
                hotels.add(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Return the list of hotels
        return hotels;
    }

    public int totalHotels() throws SQLException{
        int total = 0;
        String query = "SELECT count(id) AS total_hotels FROM HOTELS";
        try (PreparedStatement st = AbstractDao.getConnection().prepareStatement(query)) {
            ResultSet result = st.executeQuery();
            if (result.next()) total = result.getInt("total_hotels");
        }
        return total;
    }
}