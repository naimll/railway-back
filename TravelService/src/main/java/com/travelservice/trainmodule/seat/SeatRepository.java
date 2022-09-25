package com.travelservice.trainmodule.seat;

import com.travelservice.trainmodule.train.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    Optional<Seat> findByTrainId(Train trainId);

    Optional<Seat> findSeatById (Long seatId);



    @Query("SELECT s FROM Seat s WHERE s.status = false")
    List <Seat> findAllFreeSeats();

    @Query("update Seat s set s.status = true where s.id = ?1 and s.status = false ")
    Seat selectSeat(@Param("seatId") Long seatId);







}


