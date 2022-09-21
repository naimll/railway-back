package com.travelservice.routemodule.station;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.travelservice.routemodule.route.Route;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String stationName;

    private Double longitude;

    private Double latitude;
    private String city;

    private String country;

    @JsonIgnore
    @OneToMany(mappedBy = "startPoint")
    private List<Route> startPointRoutes = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "endPoint")
    private List<Route> endPointRoutes = new ArrayList<>();
    @JsonIgnore
    @ManyToMany(mappedBy = "middlePoints")
    private List<Route> middlePointRoutes = new ArrayList<>();


    public Station(String stationName, Double latitude, Double longitude, String city, String country, List<Route> startPointRoutes, List<Route> endPointRoutes, List<Route> middlePointRoutes) {
        this.stationName = stationName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
        this.country = country;
        this.startPointRoutes = startPointRoutes;
        this.endPointRoutes = endPointRoutes;
        this.middlePointRoutes = middlePointRoutes;
    }

}
