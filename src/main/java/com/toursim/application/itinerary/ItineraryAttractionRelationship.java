package com.toursim.application.itinerary;

import com.toursim.application.attraction.Attraction;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "ItineraryAttractionRelationship")
@Table(name = "itinerary_attraction")
public class ItineraryAttractionRelationship {

    @EmbeddedId
    private ItineraryAttractionPK id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("id_itinerary")
    private Itinerary itinerary;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("id_attraction")
    private Attraction attraction;

    @Column(name = "day")
    private int day;

    public ItineraryAttractionPK getId() {
        return id;
    }

    public void setId(ItineraryAttractionPK id) {
        this.id = id;
    }

    public Itinerary getItinerary() {
        return itinerary;
    }

    public void setItinerary(Itinerary itinerary) {
        this.itinerary = itinerary;
    }

    public Attraction getAttraction() {
        return attraction;
    }

    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        ItineraryAttractionRelationship that = (ItineraryAttractionRelationship) o;
        return Objects.equals(itinerary, that.itinerary) &&
                Objects.equals(attraction, that.attraction) &&
                Objects.equals(day, that.day);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itinerary, attraction, day);
    }
}
