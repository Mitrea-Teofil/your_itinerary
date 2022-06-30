package com.toursim.application.itinerary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItineraryService {

    @Autowired
    private ItineraryRepository itineraryRepository;

    public List<Itinerary> getItineraries() {
        return itineraryRepository.findAll();
    }

    public Itinerary getItinerary(int id) {
        Optional<Itinerary> foundItinerary = itineraryRepository.findById(id);
        if(foundItinerary.isPresent()){
            return foundItinerary.get();
        }else{
            throw new RuntimeException("itinerary is not found for the id " + id);
        }
    }

    public Itinerary saveItinerary(Itinerary itinerary) {
        return itineraryRepository.save(itinerary);
    }

    public Itinerary updateItinerary(int id, Itinerary itinerary) {
        return itineraryRepository.findById(id)
                .map(updatedItinerary -> {
                    updatedItinerary.setNumberDays(itinerary.getNumberDays());
                    updatedItinerary.setDescription(itinerary.getDescription());
                    updatedItinerary.setGuideName(itinerary.getGuideName());
                    updatedItinerary.setPicture(itinerary.getPicture());
                    updatedItinerary.setAttractions(itinerary.getAttractions());
                    updatedItinerary.setRatings(itinerary.getRatings());
                    return itineraryRepository.save(updatedItinerary);
                })
                .orElseGet(() ->{
                    itinerary.setId(id);
                    return itineraryRepository.save(itinerary);
                });
    }
    public void deleteItinerary(int id) {
        itineraryRepository.deleteById(id);
    }

    private void isValid(Itinerary itinerary){

        itinerary.getAttractions().stream().allMatch(attraction -> attraction.getCity().getId() == (itinerary.getCity().getId()));
    }
}
