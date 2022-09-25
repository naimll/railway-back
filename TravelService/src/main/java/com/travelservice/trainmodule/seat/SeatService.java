package com.travelservice.trainmodule.seat;

import com.fasterxml.jackson.databind.introspect.AnnotationCollector;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
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


        if(seatRepository.findByTrainId(seat.getTrainId()).isPresent() &&
                seatRepository.findSeatById(seat.getId()).isPresent()){
            throw new IllegalStateException("seat exists");
        }

        /* trainRepository.findByTrainName(train.getTrainName()).isPresent()){ */

        seatRepository.save(seat);

    }

    public void deleteSeat(Long seatId) {
        seatRepository.deleteById(seatId);
    }

    public Seat selectSeat(Long seatId) {return seatRepository.selectSeat(seatId);}

    @Transactional
    public void updateSeat(Long seatId, Seat updatedSeat) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new IllegalStateException(
                        "seat with id " + seatId + " does not exist!"
                ));

        if(updatedSeat.getSeatCategory() != null && !Objects.equals(seat.getSeatCategory(), updatedSeat.getSeatCategory())){
            seat.setSeatCategory(updatedSeat.getSeatCategory());
        }



    }



}
