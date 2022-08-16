package com.toursim.application.attraction;


import com.toursim.application.city.City;
import com.toursim.application.city.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AttractionService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private AttractionRepository attractionRepository;

    @Autowired
    private CityRepository cityRepository;

    public List<Attraction> getAttractionsForCity() {
        return attractionRepository.findAll();
    }

    public List<Attraction> getAttractionsForCity(int cityId) {
        City city = cityRepository.getById(cityId);
        if (city == null) {
            return Collections.emptyList();
        }
        return city.getAttractions();
    }

    public Attraction getAttraction(int id) {
        Optional<Attraction> foundAttraction = attractionRepository.findById(id);
        if (foundAttraction.isPresent()) {
            return foundAttraction.get();
        } else {
            throw new RuntimeException("The Attraction is not found for the id" + id);
        }
    }

    public Attraction saveAttraction(RAttraction rAttraction) {
        Attraction attraction = AttractionAdapter.toServerModel(rAttraction);
        City cityReference = entityManager.getReference(City.class, rAttraction.getCityId());
        attraction.setCity(cityReference);
        return attractionRepository.save(attraction);
    }

    public Attraction updateAttraction(int id, Attraction attraction) {
        return attractionRepository.findById(id)
                .map(updatedAttraction -> {
                    updatedAttraction.setName(attraction.getName());
                    updatedAttraction.setDescription((attraction.getDescription()));
                    updatedAttraction.setPicture(attraction.getPicture());
                    updatedAttraction.setPrice((attraction.getPrice()));
//                    updatedAttraction.setItineraries(attraction.getItineraries());
//                    updatedAttraction.setRatings(attraction.getRatings());
//                    updatedAttraction.setCity((attraction.getCity()));
                    return attractionRepository.save(updatedAttraction);
                })
                .orElseGet(() -> {
                    attraction.setId(id);
                    return attractionRepository.save(attraction);
                });
    }

    public void deleteAttraction(int id) {
        attractionRepository.deleteById(id);
    }
}
