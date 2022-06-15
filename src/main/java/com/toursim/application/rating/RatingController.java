package com.toursim.application.rating;

import com.toursim.application.city.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

//    @Autowired
//    private RatingRepository repository;

    @Autowired
    private RatingService ratingService;

//    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Rating> addRating(@RequestBody Rating rating) {
//        Rating saved = repository.save(rating);
//
//        if (saved != null) {
//            return new ResponseEntity<>(saved, HttpStatus.CREATED);
//        }
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }
//
//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity<List<Rating>> getAll() {
//        List<Rating> all = repository.findAll();
//        return new ResponseEntity<>(all, HttpStatus.OK);
//    }

    @GetMapping
    public List<Rating> getRatings(){
        return ratingService.getRatings();
    }

    @GetMapping("/{id}")
    public Rating getRating(@PathVariable int id){
        return ratingService.getRating(id);
    }

    @PostMapping
    public Rating saveRating(@RequestBody Rating rating){
        return ratingService.saveRating(rating);
    }

    @PutMapping("/{id}")
    public Rating updateRating(@PathVariable int id, @RequestBody Rating rating){
        return ratingService.updateRating(id, rating);
    }

    @DeleteMapping
    public void deleteRating(@RequestParam int id){
        ratingService.deleteRating(id);
    }
}
