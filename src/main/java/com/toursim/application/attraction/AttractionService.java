package com.toursim.application.attraction;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttractionService {

    @Autowired
    private AttractionRepository attractionRepository;

    public List<Attraction> getAttractions() {
        return attractionRepository.findAll();
    }

    public Attraction getAttraction (int id) {
         Optional<Attraction> foundAttraction = attractionRepository.findById(id);
         if(foundAttraction.isPresent()){
             return foundAttraction.get();
         }else{
             throw new RuntimeException("The Attraction is not found for the id" + id);
         }
    }

    public Attraction saveAttraction(Attraction attraction){
        return attractionRepository.save(attraction);
    }

    public Attraction updateAttraction(int id, Attraction attraction){
        return attractionRepository.findById(id)
                .map(updatedAttraction ->{
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
