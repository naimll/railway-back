package com.travelservice.routemodule.station;

import com.travelservice.routemodule.route.Route;
import com.travelservice.routemodule.route.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
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

    public Optional<Station> getStation(Long stationId){ return stationRepository.findById(stationId);}

    public void addNewStation(Station station){
        Optional<Station> stationByName =
                stationRepository.findStationByStationName(station.getStationName());
        if(stationByName.isPresent()){
            throw new IllegalStateException("station taken!");
        }
        stationRepository.save(station);
    }

    @Transactional
    public void updateStation(Long stationId, Station updatedStation) {
        Station station = stationRepository.findById(stationId)
                .orElseThrow(() -> new IllegalStateException(
                        "station with id " + stationId + " does not exist!"
                ));

        if(updatedStation.getStationName() != null && !Objects.equals(station.getStationName(), updatedStation.getStationName())){
            station.setStationName(updatedStation.getStationName());
        }

        if(updatedStation.getCity() != null && !Objects.equals(station.getCity(), updatedStation.getCity())){
            station.setCity(updatedStation.getCity());
        }

        if(updatedStation.getCountry() != null && !Objects.equals(station.getCountry(), updatedStation.getCountry())){
            station.setCountry(updatedStation.getCountry());
        }

        if(updatedStation.getLatitude() != null && !Objects.equals(station.getLatitude(), updatedStation.getLatitude())){
            station.setLatitude(updatedStation.getLatitude());
        }

        if(updatedStation.getLongitude() != null && !Objects.equals(station.getLongitude(), updatedStation.getLongitude())){
            station.setLongitude(updatedStation.getLongitude());
        }
    }
    public List<StationSelect> getStationSelect(){
        return stationRepository.findAllStation();
    }
    public void deleteStation(Long stationId) {
        stationRepository.deleteById(stationId);
    }

    public void deleteStations(){stationRepository.deleteAll();}

}
