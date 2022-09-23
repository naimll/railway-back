package com.travelservice.trainmodule.seat;

import com.travelservice.routemodule.station.Station;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatService {

    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public List<Seat> getAllSeats(){

        return seatRepository.findAll();
    }



    public List<Seat> findAllFreeSeats(){

        return seatRepository.findAll();
    }

    public void addNewSeat(Seat seat) {

        Optional<Seat> seatById =
                seatRepository.findById(seat.getId());
        if(seatById.isPresent()){
            throw new IllegalStateException("seat exists");
        }

        seatRepository.save(seat);
    }

    public void deleteSeat(Long seatId) {
        seatRepository.deleteById(seatId);
    }

    public Seat selectSeat(Long seatId) {return seatRepository.selectSeat(seatId);}




}
