package com.travelservice.travelmodule;


import com.travelservice.advSearch.APIResponse;
import com.travelservice.advSearch.SearchCriteria;
import com.travelservice.routemodule.route.Route;

import com.travelservice.travelmodule.tripSearch.TripSearchDto;
import com.travelservice.travelmodule.tripSearch.TripSpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping(path = "{tripId}")
    public Optional<Trip> getTrip(@PathVariable("tripId") Long tripId){
        return tripService.getTrip(tripId);
    }

    @GetMapping(path = "{tripId}/routeTrip")
    public Route getRouteTrip(@PathVariable("tripId") Long tripId){
        return tripService.getRouteTrip(tripId);
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

    @PostMapping(path = "search")
    public ResponseEntity<APIResponse> searchTrips(@RequestParam(name="pageNr", defaultValue = "0") int pageNr,
                                                    @RequestParam(name="pageSize", defaultValue = "10") int pageSize,
                                                    @RequestBody TripSearchDto tripSearchDto) {
        APIResponse apiResponse = new APIResponse();
        TripSpecificationBuilder builder = new TripSpecificationBuilder();

        List<SearchCriteria> criteriaList = tripSearchDto.getSearchCriteriaList();
        if (criteriaList != null) {
            criteriaList.forEach(
                    x -> {
                        x.setDataOption(tripSearchDto.getDataOption());
                        builder.with(x);
                    }
            );
        }
        Pageable page = PageRequest.of(pageNr, pageSize, Sort.by("departureTime").ascending());

        Page<Trip> tripPage = tripService.findBySearchCriteria(builder.build(), page);

        apiResponse.setData(tripPage.toList());
        apiResponse.setResponseCode(HttpStatus.OK);
        apiResponse.setMessage("Successfully retrieved route record");

        return new ResponseEntity<>(apiResponse, apiResponse.getResponseCode());
    }

    @DeleteMapping(path = "{tripId}")
    public void deleteTrip(@PathVariable("tripId") Long tripId) {
        tripService.deleteTrip(tripId);
    }

    @DeleteMapping
    public void deleteTrips(){
        tripService.deleteTrips();
    }
}

