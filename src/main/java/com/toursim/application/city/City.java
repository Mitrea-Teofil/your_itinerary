package com.toursim.application.city;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.toursim.application.attraction.Attraction;
import com.toursim.application.base.RateableEntity;
import com.toursim.application.itinerary.Itinerary;
import com.toursim.application.rating.Rating;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "City")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class City extends RateableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Continent continent;

    @OneToMany(
            mappedBy = "city",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Itinerary> itineraries;

    @OneToMany(
            mappedBy = "city",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    protected List<Rating> ratings;

    @OneToMany(
            mappedBy = "city",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Attraction> attractions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Itinerary> getItineraries() {
        return itineraries;
    }

    public void setItineraries(List<Itinerary> itineraries) {
        this.itineraries = itineraries;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    @Override
    @JsonIgnore
    public List<Rating> getRatings() {
        return ratings;
    }

    @Override
    @JsonIgnore
    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    @JsonIgnore
    public List<Attraction> getAttractions() {
        return attractions;
    }

    @JsonIgnore
    public void setAttractions(List<Attraction> attractions) {
        this.attractions = attractions;
    }
}
