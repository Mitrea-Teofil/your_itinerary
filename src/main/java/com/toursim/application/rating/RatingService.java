package com.toursim.application.rating;

import com.toursim.application.attraction.Attraction;
import com.toursim.application.city.City;
import com.toursim.application.itinerary.Itinerary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;
	@PersistenceContext
	EntityManager entityManager;

    public List<RRating> getRatings() {
        return ratingRepository.findAll().stream().map(RatingAdapter::toClientModel).collect(Collectors.toList());
    }

    public RRating getRating(int id) {
        Optional<Rating> foundRating = ratingRepository.findById(id);
        if(foundRating.isPresent()){
            return RatingAdapter.toClientModel(foundRating.get());
        }else{
            throw new RuntimeException("The rating doesn't exist");
        }
    }

    public RRating saveRating(RRating rating) {
        Rating serverModel = RatingAdapter.toServerModel(rating);

        if(rating.getItineraryId() != 0) {
			Itinerary itineraryReference = entityManager.getReference(Itinerary.class, rating.getItineraryId());
			serverModel.setItinerary(itineraryReference);
        } else if(rating.getAttractionId() != 0) {
			Attraction attractionReference = entityManager.getReference(Attraction.class, rating.getAttractionId());
			serverModel.setAttraction(attractionReference);
        }

        Rating saved = ratingRepository.save(serverModel);
        return RatingAdapter.toClientModel(saved);
    }

//    public RRating updateRating(int id, RRating rating) {
//        return ratingRepository.findById(id)
//                .map(updatedRating -> {
//                    updatedRating.setStars(rating.getStars());
//                    updatedRating.setComment(rating.getComment());
//                    updatedRating.setItinerary(rating.getItinerary());
//                    updatedRating.setCity(rating.getCity());
//                    updatedRating.setAttraction(rating.getAttraction());
//                    return ratingRepository.save(updatedRating);
//                })
//                .orElseGet(() -> {
//                    rating.setId(id);
//                    return ratingRepository.save(rating);
//                });
//    }

    public void deleteRating(int id) {
        ratingRepository.deleteById(id);
    }
}
