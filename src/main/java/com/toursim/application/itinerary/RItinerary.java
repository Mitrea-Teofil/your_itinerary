package com.toursim.application.itinerary;

import com.toursim.application.city.City;
import com.toursim.application.rating.Rating;
import com.toursim.application.user.User;

import java.util.List;

public class RItinerary {

    private int id;

    private String name;

    private int numberDays;

    private String description;

    private double price;

    private double rating;

    private String picture;

    private City city;

    private User user;

    List<ItineraryAttractionRelationship> attractions;

    protected List<Rating> ratings;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberDays() {
        return numberDays;
    }

    public void setNumberDays(int numberDays) {
        this.numberDays = numberDays;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ItineraryAttractionRelationship> getAttractions() {
        return attractions;
    }

    public void setAttractions(List<ItineraryAttractionRelationship> attractions) {
        this.attractions = attractions;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
