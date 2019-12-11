package com.example.springsocial.repository;

import com.example.springsocial.model.Location;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LocationRepository extends JpaRepository<Location, Long> {
    
    ArrayList<Location> findLocationByPlaceAndStateAbbr(String place, String state_abbr);

    List<Location> findDistinctByState(String state);

    public static final String FIND_LOCATIONS = "SELECT DISTINCT(place) FROM locations WHERE state_abbr = :state ORDER BY place ASC";

    @Query(value = FIND_LOCATIONS, nativeQuery = true)
    public List<String> findLocationsByState(@Param("state") String state);

}
