package com.toursim.application.rating;

import com.toursim.application.itinerary.RItinerary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {


    @Autowired
    private RatingService ratingService;


    @GetMapping
    public List<Rating> getRatings() {
        return ratingService.getRatings();
    }

    @GetMapping("/{id}")
    public Rating getRating(@PathVariable int id) {
        return ratingService.getRating(id);
    }

    @PostMapping
    public Rating saveRating(@RequestBody Rating rating) {
        return ratingService.saveRating(rating);
    }

    @PutMapping("/{id}")
    public Rating updateRating(@PathVariable int id, @RequestBody Rating rating) {
        return ratingService.updateRating(id, rating);
    }

    @DeleteMapping
    public void deleteRating(@RequestParam int id) {
        ratingService.deleteRating(id);
    }

}
