package com.hekurudhe.app.route.Controllers;

import com.hekurudhe.app.route.Entities.Station;
import com.hekurudhe.app.route.Repositories.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class StationController {
    @Autowired
    StationRepository stationRepository;
    @GetMapping("/stations")
    public ResponseEntity<List<Station>> getAllStations(@RequestParam(required = false) String city)
    {
        try{
            List<Station> stations = new ArrayList<Station>();
            if(city == null) {
                stationRepository.findAll().forEach(stations::add);
            }
            else {
                stationRepository.findByCity(city).forEach(stations::add);
            }

            if(stations.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(stations, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/stations/{id}")
    public ResponseEntity<Station> getStationById(@PathVariable("id") Integer Id){
        Optional<Station> stationData = stationRepository.findById(Id);
        if(stationData.isPresent()){
            return new ResponseEntity<>(stationData.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/stations/{stationName}")
    public ResponseEntity<Station> getStationByName(@PathVariable("stationName") String stationName){
        Optional<Station> stationData = Optional.ofNullable(stationRepository.findByStationName(stationName));
        if(stationData.isPresent()){
            return new ResponseEntity<>(stationData.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/stations")
    public ResponseEntity<Station> createStation(@RequestBody Station station){
        try{
            Station _station = stationRepository
                    .save(new Station(station.getStationName(), station.getLocation(), station.getCity()));
            return new ResponseEntity<>(_station, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/stations/{id}")
    public ResponseEntity<Station> updateStation(@PathVariable("id") Integer Id, @RequestBody Station station){
        Optional<Station> stationData = stationRepository.findById(Id);
        if(stationData.isPresent()){
            Station _station = stationData.get();
            _station.setStationName(station.getStationName());
            _station.setLocation(station.getLocation());
            _station.setCity(station.getCity());
            _station.setRoute(station.getRoute());
            return new ResponseEntity<>(stationRepository.save(_station), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/stations/{id}")
    public ResponseEntity<HttpStatus> deleteStation(@PathVariable("id") Integer Id){
        try{
            stationRepository.deleteById(Id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/stations")
    public ResponseEntity<HttpStatus> deleteAllStations(){
        try{
            stationRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
