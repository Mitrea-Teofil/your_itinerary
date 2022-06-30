package com.toursim.application.itinerary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RestController
//@RequestMapping("/itineraries")
public class ItineraryController {

    @Autowired
    private ItineraryService itineraryService;

    public ItineraryController(ItineraryService itineraryService) {
        super();
        this.itineraryService = itineraryService;
    }

    @GetMapping("/itineraries")
    public String getItineraries(Model model) {
        model.addAttribute("itineraries", itineraryService.getItineraries());
        return "itineraries";
    }

    @GetMapping("/itineraries/new")
    public String createItineraryForm(Model model){
        Itinerary itinerary = new Itinerary();
        model.addAttribute("itinerary", itinerary);
        return "create_itinerary";
    }

    @PostMapping("/itineraries")
    public String saveItinerary(@ModelAttribute("itinerary") Itinerary itinerary) {
        itineraryService.saveItinerary(itinerary);
        return "redirect:/itineraries";
    }

    @GetMapping("/itineraries/{id}")
    public Itinerary getItinerary(@PathVariable int id) {
        return itineraryService.getItinerary(id);
    }

    @PutMapping("/itineraries/{id}")
    public Itinerary updateItinerary(@PathVariable int id, @RequestBody Itinerary itinerary) {
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
