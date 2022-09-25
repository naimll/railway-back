package com.travelservice.trainmodule.train;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.travelservice.trainmodule.seat.Seat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Train {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String trainName;
    private int nrOfSeats;
    private int nrOfWagons;


    @JsonIgnore
    @OneToMany(mappedBy = "trainId")
    private List<Seat> seatIdSeats = new ArrayList<>();

    enum TrainCategory {
        FAST,
        REGULAR
    }
    @Enumerated
    @NotNull
    TrainCategory trainCategory;



    public Train (String trainName, int nrOfSeats, int nrOfWagons,List<Seat> seatIdSeats,TrainCategory trainCategory){
        this.trainName = trainName;
        this.nrOfSeats = nrOfSeats;
        this.nrOfWagons = nrOfWagons;
        this.seatIdSeats=seatIdSeats;
        this.trainCategory = trainCategory;


    }


}
