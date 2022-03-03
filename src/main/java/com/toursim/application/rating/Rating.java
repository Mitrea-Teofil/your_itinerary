package com.toursim.application.rating;

import com.toursim.application.base.RateableEntity;

import javax.persistence.*;

@Entity(name = "rating")
public class Rating extends RateableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(nullable = false)
    int stars;
    String comment;

    public int getStars() {
        return stars;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}