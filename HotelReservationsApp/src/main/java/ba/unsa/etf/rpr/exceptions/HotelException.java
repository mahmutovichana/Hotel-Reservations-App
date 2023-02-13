package ba.unsa.etf.rpr.exceptions;

/**
 * The type Hotel exception.
 */
public class HotelException extends Exception{

    /**
     * Instantiates a new Hotel exception.
     *
     * @param msg    the msg
     * @param reason the reason
     */
    public HotelException(String msg, Exception reason){
        super(msg, reason);
    }

    /**
     * Instantiates a new Hotel exception.
     *
     * @param msg the msg
     */
    public HotelException(String msg){
        super(msg);
    }
}