package com.travelservice.Repositories;


import com.travelservice.Entities.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station, Integer> {
    Station findByStationName(@Param("stationName") String stationName);
    List<Station> findByCity(@Param("city") String city);
}
