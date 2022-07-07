package com.toursim.application.base;

import com.toursim.application.itinerary.Itinerary;
import com.toursim.application.itinerary.ItineraryAdapter;
import com.toursim.application.itinerary.ItineraryAttractionRelationship;
import com.toursim.application.itinerary.RItinerary;
import com.toursim.application.rating.Rating;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Utilities {

    public static double averageRatings(RateableEntity entity) {
        List<Rating> ratingsByCityId = entity.getRatings();
        if (CollectionUtils.isEmpty(ratingsByCityId)) {
            return 0;
        }
        List<Integer> reviewsScores = ratingsByCityId.stream().map(rating -> rating.getStars()).collect(Collectors.toList());
        int numberOfTotalReviews = reviewsScores.size();

        return reviewsScores.stream().reduce(0, (acumulator, newValue) -> acumulator + newValue) / numberOfTotalReviews;
    }

    public static double getPriceForItinerary(Itinerary itinerary) {
        List<ItineraryAttractionRelationship> attractions = itinerary.getAttractionsRelationships();
        if (CollectionUtils.isEmpty(attractions)) {
            return 0;
        }
        return attractions.stream().map(itrAtrRel -> itrAtrRel.getAttraction().getPrice()).reduce(0.0, (acumulator, priceAttraction) -> acumulator + priceAttraction);

    }

    public static RItinerary prepareItineraryForClient(Itinerary itinerary) {
        RItinerary rItinerary = ItineraryAdapter.toClientModel(itinerary);
        rItinerary.setPrice(getPriceForItinerary(itinerary));
        rItinerary.setRating(Utilities.averageRatings(itinerary));
        return rItinerary;
    }
}
