package com.toursim.application.itinerary;

import com.toursim.application.base.Utilities;
import com.toursim.application.rating.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItineraryService {

    @Autowired
    private ItineraryRepository itineraryRepository;

    @Autowired
    private RatingRepository ratingRepository;

    public List<RItinerary> getItineraries() {
        List<Itinerary> all = itineraryRepository.findAll();
        List<RItinerary> rItineraries = all.stream().map(Utilities::prepareItineraryForClient).collect(Collectors.toList());
        return rItineraries;
    }

    public RItinerary getItinerary(int id) {
        Optional<Itinerary> foundItinerary = itineraryRepository.findById(id);
        if (foundItinerary.isPresent()) {
            Itinerary itinerary = foundItinerary.get();
            return Utilities.prepareItineraryForClient(itinerary);
        }
        throw new RuntimeException("itinerary is not found for the id " + id);
    }

    public RItinerary saveItinerary(Itinerary itinerary) {
        return ItineraryAdapter.toClientModel(itineraryRepository.save(itinerary));
    }

    public RItinerary updateItinerary(int id, Itinerary itinerary) {
        return itineraryRepository.findById(id)
                .map(updatedItinerary -> {
                    updatedItinerary.setName(itinerary.getName());
                    updatedItinerary.setNumberDays(itinerary.getNumberDays());
                    updatedItinerary.setDescription(itinerary.getDescription());
                    updatedItinerary.setPicture(itinerary.getPicture());
//                    updatedItinerary.setAttractions(itinerary.getAttractions());
                    updatedItinerary.setRatings(itinerary.getRatings());
                    return ItineraryAdapter.toClientModel(itineraryRepository.save(updatedItinerary));
                })
                .orElseGet(() -> {
                    itinerary.setId(id);
                    return ItineraryAdapter.toClientModel(itineraryRepository.save(itinerary));
                });
    }

    public void deleteItinerary(int id) {
        itineraryRepository.deleteById(id);
    }

    private void isValid(RItinerary itinerary) {
        itinerary.getAttractions().stream().allMatch(itrAtrRel -> itrAtrRel.getAttraction().getCity().getId() == (itinerary.getCity().getId()));
    }


}
