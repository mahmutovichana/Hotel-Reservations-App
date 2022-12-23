package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class Room {
    private int roomId;
    private String type;
    private int capacity;
    private int hasAirConditioning;
    private int status;
    private Hotel hotelId;

    public Room(int roomId, String type, int capacity, int hasAirConditioning, int status, Hotel hotelId) {
        this.roomId = roomId;
        this.type = type;
        this.capacity = capacity;
        this.hasAirConditioning = hasAirConditioning;
        this.status = status;
        this.hotelId = hotelId;
    }

    public Room() {}

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
        if(hasAirConditioning!=0) return 1; else return 0;
    }

    public void setHasAirConditioning(int hasAirConditioning) {this.hasAirConditioning = hasAirConditioning;}

    public int getStatus() {
        if(status!=0) return 1; else return 0;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomId == room.roomId && capacity == room.capacity && hasAirConditioning == room.hasAirConditioning && status == room.status && hotelId == room.hotelId && type.equals(room.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, type, capacity, hasAirConditioning, status, hotelId);
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
                '}';
    }
}
