package com.ticketService.ticket;

/*import com.travelservice.trainmodule.seat.Seat;
import com.travelservice.trainmodule.train.Train;*/

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Repository
@Service
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Optional<Object> findBySeatNr(Long seatNr);

  /* @Query("" +
            "update Seat s set s.status = true where s.id = Ticket t t.status = false ")
    Seat selectSeat(@Param("seatId") Long seatId);*/
}
