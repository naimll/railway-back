package com.travelservice.routemodule.route;

import com.travelservice.routemodule.station.Station;
import com.travelservice.routemodule.station.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RouteService {

    private final RouteRepository routeRepository;
    private final StationRepository stationRepository;
    @Autowired
    public RouteService(RouteRepository routeRepository, StationRepository stationRepository){
        this.routeRepository = routeRepository;
        this.stationRepository = stationRepository;
    }

    public List<Route> getRoutes(){
        return routeRepository.findAll();
    }

    public void addNewRoute(Long startPoint, Long endPoint){
//        Optional<Route> routeByDistance =
//                routeRepository.findRouteByDistance(route.getDistance());
//        if(routeByDistance.isPresent()){
//            throw new IllegalStateException("route taken!");
//        }
        Optional<Station> start = stationRepository.findById(startPoint);
        Optional<Station> end = stationRepository.findById(endPoint);
        Route route = new Route(start.get(),end.get(),null,null,null,null);
        Double d = calculateDistance(route.getStartPoint().getLatitude(), route.getStartPoint().getLongitude(), route.getEndPoint().getLatitude(),  route.getEndPoint().getLongitude());
        route.setDistance(d);
        route.setDuration(calculateDuration(d));
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

        /*Double d =  calculateDistance(updatedRoute.getStartPoint().getLatitude(), updatedRoute.getStartPoint().getLongitude(), updatedRoute.getEndPoint().getLatitude(), updatedRoute.getEndPoint().getLongitude());
        Double dur = calculateDuration(d);*/

        if(updatedRoute.getStartPoint() != null && !Objects.equals(route.getStartPoint(), updatedRoute.getStartPoint())){
            route.setStartPoint(updatedRoute.getStartPoint());
        }

        if(updatedRoute.getEndPoint() != null && !Objects.equals(route.getEndPoint(), updatedRoute.getEndPoint())){
            route.setEndPoint(updatedRoute.getEndPoint());
        }

        if(updatedRoute.getMiddlePoints() != null && !Objects.equals(route.getMiddlePoints(), updatedRoute.getMiddlePoints())){
            route.setMiddlePoints(updatedRoute.getMiddlePoints());
        }

        if(updatedRoute.getAttractions() != null && !Objects.equals(route.getAttractions(), updatedRoute.getAttractions())){
            route.setAttractions(updatedRoute.getAttractions());
        }

        if(updatedRoute.getDistance() != null && !Objects.equals(route.getDistance(), updatedRoute.getDistance())){
            route.setDistance(updatedRoute.getDistance());
        }

        if(updatedRoute.getDuration() != null && !Objects.equals(route.getDuration(), updatedRoute.getDuration())){
            route.setDuration(updatedRoute.getDuration());
        }

//        if(updatedRoute.getDistance() != null && !Objects.equals(route.getDistance(), updatedRoute.getDistance())){
//            route.setDistance(d);
//        }
//
//        if(updatedRoute.getDuration() != null && !Objects.equals(route.getDuration(), updatedRoute.getDuration())){
//            route.setDuration(dur);
//        }
    }

    public Page<Route> findBySearchCriteria(Specification<Route> routeSpec, Pageable page){
        Page<Route> searchResult = routeRepository.findAll(routeSpec, page);
        return searchResult;
    }

    //nese ka me shume middlepoints me jau llogarite edhe atyne. me shku startpoint middlepoint tani middlepoint endpoint
    public Double calculateDistance(Double latStart, Double longStart, Double latEnd, Double longEnd){
        Double a = 0.0;

        latStart = Math.toRadians(latStart);
        latEnd = Math.toRadians(latEnd);
        longStart = Math.toRadians(longStart);
        longEnd = Math.toRadians(longEnd);

        Double dLat = latEnd - latStart;
        Double dLong = longEnd - longStart;
        a = Math.pow(Math.sin(dLat/2), 2) + Math.cos(latStart) * Math.cos(latEnd)
                * Math.pow(Math.sin(dLong/2), 2);

        Double c = 2 * Math.asin(Math.sqrt(a));

        Double r = 6371.0;

        return c * r;
    }

    public Double calculateDuration(Double dist){
        Double trainSpeed = 80.0;
        Double duration = dist/trainSpeed;

        return duration * 60;
    }
}
