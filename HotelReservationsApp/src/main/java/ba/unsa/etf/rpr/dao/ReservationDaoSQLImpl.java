package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.exceptions.HotelException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL Implementation of DAO
 *
 * @author Hana Mahmutović
 */
public class ReservationDaoSQLImpl extends AbstractDao<Reservation> implements ReservationDao {

    private static ReservationDaoSQLImpl instance = null;
    private ReservationDaoSQLImpl() {
        super("RESERVATIONS");
    }

    /**
     * Get instance reservation dao sql.
     *
     * @return QuoteDaoSQLImpl We don't need more than one object for CRUD operations on table 'quotes' so we will use Singleton This method will call private constructor in instance==null and then return that instance
     * @author Hana Mahmutović
     */
    public static ReservationDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new ReservationDaoSQLImpl();
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
    public Reservation row2object(ResultSet rs) throws HotelException{
        try {
            Reservation reservation = new Reservation();
            reservation.setId(rs.getInt("id"));
            reservation.setAdults(rs.getInt("adults"));
            reservation.setChildren(rs.getInt("children"));
            reservation.setRoomId(DaoFactory.roomDao().getById(rs.getInt("room_id")));
            reservation.setUsername(DaoFactory.userDao().getById(rs.getInt("user_id")));
            reservation.setCheckIn(rs.getDate("checkIn").toLocalDate());
            reservation.setCheckOut(rs.getDate("checkOut").toLocalDate());
            reservation.setTotal(rs.getInt("total"));
            return reservation;
        } catch (Exception e) {
            throw new HotelException(e.getMessage(), e);
        }
    }

    /**
     * @param object - object to be mapped
     * @return map representation of object
     */
    @Override
    public Map<String, Object> object2row(Reservation object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("adults", object.getAdults());
        item.put("children", object.getChildren());
        item.put("total",object.getTotal());
        item.put("checkIn",object.getCheckIn());
        item.put("checkOut",object.getCheckOut());
        item.put("user_id", object.getUsername().getId());
        item.put("room_id",object.getRoomId().getId());
        return item;
    }

    public int totalIncome() throws SQLException {
        int totalIncome = 0;
        String query = "SELECT SUM(total) AS total_price FROM RESERVATIONS";
        try (PreparedStatement st = AbstractDao.getConnection().prepareStatement(query)) {
            ResultSet result = st.executeQuery();
            if (result.next()) totalIncome = result.getInt("total_price");
        }
        return totalIncome;
    }
}