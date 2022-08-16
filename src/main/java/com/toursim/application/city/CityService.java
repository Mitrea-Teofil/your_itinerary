package com.toursim.application.city;

import com.toursim.application.base.Utilities;
import com.toursim.application.itinerary.RItinerary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<City> getCities() {
        return cityRepository.findAll();
    }

    public List<City> getCitiesByContinent(String continent) {
        return cityRepository.findCitiesByContinent(continent);
    }

    public City getCity(int id) {
        Optional<City> foundCity = cityRepository.findById(id);
        if (foundCity.isPresent()) {
            return foundCity.get();
        } else {
            throw new RuntimeException("City is not found for the id " + id);
        }
    }

    public City saveCity(City city) {
        return cityRepository.save(city);
    }

    public City updateCity(int id, City city) {
        return cityRepository.findById(id)
                .map(updatedCity -> {
                    updatedCity.setName(city.getName());
                    updatedCity.setDescription(city.getDescription());
                    updatedCity.setPicture(city.getPicture());
                    return cityRepository.save(updatedCity);
                })
                .orElseGet(() -> {
                    city.setId(id);
                    return cityRepository.save(city);
                });
    }

    public void deleteCity(int id) {
        cityRepository.deleteById(id);
    }

    public List<RItinerary> getCityItineraries(int id) {
        City city = cityRepository.getById(id);
        return getCityItineraries(city);
    }
    public List<RItinerary> getCityItineraries(City city) {
        if(city != null){
            return city.getItineraries().stream().map(Utilities::prepareItineraryForClient).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}