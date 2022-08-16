package com.toursim.application.itinerary;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ItineraryAttractionPK implements Serializable {

    private int id_itinerary;

    private int id_attraction;

    public int getId_itinerary() {
        return id_itinerary;
    }

    public void setId_itinerary(int id_itinerary) {
        this.id_itinerary = id_itinerary;
    }

    public int getId_attraction() {
        return id_attraction;
    }

    public void setId_attraction(int id_attraction) {
        this.id_attraction = id_attraction;
    }

    public ItineraryAttractionPK() {
        super();
    }

    public ItineraryAttractionPK(int id_itinerary, int id_attraction) {
        this.id_attraction = id_attraction;
        this.id_itinerary = id_itinerary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        ItineraryAttractionPK that = (ItineraryAttractionPK) o;
        return Objects.equals(id_itinerary, that.id_itinerary) &&
                Objects.equals(id_attraction, that.id_attraction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_itinerary, id_attraction);
    }
}
