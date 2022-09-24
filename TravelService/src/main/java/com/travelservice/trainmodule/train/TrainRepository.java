package com.travelservice.trainmodule.train;

import com.travelservice.routemodule.station.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainRepository extends JpaRepository<Train, Long>  {

    Optional<Train> findById(@Param("stationName") String trainName);
}

