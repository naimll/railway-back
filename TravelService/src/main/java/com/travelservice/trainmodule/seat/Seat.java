package com.travelservice.trainmodule.seat;



/*import com.ticketService.ticket.Ticket; */
import com.travelservice.trainmodule.train.Train;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;



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
    @JoinColumn(name="trainId", referencedColumnName = "id")
    private Train trainId;

   /* @OneToOne
    @JoinColumn(name="ticketId", referencedColumnName = "id")
    private Ticket ticketId; */


    @NotNull
    private boolean status;
    enum SeatCategory {
        FIRSTCLASS,
        REGULAR;
    }
    @Enumerated
    @NotNull
   SeatCategory seatCategory;



    public Seat (Train trainId, boolean status/*, Ticket ticketId*/, SeatCategory seatCategory){

        this.status = status;
        this.trainId=trainId;
        /*this.ticketId=ticketId;*/
        this.seatCategory=seatCategory;

    }


}
