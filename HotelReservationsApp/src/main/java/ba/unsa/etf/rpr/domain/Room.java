/**
 The Room class represents a hotel room with its room ID, type, capacity, hasAirConditioning attribute, status and hotel ID.
 It has getters and setters for each of its attributes, as well as methods for checking equality and generating a string representation of the object.
 */

package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * The type Room.
 */
public class Room implements Idable{
    private int id;
    private String type;
    private int capacity;
    private int hasAirConditioning;
    private int status;
    private Hotel hotelId;
    private int price;

    /**
     * Instantiates a new Room.
     *
     * @param type               the type
     * @param capacity           the capacity
     * @param hasAirConditioning the has air conditioning
     * @param status             the status
     * @param hotelId            the hotel id
     * @param price              the price
     */
    public Room(String type, int capacity, int hasAirConditioning, int status, Hotel hotelId, int price) {
        this.type = type;
        this.capacity = capacity;
        this.hasAirConditioning = hasAirConditioning;
        this.status = status;
        this.hotelId = hotelId;
        this.price = price;
    }

    /**
     * Instantiates a new Room.
     */
    public Room() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets capacity.
     *
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets capacity.
     *
     * @param capacity the capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Gets has air conditioning.
     *
     * @return the has air conditioning
     */
    public int getHasAirConditioning() {
        return hasAirConditioning;
    }

    /**
     * Sets has air conditioning.
     *
     * @param hasAirConditioning the has air conditioning
     */
    public void setHasAirConditioning(int hasAirConditioning) {
        this.hasAirConditioning = hasAirConditioning;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Gets hotel id.
     *
     * @return the hotel id
     */
    public Hotel getHotelId() {
        return hotelId;
    }

    /**
     * Sets hotel id.
     *
     * @param hotelId the hotel id
     */
    public void setHotelId(Hotel hotelId) {
        this.hotelId = hotelId;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id && capacity == room.capacity && hasAirConditioning == room.hasAirConditioning && status == room.status && Double.compare(room.price, price) == 0 && type.equals(room.type) && hotelId.equals(room.hotelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, capacity, hasAirConditioning, status, hotelId, price);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", capacity=" + capacity +
                ", hasAirConditioning=" + hasAirConditioning +
                ", status=" + status +
                ", hotelId=" + hotelId +
                ", price=" + price +
                '}';
    }
}
