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

    public static HotelDao hotelDao(){
        return hotelDao;
    }

    public static ReservationDao reservationDao(){
        return reservationDao;
    }

    public static RoomDao roomDao(){
        return roomDao;
    }

    public static UserDao userDao(){
        return userDao;
    }

}