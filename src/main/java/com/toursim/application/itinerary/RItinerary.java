package com.toursim.application.itinerary;

import com.toursim.application.attraction.Attraction;
import com.toursim.application.city.City;
import com.toursim.application.rating.Rating;
import com.toursim.application.user.User;

import java.util.ArrayList;
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

    private int cityId;

    private User user;

    private List<Attraction> day1;
    private List<Attraction> day2;
    private List<Attraction> day3;

    protected List<Rating> ratings;


    public RItinerary() {
        day1 = new ArrayList<>();
        day2 = new ArrayList<>();
        day3 = new ArrayList<>();
    }

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

    public List<Attraction> getDay1() {
        return day1;
    }

    public void setDay1(List<Attraction> day1) {
        this.day1 = day1;
    }

    public List<Attraction> getDay2() {
        return day2;
    }

    public void setDay2(List<Attraction> day2) {
        this.day2 = day2;
    }

    public List<Attraction> getDay3() {
        return day3;
    }

    public void setDay3(List<Attraction> day3) {
        this.day3 = day3;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
