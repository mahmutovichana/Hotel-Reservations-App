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

    @Override
    public Room getByUsername(String username) {
        return null;
    }

    @Override
    public Room add(Room room) {
        String insert = "INSERT INTO ROOMS(type,capacity,hasAirConditioning,status,hotel_id) VALUES(?,?,?,?,?)";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, room.getType());
            stmt.setInt(2,room.getCapacity());
            stmt.setInt(3,room.getHasAirConditioning());
            stmt.setInt(4,room.getStatus());
            stmt.setInt(5,room.getHotelId().getHotelId());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next(); // we know that there is one key
            room.setRoomId(rs.getInt(1)); //set username to return it back
            return room;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Room update(Room room) {
        String insert = "UPDATE ROOMS SET type = ?, capacity = ?, hasAirConditioning = ?, status = ?, hotel_id = ? WHERE room_id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, room.getType());
            stmt.setObject(2, room.getCapacity());
            stmt.setObject(3, room.getHasAirConditioning());
            stmt.setObject(4, room.getStatus());
            stmt.setObject(5, room.getHotelId());
            stmt.setObject(6, room.getRoomId());
            stmt.executeUpdate();
            return room;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Room room) {
        String insert = "DELETE FROM ROOMS WHERE room_id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, room.getRoomId());
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Room> getAll() {
        String query = "SELECT * FROM ROOMS";
        List<Room> rooms = new ArrayList<>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                Room room = new Room();
                room.setRoomId(rs.getInt("room_id"));
                room.setType(rs.getString("type"));
                room.setCapacity(rs.getInt("capacity"));
                room.setHasAirConditioning(rs.getInt("hasAirConditioning"));
                room.setStatus(rs.getInt("status"));
                room.setHotelId(new HotelDaoSQLImpl().getById(rs.getInt("hotel_id")));
                rooms.add(room);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return rooms;
    }

}