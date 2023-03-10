package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.HotelException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL Implementation of DAO
 *
 * @author Hana MahmutoviÄ‡
 */
public class UserDaoSQLImpl extends AbstractDao<User> implements UserDao {

    private static UserDaoSQLImpl instance = null;
    private UserDaoSQLImpl() {
        super("USERS");
    }

    /**
     * Get instance user dao sql.
     *
     * @return QuoteDaoSQLImpl We don't need more than one object for CRUD operations on table 'quotes' so we will use Singleton This method will call private constructor in instance==null and then return that instance
     * @author Hana MahmutoviÄ‡
     */
    public static UserDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new UserDaoSQLImpl();
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
    public User row2object(ResultSet rs) throws HotelException{
        try {
            User person = new User();
            person.setId(rs.getInt("id"));
            person.setUsername(rs.getString("username"));
            person.setFirstName(rs.getString("first_name"));
            person.setLastName(rs.getString("last_name"));
            person.setEmail(rs.getString("email"));
            person.setRole(rs.getInt("role"));
            person.setPassword(rs.getString("password"));
            return person;
        } catch (Exception e) {
            throw new HotelException(e.getMessage(), e);
        }
    }

    /**
     * @param object - object to be mapped
     * @return map representation of object
     */
    @Override
    public Map<String, Object> object2row(User object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("username", object.getUsername());
        item.put("first_name", object.getFirstName());
        item.put("last_name", object.getLastName());
        item.put("email", object.getEmail());
        item.put("role", object.getRole());
        item.put("password", object.getPassword());
        return item;
    }

    @Override
    public User findUsername(String usernameField) throws HotelException{
        String find = "SELECT id from USERS where username='" + usernameField +"'";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(find, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) { // result set is iterator.
                return getById(rs.getInt(1));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int totalUsers() throws SQLException{
        int total = 0;
        String query = "SELECT count(username) AS total_users FROM USERS";
        try (PreparedStatement st = AbstractDao.getConnection().prepareStatement(query)) {
            ResultSet result = st.executeQuery();
            if (result.next()) total = result.getInt("total_users");
        }
        return total;
    }
}