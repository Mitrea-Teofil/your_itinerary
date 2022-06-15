package com.toursim.application.itinerary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itineraries")
public class ItineraryController {
//    @Autowired
//    private ItineraryRepository repository;

    @Autowired
    private ItineraryService itineraryService;

//    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Itinerary> addItinerary(@RequestBody Itinerary itinerary) {
//        Itinerary saved = repository.save(itinerary);
//
//        if (saved != null) {
//            return new ResponseEntity<>(saved, HttpStatus.CREATED);
//        }
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }
//
//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity<List<Itinerary>> getAll() {
//        List<Itinerary> all = repository.findAll();
//        return new ResponseEntity<>(all, HttpStatus.OK);
//    }

    @GetMapping
    public List<Itinerary> getItineraries(){
        return itineraryService.getItineraries();
    }

    @GetMapping("/{id}")
    public Itinerary getItinerary(@PathVariable int id){
        return itineraryService.getItinerary(id);
    }

    @PostMapping()
    public Itinerary saveItinerary(@RequestBody Itinerary itinerary){
        return itineraryService.saveItinerary(itinerary);
    }

    @PutMapping("/{id}")
    public Itinerary updateItinerary(@PathVariable int id, @RequestBody Itinerary itinerary){
        return itineraryService.updateItinerary(id, itinerary);
    }

    @DeleteMapping
    public void deleteItinerary(@RequestParam("id") int id){
        itineraryService.deleteItinerary(id);
    }
}
