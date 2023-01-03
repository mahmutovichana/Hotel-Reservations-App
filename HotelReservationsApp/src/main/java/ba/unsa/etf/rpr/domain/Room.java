package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class Room {
    private int roomId;
    private String type;
    private int capacity;
    private int hasAirConditioning;
    private int status;
    private Hotel hotelId;
    private double price;

    public Room(int roomId, String type, int capacity, int hasAirConditioning, int status, Hotel hotelId, double price) {
        this.roomId = roomId;
        this.type = type;
        this.capacity = capacity;
        this.hasAirConditioning = hasAirConditioning;
        this.status = status;
        this.hotelId = hotelId;
        this.price = price;
    }

    public Room() {
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomId == room.roomId && capacity == room.capacity && hasAirConditioning == room.hasAirConditioning && status == room.status && Double.compare(room.price, price) == 0 && type.equals(room.type) && hotelId.equals(room.hotelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, type, capacity, hasAirConditioning, status, hotelId, price);
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", type='" + type + '\'' +
                ", capacity=" + capacity +
                ", hasAirConditioning=" + hasAirConditioning +
                ", status=" + status +
                ", hotelId=" + hotelId +
                ", price=" + price +
                '}';
    }
}
