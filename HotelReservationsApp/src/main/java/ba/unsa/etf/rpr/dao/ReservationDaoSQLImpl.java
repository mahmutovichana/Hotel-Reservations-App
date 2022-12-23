package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Reservation;

import java.io.FileReader;
import java.sql.*;
import java.util.*;

public class ReservationDaoSQLImpl implements ReservationDao {

    private Connection connection;

    public ReservationDaoSQLImpl() {
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
    public Reservation getById(int id) {
        String query = "SELECT * FROM RESERVATIONS WHERE reservation_id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){ // result set is iterator.
                Reservation reservation = new Reservation();
                reservation.setReservationId(rs.getInt("reservation_id"));
                reservation.setCheckIn(rs.getDate("checkIn"));
                reservation.setCheckOut(rs.getDate("checkOut"));
                reservation.setTotal(rs.getInt("total"));
                reservation.setChildren(rs.getInt("children"));
                reservation.setAdults(rs.getInt("adults"));
                reservation.setRoomId(new RoomDaoSQLImpl().getById(rs.getInt("room_id")));
                reservation.setUsername(new UserDaoSQLImpl().getByUsername(rs.getString("username")));
                rs.close();
                return reservation;
            }else{
                return null; // if there is no elements in the result set return null
            }
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return null;
    }

    @Override
    public Reservation getByUsername(String username) {
        return null;
    }

    @Override
    public Reservation add(Reservation reservation) {
        String insert = "INSERT INTO RESERVATIONS(checkIn,checkOut,total,adults,children,room_id,username) VALUES(?,?,?,?,?,?,?)";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setDate(1, reservation.getCheckIn());
            stmt.setDate(2, reservation.getCheckOut());
            stmt.setInt(3,  reservation.getTotal());
            stmt.setInt(4,  reservation.getAdults());
            stmt.setInt(5,  reservation.getChildren());
            stmt.setInt(6,  reservation.getRoomId().getRoomId());

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next(); // we know that there is one key
            reservation.setUsername(new UserDaoSQLImpl().getByUsername(rs.getString("username"))); //set username to return it back
            return reservation;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Reservation update(Reservation reservation) {
        String insert = "UPDATE RESERVATIONS SET room_id = ? AND checkIN = ? AND checkOut = ? AND adults = ? AND children = ? AND total = ? WHERE username = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, reservation.getRoomId().getRoomId());
            stmt.setObject(2, reservation.getCheckIn());
            stmt.setObject(3, reservation.getCheckOut());
            stmt.setObject(4, reservation.getAdults());
            stmt.setObject(5, reservation.getChildren());
            stmt.setObject(6, reservation.getTotal());
            stmt.executeUpdate();
            return reservation;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

}