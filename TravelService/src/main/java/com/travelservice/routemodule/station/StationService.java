package com.travelservice.routemodule.station;

import com.travelservice.routemodule.route.Route;
import com.travelservice.routemodule.route.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StationService {

    private final StationRepository stationRepository;

    @Autowired
    public StationService(StationRepository stationRepository){
        this.stationRepository = stationRepository;
    }

    public List<Station> getStations(){
        return stationRepository.findAll();
    }

    public void addNewStation(Station station){
        Optional<Station> stationByName =
                stationRepository.findStationByStationName(station.getStationName());
        if(stationByName.isPresent()){
            throw new IllegalStateException("station taken!");
        }
        stationRepository.save(station);
    }

    public void deleteStation(Long stationId) {
        stationRepository.deleteById(stationId);
    }
}
