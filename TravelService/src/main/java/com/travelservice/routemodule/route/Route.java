package com.travelservice.routemodule.route;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JoinColumn(name="station_id", referencedColumnName = "id", insertable=false, updatable=false)
    private Station startPoint;


    @ManyToOne
    @JoinColumn(name="station_id", referencedColumnName = "id", insertable=false, updatable=false)
    private Station endPoint;

    @ManyToMany
    @JoinTable(
            name="middlepointsOfRoutes",
            joinColumns = @JoinColumn(name="route_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="station_id", referencedColumnName = "id")
    )
     List<Station> middlePoints = new ArrayList<>();
    private Double distance;


    public Route(Station startPoint , Station endPoint, List<Station> middlePoints, Double distance){
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.middlePoints = middlePoints;
        this.distance = distance;
    }

}
