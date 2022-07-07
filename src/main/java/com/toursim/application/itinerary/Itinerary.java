package com.toursim.application.itinerary;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.toursim.application.base.RateableEntity;
import com.toursim.application.city.City;
import com.toursim.application.rating.Rating;
import com.toursim.application.user.User;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Itinerary")
@Table(name = "Itinerary")
@NaturalIdCache
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Itinerary extends RateableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int numberDays;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String picture;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(
            mappedBy = "itinerary",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<ItineraryAttractionRelationship> attractionsRelationships;

    @OneToMany(
            mappedBy = "itinerary",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    protected List<Rating> ratings;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberDays() {
        return numberDays;
    }

    public void setNumberDays(int numberDays) {
        this.numberDays = numberDays;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<ItineraryAttractionRelationship> getAttractionsRelationships() {
        return attractionsRelationships;
    }

    public void setAttractionsRelationships(List<ItineraryAttractionRelationship> attractionsRelationships) {
        this.attractionsRelationships = attractionsRelationships;
    }

    @Override
    @JsonIgnore
    public List<Rating> getRatings() {
        return this.ratings;
    }

    @Override
    @JsonIgnore
    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
