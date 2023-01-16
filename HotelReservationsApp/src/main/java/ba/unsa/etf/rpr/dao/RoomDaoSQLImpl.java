package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Room;
import ba.unsa.etf.rpr.exceptions.HotelException;

import java.sql.ResultSet;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL Implementation of DAO
 * @author Hana Mahmutović
 */
public class RoomDaoSQLImpl extends AbstractDao<Room> implements RoomDao {

    private static RoomDaoSQLImpl instance = null;
    private RoomDaoSQLImpl() {
        super("ROOMS");
    }

    /**
     * @author Hana Mahmutović
     * @return QuoteDaoSQLImpl
     * We don't need more than one object for CRUD operations on table 'quotes' so we will use Singleton
     * This method will call private constructor in instance==null and then return that instance
     */
    public static RoomDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new RoomDaoSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    @Override
    public Room row2object(ResultSet rs) throws HotelException{
        try {
            Room room = new Room();
            room.setId(rs.getInt("id"));
            room.setType(rs.getString("type"));
            room.setCapacity(rs.getInt("capacity"));
            room.setHasAirConditioning(rs.getInt("hasAirConditioning"));
            room.setStatus(rs.getInt("status"));
            room.setHotelId(DaoFactory.hotelDao().getById(rs.getInt("hotel_id")));
            room.setPrice(rs.getDouble("price"));
            return room;
        } catch (Exception e) {
            throw new HotelException(e.getMessage(), e);
        }
    }

    /**
     * @param object - object to be mapped
     * @return map representation of object
     */
    @Override
    public Map<String, Object> object2row(Room object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("type", object.getType());
        item.put("capacity", object.getCapacity());
        item.put("hasAirConditioning",object.getHasAirConditioning());
        item.put("status",object.getStatus());
        item.put("hotel_id",object.getHotelId().getId());
        item.put("price", object.getPrice());
        return item;
    }
}