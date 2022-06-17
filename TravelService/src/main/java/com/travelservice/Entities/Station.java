package com.travelservice.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Station {

    @Id
    @SequenceGenerator(
            name = "station_id_sequence",
            sequenceName = "station_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "station_id_sequence"
    )
    @Column (name ="station_Id")

    private Integer Id;
    private String stationName;
    private String location;
    private String city;

    @ManyToOne

    @JoinColumn(name="Id")
    private Route route;


    public Station(String stationName, String location, String city){
        this.stationName = stationName;
        this.location = location;
        this.city = city;
    }


}
