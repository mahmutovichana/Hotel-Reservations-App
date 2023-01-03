/**
 The Reservation class represents a reservation with its reservation ID, check in, check out, total, adults, children, room ID and username of the user.
 It has getters and setters for each of its attributes, as well as methods for checking equality and generating a string representation of the object.
 */

package ba.unsa.etf.rpr.domain;

import java.util.Date;
import java.util.Objects;

public class Reservation {
    private int reservationId;
    private Date checkIn;
    private Date checkOut;
    private int total;
    private int adults;
    private int children;
    private Room roomId;
    private User username;

    public Reservation() { }

    public Reservation(int reservationId, Date checkIn, Date checkOut, int total, int adults, int children, Room roomId, User username) {
        this.reservationId = reservationId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.total = total;
        this.adults = adults;
        this.children = children;
        this.roomId = roomId;
        this.username = username;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public java.sql.Date getCheckIn() {
        return (java.sql.Date) checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public java.sql.Date getCheckOut() {
        return (java.sql.Date) checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public Room getRoomId() {
        return roomId;
    }

    public void setRoomId(Room roomId) {
        this.roomId = roomId;
    }

    public User getUsername() {
        return username;
    }

    public void setUsername(User username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return reservationId == that.reservationId && total == that.total && adults == that.adults && children == that.children && checkIn.equals(that.checkIn) && checkOut.equals(that.checkOut) && roomId.equals(that.roomId) && username.equals(that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationId, checkIn, checkOut, total, adults, children, roomId, username);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", total=" + total +
                ", adults=" + adults +
                ", children=" + children +
                ", roomId=" + roomId +
                ", username=" + username +
                '}';
    }
}
