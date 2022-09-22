package com.travelservice.travelmodule;

import com.travelservice.routemodule.station.Station;
import com.travelservice.routemodule.station.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/trips")
public class TripController {

    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping
    public List<Trip> getTrips() {
        return tripService.getTrips();
    }

    @PostMapping
    public void createNewTrips(@RequestBody Trip trip) {
        tripService.addNewTrip(trip);
    }

    @PutMapping(path = "{tripId}")
    public void updateTrip(
            @PathVariable("tripId") Long tripId,
            @RequestBody Trip updatedTrip
    ) {
        tripService.updateTrip(tripId, updatedTrip);
    }

    @DeleteMapping(path = "{tripId}")
    public void deleteTrip(@PathVariable("tripId") Long tripId) {
        tripService.deleteTrip(tripId);
    }
}

