/**
 The Hotel class represents a hotel with its name, zip code, city, country, and star rating.
 It has getters and setters for each of its attributes, as well as methods for checking equality and generating a string representation of the object.
 */

package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * The type Hotel.
 */
public class Hotel implements Idable{
    private int id;
    private String name;
    private int zipCode;
    private String city;
    private String country;
    private int starRating;

    /**
     * Instantiates a new Hotel.
     */
    public Hotel(){}

    /**
     * Instantiates a new Hotel.
     *
     * @param name       the name
     * @param zipCode    the zip code
     * @param city       the city
     * @param country    the country
     * @param starRating the star rating
     */
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

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() { return name; }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) { this.name = name; }

    /**
     * Gets zip code.
     *
     * @return the zip code
     */
    public int getZipCode() {
        return zipCode;
    }

    /**
     * Sets zip code.
     *
     * @param zipCode the zip code
     */
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets country.
     *
     * @param country the country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets star rating.
     *
     * @return the star rating
     */
    public int getStarRating() {
        return starRating;
    }

    /**
     * Sets star rating.
     *
     * @param starRating the star rating
     */
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
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, zipCode, city, country, starRating);
    }

}
