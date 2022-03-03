package com.toursim.application.itAtRelationship;

import com.toursim.application.attraction.Attraction;
import com.toursim.application.itinerary.Itinerary;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="itAtRelationship")
public class ItAtRelationship {
    @Id
    int id;
    @ManyToOne
    @JoinColumn(name="id", nullable=false)
    Itinerary idItinerary;
    @ManyToOne
    @JoinColumn(name="id", nullable=false)
    Attraction idAttraction;
}
