package com.hekurudhe.app.route.Repositories;

import com.hekurudhe.app.route.Entities.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Integer> {
    Attraction findByAttractionName(@Param("attractionName") String attractionName);
    List<Attraction> findByLocationContaining(@Param("location") String location);
}
