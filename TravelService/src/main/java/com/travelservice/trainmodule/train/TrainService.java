package com.travelservice.trainmodule.train;


import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TrainService {

    private final TrainRepository trainRepository;

    public TrainService(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    public List<Train> getTrains(){

        return trainRepository.findAll();
    }

    public void addNewTrain(Train train){

        if (trainRepository.findByTrainName(train.getTrainName()).isPresent()){

            throw new IllegalStateException("train exists");
        }
        trainRepository.save(train);
    }

    public void deleteTrain(Long trainId) {
        trainRepository.deleteById(trainId);
    }


   @Transactional
    public void updateTrain(Long trainId, Train updatedTrain) {
        Train train = trainRepository.findById(trainId)
                .orElseThrow(() -> new IllegalStateException(
                        "train with id " + trainId + " does not exist!"
                ));

       if(updatedTrain.getTrainName() != null && !Objects.equals(train.getTrainName(), updatedTrain.getTrainName())){
           train.setTrainName(updatedTrain.getTrainName());
       }
        if(updatedTrain.getNrOfSeats() != 0 && !Objects.equals(train.getNrOfSeats(), updatedTrain.getNrOfSeats())){
            train.setNrOfSeats(updatedTrain.getNrOfSeats());
        }
        if(updatedTrain.getNrOfWagons() != 0 && !Objects.equals(train.getNrOfWagons(), updatedTrain.getNrOfWagons())){
            train.setNrOfWagons(updatedTrain.getNrOfWagons());
        }
       if(updatedTrain.getTrainCategory() != null && !Objects.equals(train.getTrainCategory(), updatedTrain.getTrainCategory())){
           train.setTrainCategory(updatedTrain.getTrainCategory());
       }






   }

}
