package com.travelservice.trainmodule.seat;

import com.travelservice.routemodule.station.Station;
import com.travelservice.routemodule.station.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/seats")

public class SeatController {

    private final SeatService seatService;

    @Autowired
    public SeatController(SeatService seatService){
        this.seatService = seatService;
    }


    @GetMapping
    public List<Seat> getSeats(){
        return seatService.getAllSeats();
    }

    @PostMapping
    public void createNewSeat(@RequestBody Seat seat){seatService.addNewSeat(seat);
    }

    @DeleteMapping(path = "{seatId}")
    public void deleteSeat(@PathVariable("seatId") Long seatId){
        seatService.deleteSeat(seatId);
    }

    @PostMapping(path = "{seatId}")
    public Seat selectSeat(@PathVariable("seatId") Long seatId){
        return seatService.selectSeat(seatId);
    }


}
