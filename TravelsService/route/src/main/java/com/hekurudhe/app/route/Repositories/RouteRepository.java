package com.hekurudhe.app.route.Repositories;

import com.hekurudhe.app.route.Entities.Route;
import com.hekurudhe.app.route.Entities.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {
    List<Route> findByStartPoint(@Param("startPoint") Integer startPoint);
    List<Route> findByEndPoint(@Param("endPoint")Integer endPoint);
    /*List<Route> findByEndAndStartPoint(@Param("startPoint") Integer startPoint, @Param("endPoint") Integer endPoint);
    List<Route> findByPoints(@Param("startPoint")Integer startPoint, @Param("endPoint") Integer endPoint, @Param("middlePoint") Integer middlePoint);*/
}
