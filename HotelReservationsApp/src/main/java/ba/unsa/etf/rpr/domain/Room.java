/**
 The Room class represents a hotel room with its room ID, type, capacity, hasAirConditioning attribute, status and hotel ID.
 It has getters and setters for each of its attributes, as well as methods for checking equality and generating a string representation of the object.
 */

package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class Room implements Idable{
    private int id;
    private String type;
    private int capacity;
    private int hasAirConditioning;
    private int status;
    private Hotel hotelId;
    private int price;

    public Room(String type, int capacity, int hasAirConditioning, int status, Hotel hotelId, int price) {
        this.type = type;
        this.capacity = capacity;
        this.hasAirConditioning = hasAirConditioning;
        this.status = status;
        this.hotelId = hotelId;
        this.price = price;
    }

    public Room() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getHasAirConditioning() {
        return hasAirConditioning;
    }

    public void setHasAirConditioning(int hasAirConditioning) {
        this.hasAirConditioning = hasAirConditioning;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Hotel getHotelId() {
        return hotelId;
    }

    public void setHotelId(Hotel hotelId) {
        this.hotelId = hotelId;
    }

    public int getPrice() {
        return price;
    }

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
