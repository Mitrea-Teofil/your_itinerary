package com.toursim.application.rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;


    @GetMapping
    public List<RRating> getRatings() {
        return ratingService.getRatings();
    }

    @PostMapping
    public String saveRating(@RequestBody RRating rating) {
        ratingService.saveRating(rating);
        return rating.getAttractionId() != 0 ? "redirect:/attraction/" + rating.getAttractionId() : "redirect:/itinerary/" + rating.getItineraryId();
    }

    @DeleteMapping
    public void deleteRating(@RequestParam int id) {
        ratingService.deleteRating(id);
    }

}
