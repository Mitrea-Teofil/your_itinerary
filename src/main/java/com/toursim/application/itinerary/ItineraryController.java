package com.toursim.application.itinerary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/itineraries")
public class ItineraryController {
    @Autowired
    private ItineraryRepository repository;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Itinerary> addItinerary(@RequestBody Itinerary itinerary) {
        Itinerary saved = repository.save(itinerary);

        if (saved != null) {
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Itinerary>> getAll() {
        List<Itinerary> all = repository.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }
}
