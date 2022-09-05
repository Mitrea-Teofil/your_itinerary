package com.toursim.application.rating;

public abstract class RatingAdapter
{
    public static Rating toServerModel(RRating rRating) {
        Rating rating = new Rating();
        rating.setComment(rRating.getComment());
        rating.setStars(rRating.getStars());
        return rating;
    }

    public static RRating toClientModel(Rating rating) {
        RRating rRating = new RRating();
        rRating.setUserName(rating.getUser().getFirstName() + " " + rating.getUser().getLastName());
        rRating.setComment(rating.getComment());
        rRating.setStars(rating.getStars());
        return rRating;
    }
}
