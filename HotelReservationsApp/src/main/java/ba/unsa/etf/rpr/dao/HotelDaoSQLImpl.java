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


}