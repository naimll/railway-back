/*
package com.travelservice.routemodule.attraction;


import com.travelservice.routemodule.attraction.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Integer> {
    Attraction findByAttractionName(@Param("attractionName") String attractionName);
    List<Attraction> findByLocationContaining(@Param("location") String location);

    Optional<Attraction> findById(@Param("attraction_id") Integer attraction_id);
}
*/
