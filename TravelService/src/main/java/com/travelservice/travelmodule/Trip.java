package com.travelservice.travelmodule;


import com.travelservice.routemodule.route.Route;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.client.RestTemplate;

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

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private String status;

    //private Train train;

    @ManyToOne
    @JoinColumn(name="routeTrip_id", referencedColumnName = "id")
    private Route routeTrip;
    //Route routeTrip=  new RestTemplate().getForObject("http://localhost:8080/api/routes/{id}", Route.class, getId());


    public Trip (LocalDateTime departure_time, LocalDateTime arrival_time, String status, Route routeTrip){
        this.departureTime = departure_time;
        this.arrivalTime = arrival_time;
        this.status = status;
        this.routeTrip = routeTrip;
    }


}
