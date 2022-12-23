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

}