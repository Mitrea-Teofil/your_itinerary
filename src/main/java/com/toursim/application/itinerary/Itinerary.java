package com.toursim.application.itinerary;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.toursim.application.attraction.Attraction;
import com.toursim.application.base.RateableEntity;
import com.toursim.application.rating.Rating;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Itinerary")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Itinerary extends RateableEntity {

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

    @OneToMany(
            mappedBy = "itinerary",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    protected List<Rating> ratings;

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
