package com.travelservice.routemodule.route;

import com.travelservice.routemodule.route.Route;
import com.travelservice.routemodule.station.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long>, JpaSpecificationExecutor<Route> {
    List<Route> findByStartPoint(@Param("startPoint") Station startPoint);
    List<Route> findByEndPoint(@Param("endPoint")Station endPoint);

    Optional<Route> findRouteByDistance(Double distance);

    /*List<Route> findByEndAndStartPoint(@Param("startPoint") Integer startPoint, @Param("endPoint") Integer endPoint);
    List<Route> findByPoints(@Param("startPoint")Integer startPoint, @Param("endPoint") Integer endPoint, @Param("middlePoint") Integer middlePoint);*/
}
