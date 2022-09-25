package com.travelservice.travelmodule;

import com.travelservice.routemodule.route.Route;
import com.travelservice.routemodule.station.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TripService {

    private final TripRepository tripRepository;

    @Autowired
    public TripService(TripRepository tripRepository){ this.tripRepository = tripRepository;}

    public List<Trip> getTrips(){
        return tripRepository.findAll();
    }

    public Optional<Trip> getTrip(Long tripId){ return tripRepository.findById(tripId);}

    public void addNewTrip(Trip trip){
        trip.setArrivalTime((calculateArrivalTime(trip.getDepartureTime(), trip.getRouteTrip().getDuration())));
        tripRepository.save(trip);
    }

    @Transactional
    public void updateTrip(Long tripId, Trip updatedTrip) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new IllegalStateException(
                        "trip with id " + tripId + " does not exist!"
                ));

        if(updatedTrip.getDepartureTime() != null && !Objects.equals(trip.getDepartureTime(), updatedTrip.getDepartureTime())){
            if((updatedTrip.getDepartureTime()).isAfter(trip.getDepartureTime())){
                trip.setStatus("delayed");
            }else if (updatedTrip.getStatus() != null && !Objects.equals(trip.getStatus(), updatedTrip.getStatus())){
                trip.setStatus(updatedTrip.getStatus());
            }
            trip.setDepartureTime(updatedTrip.getDepartureTime());
            LocalDateTime arrTime = calculateArrivalTime(updatedTrip.getDepartureTime(),updatedTrip.getRouteTrip().getDuration());
            trip.setArrivalTime(arrTime);
        }



        if(updatedTrip.getRouteTrip() != null && !Objects.equals(trip.getRouteTrip(), updatedTrip.getRouteTrip())){
            trip.setRouteTrip(updatedTrip.getRouteTrip());
        }
    }

    public Route getRouteTrip(Long tripId){
        return tripRepository.getRouteTrip(tripId);
    }

    public Page<Trip> findBySearchCriteria(Specification<Trip> tripSpec, Pageable page){
        Page<Trip> searchResult = tripRepository.findAll(tripSpec, page);
        return searchResult;
    }
    public void deleteTrip(Long tripId) {
        tripRepository.deleteById(tripId);
    }

    public void deleteTrips(){tripRepository.deleteAll();}

    public LocalDateTime calculateArrivalTime(LocalDateTime depTime, Double dur){
        LocalDateTime arrTime = depTime.plusMinutes(dur.longValue());
        return arrTime;
    }

}


