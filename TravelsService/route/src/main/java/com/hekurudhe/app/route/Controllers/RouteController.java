package com.hekurudhe.app.route.Controllers;

import com.hekurudhe.app.route.Entities.Attraction;
import com.hekurudhe.app.route.Entities.Route;
import com.hekurudhe.app.route.Entities.Station;
import com.hekurudhe.app.route.Repositories.RouteRepository;
import com.hekurudhe.app.route.Requests.RouteAdditionRequest;
import com.hekurudhe.app.route.Services.RouteService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@RestController
@RequestMapping("api/routes")
public class RouteController {

   @Autowired
    RouteRepository routeRepository;

/*  @PostMapping

  public void createRoute(@RequestBody RouteAdditionRequest routeAdditionRequest) {
     log.info("route",routeAdditionRequest);
      routeService.addRoute(routeAdditionRequest);
  }*/

  @GetMapping
    public ResponseEntity<List<Route>> getAllRoutes(@RequestParam(required = false) Integer startPoint) {
        try {
            List<Route> routes = new ArrayList<Route>();
            if (startPoint == null)
                routeRepository.findAll().forEach(routes::add);
            else
                routeRepository.findByStartPoint(startPoint).forEach(routes::add);
            if (routes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(routes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{Id}")
    public ResponseEntity<Route> getRouteById(@PathVariable("Id") Integer Id){
        Optional<Route> routeData = routeRepository.findById(Id);
        if(routeData.isPresent()){
            return new ResponseEntity<>(routeData.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

   @GetMapping("/{endPoint}}")
    public ResponseEntity<List<Route>> findByEndPoint(@PathVariable("endPoint") Integer endPoint) {
        try {
            List<Route> routes = routeRepository.findByEndPoint(endPoint);
            if (routes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(routes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

/*   @GetMapping("/{startPoint}-{endPoint}")
    public ResponseEntity<List<Route>> findByStartAndEndPoint(@PathVariable("startPoint") Integer startPoint, @PathVariable("endPoint") Integer endPoint) {
        try {
            List<Route> routes = routeRepository.findByEndAndStartPoint(startPoint,endPoint);
            if (routes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(routes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

  /*  @GetMapping("/points}")
    public ResponseEntity<List<Route>> findByPoints(@Param("startPoint")Integer startPoint, @Param("endPoint") Integer endPoint, @Param("middlePoint") Integer middlePoint) {
        try {
            List<Route> routes = routeRepository.findByPoints(startPoint, endPoint, middlePoint);
            if (routes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(routes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
*/

 @PostMapping
    public ResponseEntity<Route> createRoute(@RequestBody Route route){
        try{
            Route _route = routeRepository
                    .save(new Route(route.getRouteName(), route.getStartPoint(), route.getEndPoint(), route.getMiddlePoints(), route.getDistance(), route.getAttractions()));
            return new ResponseEntity<>(_route, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Route> updateRoute(@PathVariable("id") Integer Id, @RequestBody Route route){
        Optional<Route> routeData = routeRepository.findById(Id);
        if(routeData.isPresent()){
            Route _route = routeData.get();
            _route.setRouteName(route.getRouteName());
            _route.setStartPoint(route.getStartPoint());
            _route.setEndPoint(route.getEndPoint());
            _route.setMiddlePoints(route.getMiddlePoints());
            _route.setDistance(route.getDistance());
            _route.setAttractions(route.getAttractions());
            return new ResponseEntity<>(routeRepository.save(_route), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRoute(@PathVariable("id") Integer Id){
        try{
            routeRepository.deleteById(Id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllRoutes(){
        try{
            routeRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
