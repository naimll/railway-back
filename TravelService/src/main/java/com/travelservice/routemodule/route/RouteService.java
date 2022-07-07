package com.travelservice.routemodule.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
