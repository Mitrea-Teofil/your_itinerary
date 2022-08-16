package com.toursim.application.itinerary;

import com.toursim.application.attraction.Attraction;
import com.toursim.application.base.Utilities;
import com.toursim.application.city.City;
import com.toursim.application.rating.RatingRepository;
import com.toursim.application.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItineraryService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private ItineraryRepository itineraryRepository;

    @Autowired
    private ItineraryAttractionRelationshipRepository itAtrRepository;

    @Autowired
    private UserRepository userRepository;

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

    public void saveItinerary(RItinerary rItinerary) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = currentUser.getUsername();
        com.toursim.application.user.User user = userRepository.findByEmail(username);

        City cityReference = entityManager.getReference(City.class, rItinerary.getCityId());

        Itinerary itinerary = ItineraryAdapter.toServerModel(rItinerary);
        itinerary.setCity(cityReference);
        itinerary.setUser(user);

        // First we save the itinerary
        Itinerary savedIt = itineraryRepository.save(itinerary);

        // Then we save te Itinerary-Attraction Relationships
        saveItineraryAttractionRelationships(rItinerary.getDay1(), savedIt, 1);
        saveItineraryAttractionRelationships(rItinerary.getDay2(), savedIt, 2);
        saveItineraryAttractionRelationships(rItinerary.getDay3(), savedIt, 3);
    }

    private void saveItineraryAttractionRelationships(List<Attraction> attractions, Itinerary itinerary, int day) {
        if(CollectionUtils.isEmpty(attractions)){
            return;
        }
        attractions.stream().forEach(attraction -> {
            Attraction atrReference = entityManager.getReference(Attraction.class, attraction.getId());
            ItineraryAttractionRelationship rel = new ItineraryAttractionRelationship(itinerary, atrReference, day);
            itAtrRepository.save(rel);
        });
    }

    public RItinerary updateItinerary(int id, Itinerary itinerary) {
        return itineraryRepository.findById(id)
                .map(updatedItinerary -> {
                    updatedItinerary.setName(itinerary.getName());
//                    updatedItinerary.setNumberDays(itinerary.getNumberDays());
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
}
