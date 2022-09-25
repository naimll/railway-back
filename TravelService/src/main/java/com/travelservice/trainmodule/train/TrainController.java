package com.travelservice.trainmodule.train;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/trains")

public class TrainController {

    private final TrainService trainService;

    @Autowired
    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    @GetMapping
    public List<Train> getTrains(){
        return trainService.getTrains();
    }

    @PostMapping
    public void createNewTrain(@RequestBody Train train){
        trainService.addNewTrain(train);
    }

    @DeleteMapping(path = "{trainId}")
    public void deleteTrain(@PathVariable("trainId") Long trainId){
        trainService.deleteTrain(trainId);
    }

    @PutMapping(path = "{trainId}")
    public void updateTrain(
            @PathVariable("trainId") Long trainId,
            @RequestBody Train updatedTrain
    ){
        trainService.updateTrain(trainId, updatedTrain);
    }
}
