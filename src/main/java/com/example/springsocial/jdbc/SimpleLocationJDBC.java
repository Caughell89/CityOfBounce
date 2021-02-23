package com.example.springsocial.jdbc;

import com.example.springsocial.model.SimpleLocation;
import com.example.springsocial.repository.SimpleLocationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SimpleLocationJDBC implements SimpleLocationRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public List<SimpleLocation> findByInputSearch(String place, String state) {
        return jdbcTemplate.query(
                "SELECT DISTINCT place, state, state_abbr FROM locations WHERE "
                        + "place LIKE ? AND state LIKE ? OR place LIKE ? AND "
                        + "state_abbr LIKE ? ORDER BY place, state_abbr ASC",
                new Object[]{place + "%", state +"%", place + "%", state +"%"},
                (rs, rowNum) ->
                        new SimpleLocation(
                                rs.getString("place"),
                                rs.getString("state"),
                                rs.getString("state_abbr")
                        )
        );
    }
    
}
