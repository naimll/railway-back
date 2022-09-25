package com.travelservice.routemodule.station;

import com.travelservice.routemodule.route.Route;
import com.travelservice.routemodule.route.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/stations")
public class StationController {

    private final StationService stationService;

    @Autowired
    public StationController(StationService stationService){
        this.stationService = stationService;
    }

    @GetMapping
    public List<Station> getStations(){
        return stationService.getStations();
    }

    @GetMapping(path = "{stationId}")
    public Optional<Station> getStation(@PathVariable("stationId") Long stationId){
        return stationService.getStation(stationId);
    }

    @PostMapping
    public void createNewStations(@RequestBody Station station){
        stationService.addNewStation(station);
    }

    @PutMapping(path = "{stationId}")
    public void updateStation(
            @PathVariable("stationId") Long stationId,
            @RequestBody Station updatedStation
    ){
        stationService.updateStation(stationId, updatedStation);
    }
    @DeleteMapping(path = "{stationId}")
    public void deleteStation(@PathVariable("stationId") Long stationId){
        stationService.deleteStation(stationId);
    }

    @DeleteMapping
    public void deleteStations(){
        stationService.deleteStations();
    }

 /*   @Autowired
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
    }*/
    @GetMapping("/getStationSelect")
    public ResponseEntity<List<StationSelect>> getStationSelect(){
        List<StationSelect> s = stationService.getStationSelect();
        return new ResponseEntity<>(s,HttpStatus.ACCEPTED);
    }

}
