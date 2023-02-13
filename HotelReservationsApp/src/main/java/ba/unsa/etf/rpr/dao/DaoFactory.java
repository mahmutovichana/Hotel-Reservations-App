package ba.unsa.etf.rpr.dao;

/**
 * Factory method for singleton implementation of DAOs
 *
 * @author Hana Mahmutovic
 */
public class DaoFactory {

    private static final HotelDao hotelDao = HotelDaoSQLImpl.getInstance();
    private static final RoomDao roomDao = RoomDaoSQLImpl.getInstance();
    private static final UserDao userDao = UserDaoSQLImpl.getInstance();
    private static final ReservationDao reservationDao = ReservationDaoSQLImpl.getInstance();


    private DaoFactory(){
    }

    /**
     * Hotel dao hotel dao.
     *
     * @return the hotel dao
     */
    public static HotelDao hotelDao(){
        return hotelDao;
    }

    /**
     * Reservation dao reservation dao.
     *
     * @return the reservation dao
     */
    public static ReservationDao reservationDao(){
        return reservationDao;
    }

    /**
     * Room dao room dao.
     *
     * @return the room dao
     */
    public static RoomDao roomDao(){
        return roomDao;
    }

    /**
     * User dao user dao.
     *
     * @return the user dao
     */
    public static UserDao userDao(){
        return userDao;
    }

}