package com.travelservice.routemodule.attraction;


import an.awesome.pipelinr.Pipeline;
import com.travelservice.routemodule.station.Station;
import com.travelservice.routemodule.station.StationService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @PostMapping
    public void createNewAttraction(@RequestParam("file") MultipartFile file, @RequestBody Attraction attraction){
        attractionService.addAttraction(file,attraction);
    }
   /* @PostMapping("/upload")
    public void createNewAttraction(@RequestParam("file") MultipartFile file){
        attractionService.uploadFile(file);
    }
*/


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

