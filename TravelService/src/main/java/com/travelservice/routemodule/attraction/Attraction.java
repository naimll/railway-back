package com.travelservice.routemodule.attraction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.travelservice.routemodule.route.Route;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Attraction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String attractionName;
    private String location;
    private String description;
    private String image;

    @JsonIgnore
    @ManyToMany(mappedBy = "attractions")
    List<Route> routes = new ArrayList<>();


    public Attraction(String attractionName, String location, String description, String image){
        this.attractionName = attractionName;
        this.location = location;
        this.description = description;
        this.image = image;
    }

}
