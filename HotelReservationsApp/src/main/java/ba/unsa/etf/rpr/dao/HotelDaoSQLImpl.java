package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Hotel;
import ba.unsa.etf.rpr.exceptions.HotelException;

import java.sql.*;
import java.util.*;

/**
 * MySQL Implementation of DAO
 *
 * @author Hana Mahmutović
 */
public class HotelDaoSQLImpl extends AbstractDao<Hotel> implements HotelDao {

    private static HotelDaoSQLImpl instance = null;
    private HotelDaoSQLImpl() {
        super("HOTELS");
    }

    /**
     * Get instance hotel dao sql.
     *
     * @return QuoteDaoSQLImpl  We don't need more than one object for CRUD operations on table 'quotes' so we will use Singleton This method will call private constructor in instance==null and then return that instance
     * @author Hana Mahmutović
     */
    public static HotelDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new HotelDaoSQLImpl();
        return instance;
    }

    /**
     * Remove instance.
     */
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
            try{hotel.setNoOfRooms(rs.getInt("no_rooms"));}catch (Exception e){}
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

    public int getByName(String hotelName){
        int id=0;
        try{
            // Prepare a statement to execute the query
            PreparedStatement statement = getConnection().prepareStatement("SELECT HOTELS.id FROM HOTELS WHERE name = ?");
            statement.setString(1, hotelName);
            // Execute the query and get the result set
            ResultSet resultSet = statement.executeQuery();
            // Iterate over the result set and add each hotel to the list
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public Set<String> fetchCities() {
            Set<String> cities = new HashSet<>();
            try{
                ResultSet resultSet = getConnection().createStatement().executeQuery("SELECT DISTINCT city FROM HOTELS");
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
        System.out.println(city);
        List<Hotel> hotels = new ArrayList<>();
        // Connect to the database
        try{
            // Prepare a statement to execute the query
            String query = "SELECT * FROM HOTELS WHERE city = ?";
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setString(1, city);
            System.out.println("usla u sqlimpl");
            // Execute the query and get the result set
            ResultSet resultSet = statement.executeQuery();
            // Iterate over the result set and add each hotel to the list
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int zipCode = resultSet.getInt("zipCode");
                String cityy = resultSet.getString("city");
                String country = resultSet.getString("country");
                int starRating = resultSet.getInt("starRating");
                Hotel hotel = new Hotel(name, zipCode, cityy, country, starRating);
                hotels.add(hotel);
                System.out.println(hotel.toString()+"postoji");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Return the list of hotels
        return hotels;
    }

    @Override
    public List<String> fetchHotelNames() {
        List<String> result = new ArrayList<>();
        String sql = "SELECT name FROM HOTELS";
            try {
                PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet resultSet = stmt.executeQuery();
            // Iterate over the result set and add each hotel to the list
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                result.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Return the list of hotel names
        return result;
    }

    public int totalHotels() throws SQLException{
        int total = 0;
        String query = "SELECT count(id) AS total_hotels FROM HOTELS";
        try{
            ResultSet result = getConnection().prepareStatement(query).executeQuery();

            if (result.next()) total = result.getInt("total_hotels");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return total;
    }

    @Override
    public List<Hotel> getAll() throws HotelException{
        return NoOfRooms();
    }

    public List<Hotel> NoOfRooms() throws HotelException {
        String query = "SELECT h.*, COUNT(*) AS no_rooms FROM HOTELS h JOIN ROOMS r ON r.hotel_id = h.id\n" +
                "            GROUP BY h.id ORDER BY no_rooms DESC;";

        return this.executeQuery(query,null);

        /*try{
            String query = "SELECT h.*, COUNT(*) no_rooms FROM HOTELS h JOIN ROOMS r ON r.hotel_id = h.id\n" +
                    "            GROUP BY h.id ORDER BY no_rooms DESC";
            PreparedStatement statement = getConnection().prepareStatement(query);
            // Execute the query and get the result set
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()) rooms = resultSet.getInt("no_rooms");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return total;*/
    }


}