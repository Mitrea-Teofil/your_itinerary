package com.toursim.application.itinerary;

public abstract class ItineraryAdapter {

    public static Itinerary toServerModel(RItinerary rItinerary){

        Itinerary itinerary = new Itinerary();
        itinerary.setName(itinerary.getName());
        itinerary.setPicture(itinerary.getPicture());
        itinerary.setDescription(itinerary.getDescription());
        itinerary.setNumberDays(itinerary.getNumberDays());
        return itinerary;
    }

    public static RItinerary toClientModel(Itinerary itinerary){
        RItinerary rItinerary = new RItinerary();
        rItinerary.setId(itinerary.getId());
        rItinerary.setName(itinerary.getName());
        rItinerary.setDescription(itinerary.getDescription());
        rItinerary.setNumberDays(itinerary.getNumberDays());
        rItinerary.setPicture(itinerary.getPicture());
        rItinerary.setAttractions(itinerary.getAttractionsRelationships());
        rItinerary.setCity(itinerary.getCity());
        rItinerary.setRatings(itinerary.getRatings());
        return rItinerary;
    }

}
