package com.toursim.application.rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public List<Rating> getRatings() {
        return ratingRepository.findAll();
    }

    public Rating getRating(int id) {
        Optional<Rating> foundRating = ratingRepository.findById(id);
        if(foundRating.isPresent()){
            return foundRating.get();
        }else{
            throw new RuntimeException("The rating doesn't exist");
        }
    }

    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    public Rating updateRating(int id, Rating rating) {
        return ratingRepository.findById(id)
                .map(updatedRating -> {
                    updatedRating.setStars(rating.getStars());
                    updatedRating.setComment(rating.getComment());
                    updatedRating.setItinerary(rating.getItinerary());
                    updatedRating.setCity(rating.getCity());
                    updatedRating.setAttraction(rating.getAttraction());
                    return ratingRepository.save(updatedRating);
                })
                .orElseGet(() -> {
                    rating.setId(id);
                    return ratingRepository.save(rating);
                });
    }

    public void deleteRating(int id) {
        ratingRepository.deleteById(id);
    }
}
