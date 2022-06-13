package com.hekurudhe.app.route.Controllers;

import com.hekurudhe.app.route.Entities.Attraction;
import com.hekurudhe.app.route.Repositories.AttractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Attr;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class AttractionController {
    @Autowired
    AttractionRepository attractionRepository;
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
    public ResponseEntity<Attraction> getAttractionById(@PathVariable("id") Integer Id){
        Optional<Attraction> attractionData = attractionRepository.findById(Id);
        if(attractionData.isPresent()){
            return new ResponseEntity<>(attractionData.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/attractions/{attractionName}")
    public ResponseEntity<Attraction> getAttractionByName(@PathVariable("attractionName") String attractionName){
        Optional<Attraction> attractionData = Optional.ofNullable(attractionRepository.findByAttractionName(attractionName));
        if(attractionData.isPresent()){
            return new ResponseEntity<>(attractionData.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/attractions")
    public ResponseEntity<Attraction> createAttraction(@RequestBody Attraction attraction){
        try{
            Attraction _attraction = attractionRepository
                    .save(new Attraction(attraction.getAttractionName(), attraction.getLocation(), attraction.getDescription(), attraction.getImage()));
            return new ResponseEntity<>(_attraction, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
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
