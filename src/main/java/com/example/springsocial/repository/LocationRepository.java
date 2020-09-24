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
    
    public static final String FIND_ALL_ACTIVE_LOCATIONS = "SELECT distinct concat(place, ', ', state_abbr) \n" +
    "FROM companies_areas  ca \n" +
    "JOIN locations l \n" +
    "ON ca.zip_id=l.zip_code\n" +
    "ORDER BY concat(place, ', ', state_abbr) ";

    @Query(value = FIND_ALL_ACTIVE_LOCATIONS, nativeQuery = true)
    public List<String> getAllActiveLocations();

    public static final String FIND_ALL_LOCATIONS = "SELECT distinct concat(place, ', ', state_abbr) \n" +
    "FROM locations";

    @Query(value = FIND_ALL_LOCATIONS, nativeQuery = true)
    public List<String> getAllLocations();

    public static final String FIND_LOCATIONS_BY_CITY = 
    "SELECT distinct concat(place, ', ', state_abbr) \n" +
    "FROM locations WHERE place LIKE ?% ORDER BY concat(place,', ', state_abbr) ASC;";

    @Query(value = FIND_LOCATIONS_BY_CITY, nativeQuery = true)
    public List<String> getLocationsByCity(String location);
    

}
