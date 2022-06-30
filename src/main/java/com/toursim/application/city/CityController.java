package com.toursim.application.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/cities")
@Controller
public class CityController {



    @Autowired
    private CityService cityService;

    @GetMapping("/cities")
    public List<City> getCities(){
        return cityService.getCities();
    }

    @GetMapping("/cities/{id}")
    public City getCity(@PathVariable int id){
        return cityService.getCity(id);
    }

    @PostMapping("/cities")
    public City saveCity(@RequestBody City city){
        return cityService.saveCity(city);
    }

    @PutMapping("/cities/{id}")
    public City updateCity(@PathVariable int id, @RequestBody City city){
        return cityService.updateCity(id, city);
    }

    @DeleteMapping("/cities")
    public void deleteCity(@RequestParam("id") int id){
        cityService.deleteCity(id);
    }

    @GetMapping("/city")
    public String showItineraryForm() {
        return "city";
    }
}
