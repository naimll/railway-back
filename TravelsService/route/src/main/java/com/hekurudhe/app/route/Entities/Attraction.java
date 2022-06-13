package com.hekurudhe.app.route.Entities;

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
public class Attraction {

    @Id
    @SequenceGenerator(
            name = "attraction_id_sequence",
            sequenceName = "attraction_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "attraction_id_sequence"
    )
    @Column (name ="attraction_Id")
    private Integer Id;
    private String attractionName;
    private String location;
    private String description;
    private String image;

    @ManyToOne
    @JoinColumn(name="Id")
    private Route route;



    public Attraction(String attractionName, String location, String description, String image){
        this.attractionName = attractionName;
        this.location = location;
        this.description = description;
        this.image = image;
    }
}
