package com.travelservice.routemodule.attraction;


import an.awesome.pipelinr.Pipeline;
import com.travelservice.routemodule.NotificationModel;
import com.travelservice.routemodule.route.Route;
import com.travelservice.routemodule.station.Station;
import com.travelservice.routemodule.station.StationService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
//import org.springframework.http.MediaType;
import java.io.File;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RefreshScope
@RestController
@RequestMapping("/api/attractions")
public class AttractionController {

    private final AttractionService attractionService;
    @Autowired
    public AttractionController(AttractionService attractionService){
        this.attractionService = attractionService;
    }

    @GetMapping
    public List<Attraction> getAttractions(){
        return attractionService.getAttractions();
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @Produces({ MediaType.APPLICATION_JSON_VALUE})
    public Attraction addAttraction(
                              @RequestPart("attraction") String attraction,
                              @RequestPart("file") MultipartFile file){
            RestTemplate restTemplate = new RestTemplate();
            String uri = "https://localhost:44326/api/v1/notification/sendNotificationToAll";
            NotificationModel n = new NotificationModel("Hello","Hello","Hello");
            NotificationModel result = restTemplate.postForObject(uri,n,NotificationModel.class);
        Attraction aJson = attractionService.getJson(attraction, file);
        return aJson;
    }

    @PutMapping(path = "{attractionId}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public void updateAttraction(
            @PathVariable("attractionId") Long attractionId,
            @RequestPart("attraction") Attraction updatedAttraction,
            @RequestPart("file") MultipartFile file
    ){
        attractionService.updateAttraction(attractionId,updatedAttraction, file);
    }



//    @GetMapping("/attractions/{attractionName}")
//    public ResponseEntity<Attraction> getAttractionByName(@PathVariable("attractionName") String attractionName){
//        Optional<Attraction> attractionData = Optional.ofNullable(attractionRepository.findByAttractionName(attractionName));
//        if(attractionData.isPresent()){
//            return new ResponseEntity<>(attractionData.get(), HttpStatus.OK);
//        }else{
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    /*@PostMapping("/attractions/{attraction}/{location}/{description}/{image}")
    public org.apache.http.HttpStatus createAttraction(@PathVariable("attraction") String attraction, @PathVariable("location")String location, @PathVariable("description")String description, @PathVariable("image")String image){

        try{
            attractionRepository
                    .save(new Attraction(attraction, location, description, image));
            return new org.apache.http.HttpStatus() {
                @Override
                public String toString() {
                    return super.toString();
                }
            };
        }catch(Exception e){
            return null;
        }
    }*/


    /*@PutMapping("/{id}")
    public ResponseEntity<Attraction> updateAttraction(@PathVariable("id") Long id, @RequestBody Attraction attraction){
        Optional<Attraction> attractionData = attractionRepository.findById(id);
        if(attractionData.isPresent()){
            Attraction _attraction = attractionData.get();
            _attraction.setAttractionName(attraction.getAttractionName());
            _attraction.setLocation(attraction.getLocation());
            _attraction.setDescription(attraction.getDescription());
            _attraction.setImage(attraction.getImage());
            _attraction.setRoutes(attraction.getRoutes());
            return new ResponseEntity<>(attractionRepository.save(_attraction), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/

    @DeleteMapping(path = "{attractionId}")
    public void deleteAttraction(@PathVariable("attractionId") Long attractionId){
        attractionService.deleteAttraction(attractionId);
    }


}

