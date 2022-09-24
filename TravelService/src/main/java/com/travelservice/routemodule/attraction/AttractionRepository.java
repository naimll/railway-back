package com.travelservice.routemodule.attraction;

import com.travelservice.routemodule.route.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Long> {
    Optional<Attraction> findByAttractionName(String attractionName);
    List<Attraction> findByLocationContaining(@Param("location") String location);
    Attraction findById(long id);
    
}
