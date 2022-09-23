package com.travelservice.trainmodule.seat;


import com.travelservice.routemodule.station.Station;
import com.travelservice.trainmodule.train.Train;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity


public class Seat {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name="train_id", referencedColumnName = "id")
    private Train trainId;
    @NotNull
    private boolean status;
    enum SeatCategory {
        FIRSTCLASS,
        REGULAR;
    }
    @Enumerated
    @NotNull
   SeatCategory seatCategory;



    public Seat (Train trainId, boolean status, SeatCategory seatCategory){

        this.status = status;
        this.seatCategory=seatCategory;
        this.trainId=trainId;
    }


}
