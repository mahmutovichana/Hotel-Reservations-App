package ba.unsa.etf.rpr.exceptions;

public class HotelException extends Exception{

    public HotelException(String msg, Exception reason){
        super(msg, reason);
    }

    public HotelException(String msg){
        super(msg);
    }
}