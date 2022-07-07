/*
package com.travelservice.routemodule.attraction;


import an.awesome.pipelinr.Pipeline;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:8080")

@RefreshScope
@RestController
@RequestMapping("/api")
public class AttractionController {
    @Autowired
    AttractionRepository attractionRepository;
    Pipeline pipeline;
    @GetMapping("/attractions")
    public ResponseEntity<List<Attraction>> getAllAttractions(@RequestParam(required = false) String location) {
        try {
            List<Attraction> attractions = new ArrayList<Attraction>();
            if (location == null)
                attractionRepository.findAll().forEach(attractions::add);
            else
                attractionRepository.findByLocationContaining(location).forEach(attractions::add);
            if (attractions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(attractions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/attractions/{id}")
    public Optional<Attraction> getAttractionById(Authentication authentication, @PathVariable("id") Integer Id){
        Optional<Attraction> attractionData = attractionRepository.findById(Id);
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
//                .getPrincipal();
//        String username = userDetails.getUsername();
        System.out.println("\n[username]: " + authentication.toString() + "\n");
        return attractionData;


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

    @PostMapping("/attractions/{attraction}/{location}/{description}/{image}")
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
    }


    @PutMapping("/attractions/{id}")
    public ResponseEntity<Attraction> updateAttraction(@PathVariable("id") Integer Id, @RequestBody Attraction attraction){
        Optional<Attraction> attractionData = attractionRepository.findById(Id);
        if(attractionData.isPresent()){
            Attraction _attraction = attractionData.get();
            _attraction.setAttractionName(attraction.getAttractionName());
            _attraction.setLocation(attraction.getLocation());
            _attraction.setDescription(attraction.getDescription());
            _attraction.setImage(attraction.getImage());
            _attraction.setRoute(attraction.getRoute());
            return new ResponseEntity<>(attractionRepository.save(_attraction), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/attractions/{id}")
    public ResponseEntity<HttpStatus> deleteAttraction(@PathVariable("id") Integer Id){
        try{
            attractionRepository.deleteById(Id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/attractions")
    public ResponseEntity<HttpStatus> deleteAllAttractions(){
        try{
            attractionRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

*/
