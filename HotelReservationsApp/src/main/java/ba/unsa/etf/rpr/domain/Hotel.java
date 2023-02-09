/**
 The Hotel class represents a hotel with its name, zip code, city, country, and star rating.
 It has getters and setters for each of its attributes, as well as methods for checking equality and generating a string representation of the object.
 */

package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class Hotel implements Idable{
    private int id;
    private String name;
    private int zipCode;
    private String city;
    private String country;
    private int starRating;

    public Hotel(){}

    public Hotel(String name, int zipCode, String city, String country, int starRating) {
        this.name = name;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.starRating = starRating;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getStarRating() {
        return starRating;
    }

    public void setStarRating(int starRating) {
        this.starRating = starRating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return id == hotel.id && name.equals(hotel.name) && zipCode == hotel.zipCode && starRating == hotel.starRating && city.equals(hotel.city) && country.equals(hotel.country);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", zipCode=" + zipCode +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", starRating=" + starRating +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, zipCode, city, country, starRating);
    }

}
