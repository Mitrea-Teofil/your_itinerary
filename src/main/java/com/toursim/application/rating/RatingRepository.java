package com.toursim.application.rating;

import com.toursim.application.city.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

    @Query(value = "SELECT * FROM Rating r WHERE r.city_id = :city", nativeQuery = true)
    List<Rating> getRatingsByCityId(@Param("city") int city_id);
}
