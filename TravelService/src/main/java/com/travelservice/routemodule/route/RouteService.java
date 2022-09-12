package com.travelservice.routemodule.route;

import com.travelservice.routemodule.station.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RouteService {

    private final RouteRepository routeRepository;

    @Autowired
    public RouteService(RouteRepository routeRepository){
        this.routeRepository = routeRepository;
    }

    public List<Route> getRoutes(){
        return routeRepository.findAll();
    }

    public void addNewRoute(Route route){
        Optional<Route> routeByDistance =
                routeRepository.findRouteByDistance(route.getDistance());
        if(routeByDistance.isPresent()){
            throw new IllegalStateException("route taken!");
        }

        routeRepository.save(route);
    }


    public void deleteRoute(Long routeId) {

        routeRepository.deleteById(routeId);
    }

    @Transactional
    public void updateRoute(Long routeId, Route updatedRoute) {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new IllegalStateException(
                        "route with id " + routeId + " does not exist!"
                ));

        if(updatedRoute.getStartPoint() != null && !Objects.equals(route.getStartPoint(), updatedRoute.getStartPoint())){
            route.setStartPoint(updatedRoute.getStartPoint());
        }

        if(updatedRoute.getEndPoint() != null && !Objects.equals(route.getEndPoint(), updatedRoute.getEndPoint())){
            route.setEndPoint(updatedRoute.getEndPoint());
        }

        if(updatedRoute.getMiddlePoints() != null && !Objects.equals(route.getMiddlePoints(), updatedRoute.getMiddlePoints())){
            route.setMiddlePoints(updatedRoute.getMiddlePoints());
        }

        if(updatedRoute.getDistance() != 0 && !Objects.equals(route.getDistance(), updatedRoute.getDistance())){
            route.setDistance(updatedRoute.getDistance());
        }

        if(updatedRoute.getAttractions() != null && !Objects.equals(route.getAttractions(), updatedRoute.getAttractions())){
            route.setAttractions(updatedRoute.getAttractions());
        }
    }
}
