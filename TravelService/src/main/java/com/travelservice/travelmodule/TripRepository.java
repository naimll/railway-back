package com.travelservice.travelmodule;

import com.travelservice.routemodule.route.Route;
import com.travelservice.routemodule.station.Station;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TripRepository extends JpaRepository<Trip, Long> {
    @Query("select t.routeTrip from Trip t where t.id = ?1")
    Route getRouteTrip(@Param("tripId") Long tripId);

    Page<Trip> findAll(Specification<Trip> tripSpec, Pageable page);
}
