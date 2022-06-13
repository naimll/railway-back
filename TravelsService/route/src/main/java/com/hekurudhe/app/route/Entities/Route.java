package com.hekurudhe.app.route.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Route {

    @Id
    @SequenceGenerator(
            name = "route_id_sequence",
            sequenceName = "route_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "route_id_sequence"
    )
    @Column (name ="Id")
    private Integer Id;
    private String routeName;
    private Integer startPoint;
    private Integer endPoint;
    @OneToMany(mappedBy = "route")
    private List<Station> middlePoints;
    private Double distance;
    @OneToMany(mappedBy = "route")
    private List<Attraction> attractions;



    public Route(String routeName, Integer startPoint , Integer endPoint, List<Station> middlePoints, Double distance, List<Attraction> attractions){
        this.routeName = routeName;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.middlePoints = middlePoints;
        this.distance = distance;
        this.attractions = attractions;
    }
}
