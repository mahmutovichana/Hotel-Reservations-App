/**
 The Reservation class represents a reservation with its reservation ID, check in, check out, total, adults, children, room ID and username of the user.
 It has getters and setters for each of its attributes, as well as methods for checking equality and generating a string representation of the object.
 */

package ba.unsa.etf.rpr.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

/**
 * The type Reservation.
 */
public class Reservation implements Idable{
    private int id;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private int total;
    private int adults;
    private int children;
    private Room roomId;
    private User username;

    /**
     * Instantiates a new Reservation.
     */
    public Reservation() { }

    /**
     * Instantiates a new Reservation.
     *
     * @param id       the id
     * @param checkIn  the check in
     * @param checkOut the check out
     * @param total    the total
     * @param adults   the adults
     * @param children the children
     * @param roomId   the room id
     * @param username the username
     */
    public Reservation(int id, LocalDate checkIn, LocalDate checkOut, int total, int adults, int children, Room roomId, User username) {
        this.id = id;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.total = total;
        this.adults = adults;
        this.children = children;
        this.roomId = roomId;
        this.username = username;
    }

    public Reservation(LocalDate checkIn, LocalDate checkOut, int total, int adults, int children, Room roomId, User username) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.total = total;
        this.adults = adults;
        this.children = children;
        this.roomId = roomId;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets check in.
     *
     * @return the check in
     */
    public LocalDate getCheckIn() {
        return checkIn;
    }

    /**
     * Sets check in.
     *
     * @param checkIn the check in
     */
    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    /**
     * Gets check out.
     *
     * @return the check out
     */
    public LocalDate getCheckOut() {
        return checkOut;
    }

    /**
     * Sets check out.
     *
     * @param checkOut the check out
     */
    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    /**
     * Gets total.
     *
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * Sets total.
     *
     * @param total the total
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * Gets adults.
     *
     * @return the adults
     */
    public int getAdults() {
        return adults;
    }

    /**
     * Sets adults.
     *
     * @param adults the adults
     */
    public void setAdults(int adults) {
        this.adults = adults;
    }

    /**
     * Gets children.
     *
     * @return the children
     */
    public int getChildren() {
        return children;
    }

    /**
     * Sets children.
     *
     * @param children the children
     */
    public void setChildren(int children) {
        this.children = children;
    }

    /**
     * Gets room id.
     *
     * @return the room id
     */
    public Room getRoomId() {
        return roomId;
    }

    /**
     * Sets room id.
     *
     * @param roomId the room id
     */
    public void setRoomId(Room roomId) {
        this.roomId = roomId;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public User getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(User username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return id == that.id && total == that.total && adults == that.adults && children == that.children && checkIn.equals(that.checkIn) && checkOut.equals(that.checkOut) && roomId.equals(that.roomId) && username.equals(that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, total, adults, children, checkIn, checkOut, roomId, username);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
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
