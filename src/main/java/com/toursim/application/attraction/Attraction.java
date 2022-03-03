package com.toursim.application.attraction;

import com.toursim.application.base.RateableEntity;
import com.toursim.application.itAtRelationship.ItAtRelationship;
import com.toursim.application.itinerary.Itinerary;

import javax.persistence.*;
import java.util.List;

@Entity(name = "attraction")
public class Attraction extends RateableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(nullable = false)
    String name;
    String description;
    @Column(nullable = false)
    String picture;
    @Column(nullable = false)
    double price;
    @ManyToMany(mappedBy = "itineraries")
    List<Itinerary> itineraries;

    public int getId() {
        return id;
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

    public List<Itinerary> getItineraries() {
        return itineraries;
    }

    public void setItineraries(List<Itinerary> itineraries) {
        this.itineraries = itineraries;
    }
}
