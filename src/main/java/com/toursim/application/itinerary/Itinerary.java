package com.toursim.application.itinerary;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.toursim.application.attraction.Attraction;
import com.toursim.application.base.RateableEntity;
import com.toursim.application.city.City;
import com.toursim.application.rating.Rating;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Itinerary")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Itinerary extends RateableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private int numberDays;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String guideName;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

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

    public int getId() {
        return id;
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
