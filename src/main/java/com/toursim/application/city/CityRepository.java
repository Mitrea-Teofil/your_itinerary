package com.toursim.application.city;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    @Query(value = "SELECT * FROM city WHERE city.continent = :continent", nativeQuery = true)
    List<City> findCitiesByContinent(@Param("continent")String continent);

}
