package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Hotel;

import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class HotelDaoSQLImpl implements HotelDao {

    private Connection connection;

    public HotelDaoSQLImpl(){
        try{
            FileReader reader = new FileReader("src/main/resources/db.properties");
            Properties p = new Properties();
            p.load(reader);
            String s1 = p.getProperty("username"), s2 = p.getProperty("password"), s3 = p.getProperty("server");
            this.connection = DriverManager.getConnection(s3, s1, s2);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Hotel getByUsername(String username) {
        String query = "SELECT * FROM HOTELS WHERE name = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){ // result set is iterator.
                Hotel hotel = new Hotel();
                hotel.setHotelId(rs.getInt("hotel_id"));
                hotel.setName(rs.getString("name"));
                hotel.setZipCode(rs.getInt("zipCode"));
                hotel.setCity(rs.getString("city"));
                hotel.setCountry(rs.getString("country"));
                hotel.setStarRating(rs.getInt("starRating"));
                rs.close();
                return hotel;
            }else{
                return null; // if there is no elements in the result set return null
            }
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return null;
    }

    public Hotel getById(int hotelId){
        String query = "SELECT * FROM HOTELS WHERE hotel_id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, hotelId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){ // result set is iterator.
                Hotel hotel = new Hotel();
                hotel.setHotelId(rs.getInt("hotel_id"));
                hotel.setName(rs.getString("name"));
                hotel.setZipCode(rs.getInt("zipCode"));
                hotel.setCity(rs.getString("city"));
                hotel.setCountry(rs.getString("country"));
                hotel.setStarRating(rs.getInt("starRating"));
                rs.close();
                return hotel;
            }else{
                return null; // if there is no elements in the result set return null
            }
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return null;
    }

    @Override
    public Hotel add(Hotel hotel) {
        String insert = "INSERT INTO HOTELS(name,zipCode,city,country,starRating) VALUES(?,?,?,?,?)";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,hotel.getName());
            stmt.setInt(2,hotel.getZipCode());
            stmt.setString(3,hotel.getCity());
            stmt.setString(4,hotel.getCountry());
            stmt.setInt(5,hotel.getStarRating());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            while(rs.next()){
                hotel.setName(rs.getString(2)); //set username to return it back
            }
            //rs.next(); // we know that there is one key

            return hotel;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Hotel update(Hotel hotel) {
        String insert = "UPDATE HOTELS SET zipCode = ?, city = ?, country = ?, starRating = ? WHERE name = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, hotel.getZipCode());
            stmt.setObject(2, hotel.getCity());
            stmt.setObject(3, hotel.getCountry());
            stmt.setObject(4, hotel.getStarRating());
            stmt.setObject(5, hotel.getName());
            stmt.executeUpdate();
            return hotel;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Hotel hotel) {
        String insert = "DELETE FROM HOTELS WHERE hotel_id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, hotel.getHotelId());
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Hotel> getAll() {
        String query = "SELECT * FROM HOTELS";
        List<Hotel> hotels = new ArrayList<>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                Hotel hotel = new Hotel();
                hotel.setHotelId(rs.getInt("hotel_id"));
                hotel.setName(rs.getString("name"));
                hotels.add(hotel);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return hotels;
    }

}