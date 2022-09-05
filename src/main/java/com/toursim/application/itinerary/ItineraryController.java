package com.toursim.application.itinerary;

import com.toursim.application.city.CityService;
import com.toursim.application.rating.RRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ItineraryController {

    @Autowired
    private ItineraryService itineraryService;

    @Autowired
    private CityService cityService;


    @GetMapping("/itinerary/{id}")
    public String getItineraryById(@PathVariable("id") int id, Model model) {

        RRating rating = new RRating();
        rating.setItineraryId(id);
        model.addAttribute("newRating", rating);

        RItinerary rItinerary = itineraryService.getItinerary(id);
        model.addAttribute("itinerary", rItinerary);
        return "itineraryView";
    }

    @PostMapping("/itineraries")
    public String saveItinerary(RItinerary rItinerary, Model model) {
        itineraryService.saveItinerary(rItinerary);
        return "redirect:/cities/" + rItinerary.getCityId() + "/itinerary";
    }
    // ------------------------------------------------------------------------------------------------------------

    @DeleteMapping("/itineraries")
    public void deleteItinerary(@RequestParam("id") int id) {
        itineraryService.deleteItinerary(id);
    }
}
