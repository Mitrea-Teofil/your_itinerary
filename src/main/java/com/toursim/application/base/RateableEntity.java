package com.toursim.application.base;

import org.springframework.util.CollectionUtils;
import com.toursim.application.rating.Rating;

import java.util.ArrayList;
import java.util.List;

public abstract class RateableEntity {

    protected List<Rating> ratings;

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public void addRating(Rating rating) {
        if (CollectionUtils.isEmpty(ratings)) {
            ratings = new ArrayList<>();
        }
        ratings.add(rating);
    }
}
