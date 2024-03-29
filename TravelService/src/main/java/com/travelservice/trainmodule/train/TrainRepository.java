package com.travelservice.trainmodule.train;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainRepository extends JpaRepository<Train, Long>  {

    Optional<Train> findByTrainName (String trainName);


}

