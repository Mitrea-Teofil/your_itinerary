package com.toursim.application.itinerary;

import com.toursim.application.attraction.Attraction;
import com.toursim.application.base.RateableEntity;
import com.toursim.application.itAtRelationship.ItAtRelationship;

import javax.persistence.*;
import java.util.List;

@Entity(name = "itinerary")
public class Itinerary extends RateableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(nullable = false)
    int numberDays;
    @Column(nullable = false)
    String description;
    @Column(nullable = false)
    double price;
    @Column(nullable = false)
    String guideName;
    @ManyToMany
    @JoinTable(
            name = "it_at_relationship",
            joinColumns = @JoinColumn(name = "id_itinerary"),
            inverseJoinColumns = @JoinColumn(name = "id_attraction"))
    List<Attraction> attractions;

    public int getId() {
        return id;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGuideName() {
        return guideName;
    }

    public void setGuideName(String guideName) {
        this.guideName = guideName;
    }

    public List<Attraction> getAttractions() {
        return attractions;
    }

    public void setAttractions(List<Attraction> attractions) {
        this.attractions = attractions;
    }
}
