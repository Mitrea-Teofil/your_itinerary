package com.toursim.application.city;

import com.toursim.application.rating.Rating;
import com.toursim.application.rating.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.HEAD)
    public ResponseEntity<City> cityExists(@PathVariable("id") Integer cityId) {

        Optional<City> optional = cityRepository.findById(cityId);
        if (!optional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<City>> getAll() {
        List<City> all = cityRepository.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<City> getCityById(@PathVariable("id") Integer id) {
        Optional<City> optional = cityRepository.findById(id);

        if (!optional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        City city = optional.get();
//        addRatings(city);
        return new ResponseEntity<>(city, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<City> addCity(@RequestBody City city) {
        City saved = cityRepository.save(city);

        if (saved != null) {
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    private void addRatings(City city) {
        List<Rating> ratingsByCityId = ratingRepository.getRatingsByCityId(city);
        city.setRatings(ratingsByCityId);
    }
}
