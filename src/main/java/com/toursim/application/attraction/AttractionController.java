package com.toursim.application.attraction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/attractions")
public class AttractionController {

    @Autowired
    private AttractionService attractionService;

//    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Attraction> addAttraction(@RequestBody Attraction attraction) {
//        Attraction saved = repository.save(attraction);
//
//        if (saved != null) {
//            return new ResponseEntity<>(saved, HttpStatus.CREATED);
//        }
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }

//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity<List<Attraction>> getAll() {
//        List<Attraction> all = repository.findAll();
//        return new ResponseEntity<>(all, HttpStatus.OK);
//    }

    @GetMapping
    public ModelAndView getAttractions(){
        ModelAndView mov = new ModelAndView("list-attractions");
        List<Attraction> attractions = attractionService.getAttractions();
        mov.addObject("attractions", attractions);
        return  mov;
    }

    @GetMapping("/{id}")
    public Attraction getAttraction(@PathVariable int id){
        return attractionService.getAttraction(id);
    }

    @PostMapping
    public Attraction saveAttraction(@RequestBody Attraction attraction){
        return attractionService.saveAttraction(attraction);
    }

    @PutMapping("/{id}")
    public Attraction updateAttraction(@PathVariable int id, @RequestBody Attraction attraction){
        return attractionService.updateAttraction(id, attraction);
    }

    @DeleteMapping
    public void deleteAttraction(@RequestParam("id") int id){
        attractionService.deleteAttraction(id);
    }
}
