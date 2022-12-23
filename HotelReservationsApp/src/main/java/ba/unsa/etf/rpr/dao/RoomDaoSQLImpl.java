package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Room;

import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RoomDaoSQLImpl implements RoomDao {

    private Connection connection;

    public RoomDaoSQLImpl() {
        try {
            FileReader reader = new FileReader("src/main/resources/db.properties");
            Properties p = new Properties();
            p.load(reader);
            String s1 = p.getProperty("username"), s2 = p.getProperty("password"), s3 = p.getProperty("server");
            this.connection = DriverManager.getConnection(s3, s1, s2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Room getById(int id) {
        String query = "SELECT * FROM ROOMS WHERE room_id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){ // result set is iterator.
                Room room = new Room();
                room.setRoomId(rs.getInt("room_id"));
                room.setType(rs.getString("type"));
                room.setCapacity(rs.getInt("capacity"));
                room.setHasAirConditioning(rs.getInt("hasAirConditioning"));
                room.setStatus(rs.getInt("status"));
                room.setHotelId(new HotelDaoSQLImpl().getById(rs.getInt(1)));
                rs.close();
                return room;
            }else{
                return null; // if there is no elements in the result set return null
            }
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return null;
    }

}