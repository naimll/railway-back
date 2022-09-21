package com.travelservice.routemodule.route;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.travelservice.routemodule.attraction.Attraction;
import com.travelservice.routemodule.station.Station;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name="startPoint_id", referencedColumnName = "id")
    private Station startPoint;


    @ManyToOne
    @JoinColumn(name="endPoint_id", referencedColumnName = "id")
    private Station endPoint;

    @ManyToMany
    @JoinTable(
            name="middlepointsOfRoutes",
            joinColumns = @JoinColumn(name="route_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="middlepoint_id", referencedColumnName = "id")
    )
     List<Station> middlePoints = new ArrayList<>();
    private Double distance;

    private Double duration;

    @ManyToMany
    @JoinTable(
            name = "route_attractions",
            joinColumns = @JoinColumn(name = "route_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "attraction_id", referencedColumnName = "id")
    )
     List<Attraction> attractions = new ArrayList<>();
    public Route(Station startPoint , Station endPoint, List<Station> middlePoints, Double distance, Double duration, List<Attraction> attractions){
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.middlePoints = middlePoints;
        this.distance = distance;
        this.duration = duration;
        this.attractions = attractions;
    }

}
