package com.toursim.application.rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RatingController {

    @Autowired
    private RatingService ratingService;


    @GetMapping ("ratings/{id}")
    public List<RRating> getRatings() {
        return ratingService.getRatings();
    }

    @PostMapping ("/ratings")
    public String saveRating(RRating rating) {
        ratingService.saveRating(rating);
        return rating.getAttractionId() != 0 ? "redirect:/attraction/" + rating.getAttractionId() : "redirect:/itinerary/" + rating.getItineraryId();
    }

    @DeleteMapping ("/ratings")
    public void deleteRating(@RequestParam int id) {
        ratingService.deleteRating(id);
    }

}
