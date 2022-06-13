package com.hekurudhe.app.route.Services;

import com.hekurudhe.app.route.Entities.Route;
import com.hekurudhe.app.route.Requests.RouteAdditionRequest;
import com.hekurudhe.app.route.Repositories.RouteRepository;
import org.springframework.stereotype.Service;

@Service

public record RouteService(RouteRepository routeRepository) {

    public void addRoute(RouteAdditionRequest request)
    {
        Route route = Route.builder()
                .routeName(request.routeName())
                .startPoint(request.startPoint())
                .endPoint(request.endPoint())
                .middlePoints(request.middlePoints())
                .distance(request.distance())
                .attractions(request.attractions())
                .build();

        routeRepository.save(route);
    }
}
