package com.toursim.application.itinerary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItineraryAttractionRelationshipRepository extends JpaRepository<ItineraryAttractionRelationship, Integer> {
}
