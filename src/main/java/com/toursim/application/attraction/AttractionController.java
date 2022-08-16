package com.toursim.application.attraction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AttractionController {

    @Autowired
    private AttractionService attractionService;

    @GetMapping("/attractions/{id}")
    public String getAttractions(@PathVariable("id") int cityId, Model model) {
        List<RAttraction> rAttractions = attractionService.getAttractionsForCity(cityId).stream().map(AttractionAdapter::toClientModel).collect(Collectors.toList());

        RAttraction rAttraction = new RAttraction();
        rAttraction.setCityId(cityId);
        model.addAttribute("attractions", rAttractions);
        model.addAttribute("newAttraction", rAttraction);
        return "cityAttractions";
    }

    @PostMapping("/attractions")
    public String saveAttraction(RAttraction rAttraction, Model model) {
        Attraction attraction = attractionService.saveAttraction(rAttraction);
        return "redirect:/attractions/" + rAttraction.getCityId();
    }
}
