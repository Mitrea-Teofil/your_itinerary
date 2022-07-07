package com.toursim.application.user;

import com.toursim.application.city.CityService;
import com.toursim.application.city.Continent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    CityService cityService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("europeCities", cityService.getCitiesByContinent(Continent.Europe.getContinent()));
        model.addAttribute("asiaCities", cityService.getCitiesByContinent(Continent.Asia.getContinent()));
        model.addAttribute("americaCities", cityService.getCitiesByContinent(Continent.America.getContinent()));
        model.addAttribute("africaCities", cityService.getCitiesByContinent(Continent.Africa.getContinent()));
        model.addAttribute("australiaCities", cityService.getCitiesByContinent(Continent.Australia.getContinent()));

        return "index";
    }
}
