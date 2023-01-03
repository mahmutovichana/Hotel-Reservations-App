package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Hotel;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Dao interface for Hotel domain bean
 *
 * @author Hana Mahmutovic
 */
public interface HotelDao extends Dao<Hotel>{

    /* here we insert methods that are special only for that table, the implementation of it we do in SQL Impl file */

    public List<String> getAllNames();

}
