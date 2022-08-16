package com.toursim.application.city;

import com.toursim.application.itinerary.RItinerary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/cities")
    public List<City> getCities() {
        return cityService.getCities();
    }

    @GetMapping("/cities/{id}")
    public City getCity(@PathVariable int id) {
        return cityService.getCity(id);
    }

    @GetMapping("/cities/{id}/itinerary")
    public String getItinerariesByCity(@PathVariable("id") int id, Model model) {

        City city = cityService.getCity(id);
        populateCityItinerariesModel(city, model);
        RItinerary rItinerary = new RItinerary();
        rItinerary.setCityId(city.getId());
        model.addAttribute("newItinerary", rItinerary);
        return "cityItineraries";
    }

    @PostMapping("/cities")
    public City saveCity(@RequestBody City city) {
        return cityService.saveCity(city);
    }

    @PutMapping("/cities/{id}")
    public City updateCity(@PathVariable int id, @RequestBody City city) {
        return cityService.updateCity(id, city);
    }

    @DeleteMapping("/cities")
    public void deleteCity(@RequestParam("id") int id) {
        cityService.deleteCity(id);
    }

    @GetMapping("/city")
    public String showItineraryForm() {
        return "city";
    }


    private void populateCityItinerariesModel(City city, Model model) {
        List<RItinerary> cityItineraries = cityService.getCityItineraries(city);

        ArrayList<RItinerary> oneDayIt = new ArrayList<>();
        ArrayList<RItinerary> twoDayIt = new ArrayList<>();
        ArrayList<RItinerary> threeDayIt = new ArrayList<>();

        cityItineraries.forEach(itinerary -> {
            if (itinerary.getNumberDays() == 1) {
                oneDayIt.add(itinerary);
            } else if (itinerary.getNumberDays() == 2) {
                twoDayIt.add(itinerary);
            } else {
                threeDayIt.add(itinerary);
            }
        });

        model.addAttribute("oneDayIt", oneDayIt);
        model.addAttribute("twoDayIt", twoDayIt);
        model.addAttribute("threeDayIt", threeDayIt);
        model.addAttribute("attractions", city.getAttractions());
    }

}
