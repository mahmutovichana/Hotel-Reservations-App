package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Hotel;
import ba.unsa.etf.rpr.exceptions.HotelException;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
}