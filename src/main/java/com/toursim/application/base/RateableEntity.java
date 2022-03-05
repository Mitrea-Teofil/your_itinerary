package com.toursim.application.base;

import com.toursim.application.rating.Rating;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@MappedSuperclass
public abstract class RateableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public abstract List<Rating> getRatings();

    public abstract void setRatings(List<Rating> ratings);

    public void addRating(Rating rating) {
        List<Rating> ratings = getRatings();

        if (ratings == null) {
            List<Rating> newRatings = new ArrayList<>();
            newRatings.add(rating);
            this.setRatings(newRatings);
            return;
        }

        ratings.add(rating);
    }
}
