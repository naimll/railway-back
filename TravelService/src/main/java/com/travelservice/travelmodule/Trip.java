package com.travelservice.travelmodule;

import com.travelservice.routemodule.route.Route;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime departure_time;

    private LocalDateTime arrival_time;

    private String status;

    //private Train train;

    @ManyToOne
    @JoinColumn(name="routeTrip_id", referencedColumnName = "id")
    private Route routeTrip;

    public Trip (LocalDateTime departure_time, LocalDateTime arrival_time, String status, Route routeTrip){
        this.departure_time = departure_time;
        this.arrival_time = arrival_time;
        this.status = status;
        this.routeTrip = routeTrip;
    }


}
