package com.toursim.application.itinerary;

import com.toursim.application.attraction.Attraction;

import java.util.ArrayList;
import java.util.List;

public abstract class ItineraryAdapter {

    public static Itinerary toServerModel(RItinerary rItinerary) {
        Itinerary itinerary = new Itinerary();
        itinerary.setName(rItinerary.getName());
        itinerary.setPicture(rItinerary.getPicture());
        itinerary.setDescription(rItinerary.getDescription());
        itinerary.setNumberDays(calculateNumberOfDays(rItinerary));
        return itinerary;
    }

    private static int calculateNumberOfDays(RItinerary rItinerary) {
        if (!rItinerary.getDay3().isEmpty()) {
            return 3;
        } else if (!rItinerary.getDay2().isEmpty()) {
            return 2;
        }
        return 1;
    }

    public static RItinerary toClientModel(Itinerary itinerary) {
        RItinerary rItinerary = new RItinerary();
        rItinerary.setId(itinerary.getId());
        rItinerary.setName(itinerary.getName());
        rItinerary.setDescription(itinerary.getDescription());
        rItinerary.setNumberDays(itinerary.getNumberDays());
        rItinerary.setPicture(itinerary.getPicture());
        setAttractionsByDays(rItinerary, itinerary);
        rItinerary.setCity(itinerary.getCity());
        rItinerary.setRatings(itinerary.getRatings());
        return rItinerary;
    }

    private static void setAttractionsByDays(RItinerary rItinerary, Itinerary itinerary) {
        List<ItineraryAttractionRelationship> attractions = itinerary.getAttractionsRelationships();

        List<Attraction> day1 = new ArrayList<>();
        List<Attraction> day2 = new ArrayList<>();
        List<Attraction> day3 = new ArrayList<>();

        rItinerary.setDay1(day1);
        rItinerary.setDay2(day2);
        rItinerary.setDay3(day3);

        attractions.forEach(itineraryAttractionRelationship -> {
            if (itineraryAttractionRelationship.getDay() == 1) {
                day1.add(itineraryAttractionRelationship.getAttraction());
            } else if (itineraryAttractionRelationship.getDay() == 2) {
                day2.add(itineraryAttractionRelationship.getAttraction());
            } else {
                day3.add(itineraryAttractionRelationship.getAttraction());
            }
        });
    }

}
