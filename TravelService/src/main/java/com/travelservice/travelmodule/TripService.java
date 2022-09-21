package com.travelservice.travelmodule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class TripService {

    private final TripRepository tripRepository;

    @Autowired
    public TripService(TripRepository tripRepository){ this.tripRepository = tripRepository;}

    public List<Trip> getTrips(){
        return tripRepository.findAll();
    }

    public void addNewTrip(Trip trip){
        tripRepository.save(trip);
    }

    @Transactional
    public void updateTrip(Long tripId, Trip updatedTrip) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new IllegalStateException(
                        "trip with id " + tripId + " does not exist!"
                ));

        if(updatedTrip.getDeparture_time() != null && !Objects.equals(trip.getDeparture_time(), updatedTrip.getDeparture_time())){
            trip.setDeparture_time(updatedTrip.getDeparture_time());
        }

        if(updatedTrip.getArrival_time() != null && !Objects.equals(trip.getArrival_time(), updatedTrip.getArrival_time())){
            trip.setArrival_time(updatedTrip.getArrival_time());
        }

        if(updatedTrip.getStatus() != null && !Objects.equals(trip.getStatus(), updatedTrip.getStatus())){
            trip.setStatus(updatedTrip.getStatus());
        }

        if(updatedTrip.getRouteTrip() != null && !Objects.equals(trip.getRouteTrip(), updatedTrip.getRouteTrip())){
            trip.setRouteTrip(updatedTrip.getRouteTrip());
        }
    }
    public void deleteTrip(Long tripId) {
        tripRepository.deleteById(tripId);
    }

}


