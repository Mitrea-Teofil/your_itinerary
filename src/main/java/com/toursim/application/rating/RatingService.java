package com.toursim.application.rating;

import com.toursim.application.attraction.Attraction;
import com.toursim.application.city.City;
import com.toursim.application.itinerary.Itinerary;
import com.toursim.application.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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

    @Autowired
    private UserRepository userRepository;
	@PersistenceContext
	EntityManager entityManager;

    public List<RRating> getRatings() {
        return ratingRepository.findAll().stream().map(RatingAdapter::toClientModel).collect(Collectors.toList());
    }

    public RRating saveRating(RRating rating) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = currentUser.getUsername();
        com.toursim.application.user.User user = userRepository.findByEmail(username);

        Rating serverModel = RatingAdapter.toServerModel(rating);
        serverModel.setUser(user);

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
