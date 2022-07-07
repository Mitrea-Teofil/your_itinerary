package com.toursim.application.attraction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.toursim.application.base.RateableEntity;
import com.toursim.application.city.City;
import com.toursim.application.itinerary.ItineraryAttractionRelationship;
import com.toursim.application.rating.Rating;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Attraction")
@Table(name = "attraction")
@NaturalIdCache
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Attraction extends RateableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(nullable = false)
    private String picture;

    @Column(nullable = false)
    private double price;

    @OneToMany(
            mappedBy = "attraction",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ItineraryAttractionRelationship> itineraryRelationship;

    @OneToMany(
            mappedBy = "attraction",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Rating> ratings;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<ItineraryAttractionRelationship> getItineraryRelationship() {
        return itineraryRelationship;
    }

    public void setItineraryRelationship(List<ItineraryAttractionRelationship> itineraryRelationship) {
        this.itineraryRelationship = itineraryRelationship;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
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
