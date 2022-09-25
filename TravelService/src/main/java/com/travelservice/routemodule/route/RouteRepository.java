package com.travelservice.routemodule.route;

import com.travelservice.routemodule.attraction.Attraction;
import com.travelservice.routemodule.route.Route;
import com.travelservice.routemodule.station.Station;
import com.travelservice.routemodule.station.StationSelect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long>, JpaSpecificationExecutor<Route> {
    List<Route> findByStartPoint(@Param("startPoint") Station startPoint);
    List<Route> findByEndPoint(@Param("endPoint")Station endPoint);
    Optional<Route> findRouteByDistance(Double distance);
    @Query("select r.startPoint from Route r where r.id = ?1")
    Station getStartPoint(@Param("routeId") Long routeId);

    @Query("select r.middlePoints from Route r where r.id = ?1")
    List<Station> getMiddlePoints(@Param("routeId") Long routeId);

    @Query("select r.endPoint from Route r where r.id = ?1")
    Station getEndPoint(@Param("routeId") Long routeId);



    @Query("select r.attractions from Route r where r.id = ?1")
    List<Attraction> getAttractions(@Param("routeId") Long routeId);

}
