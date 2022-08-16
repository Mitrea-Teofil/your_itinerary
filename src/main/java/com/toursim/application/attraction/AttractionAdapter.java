package com.toursim.application.attraction;

public class AttractionAdapter {

    public static Attraction toServerModel(RAttraction rAttraction) {
        Attraction attraction = new Attraction();
        attraction.setName(rAttraction.getName());
        attraction.setPicture(rAttraction.getPicture());
        attraction.setPrice(rAttraction.getPrice());
        attraction.setDescription(rAttraction.getDescription());
        return attraction;
    }

    public static RAttraction toClientModel(Attraction attraction) {
        RAttraction rAttraction = new RAttraction();
        rAttraction.setId(attraction.getId());
        rAttraction.setName(attraction.getName());
        rAttraction.setDescription(attraction.getDescription());
        rAttraction.setPicture(attraction.getPicture());
        rAttraction.setCity(attraction.getCity());
        return rAttraction;
    }

}
