package com.toursim.application.rating;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.toursim.application.attraction.Attraction;
import com.toursim.application.city.City;
import com.toursim.application.itinerary.Itinerary;
import com.toursim.application.user.User;

import javax.persistence.*;

@Entity
@Table(name = "Rating")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    private int stars;
    @Column
    private String comment;

    @OneToOne(mappedBy = "rating")
    private User user;

    @ManyToOne
    @JoinColumn(name = "itinerary_id")
    private Itinerary itinerary;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "attraction_id")
    private Attraction attraction;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getStars() {
        return stars;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public Itinerary getItinerary() {
        return itinerary;
    }

    public void setItinerary(Itinerary itinerary) {
        this.itinerary = itinerary;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Attraction getAttraction() {
        return attraction;
    }

    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }
}