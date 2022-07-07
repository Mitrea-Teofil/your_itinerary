package com.toursim.application.itinerary;

import com.toursim.application.attraction.Attraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ItineraryController {

    @Autowired
    private ItineraryService itineraryService;


    @GetMapping("/itinerary/{id}")
    public String getItinerariesByCity(@PathVariable("id") int id, Model model) {

        RItinerary itinerary = itineraryService.getItinerary(id);
        List<ItineraryAttractionRelationship> attractions = itinerary.getAttractions();

        List<Attraction> day1 = new ArrayList<>();
        List<Attraction> day2 = new ArrayList<>();
        List<Attraction> day3 = new ArrayList<>();

        attractions.forEach(itineraryAttractionRelationship -> {
            if (itineraryAttractionRelationship.getDay() == 1) {
                day1.add(itineraryAttractionRelationship.getAttraction());
            } else if (itineraryAttractionRelationship.getDay() == 2) {
                day2.add(itineraryAttractionRelationship.getAttraction());
            } else {
                day3.add(itineraryAttractionRelationship.getAttraction());
            }
        });


        model.addAttribute("day1", day1);
        model.addAttribute("day2", day2);
        model.addAttribute("day3", day3);
        model.addAttribute("itinerary", itinerary);


        return "itineraryView";
    }

    @GetMapping("/itineraries")
    public String getItineraries(Model model) {
        model.addAttribute("itineraries", itineraryService.getItineraries());
        return "itineraries";
    }

    @GetMapping("/itineraries/new")
    public String createItineraryForm(Model model) {
        Itinerary itinerary = new Itinerary();
        model.addAttribute("itinerary", itinerary);
        return "createItinerary";
    }

    // ------------------------------------------------------------------------------------------------------------

    @PostMapping("/itineraries")
    public String saveItinerary(@ModelAttribute("itinerary") Itinerary itinerary) {
        itineraryService.saveItinerary(itinerary);
        return "redirect:/itineraries";
    }

    @GetMapping("/itineraries/{id}")
    public RItinerary getItinerary(@PathVariable int id) {
        return itineraryService.getItinerary(id);
    }

    @PutMapping("/itineraries/{id}")
    public RItinerary updateItinerary(@PathVariable int id, @RequestBody Itinerary itinerary) {
        return itineraryService.updateItinerary(id, itinerary);
    }

    @DeleteMapping("/itineraries")
    public void deleteItinerary(@RequestParam("id") int id) {
        itineraryService.deleteItinerary(id);
    }

    @GetMapping("/itinerary")
    public String showItineraryForm() {
        return "itinerary";
    }
}
