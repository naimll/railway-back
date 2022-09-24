package com.travelservice.routemodule.attraction;

import an.awesome.pipelinr.Pipeline;
import com.travelservice.routemodule.NotificationModel;
import com.travelservice.routemodule.route.Route;
import com.travelservice.routemodule.route.Route;
import com.travelservice.routemodule.station.Station;
import com.travelservice.routemodule.station.StationService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
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
import java.util.*;

@CrossOrigin(origins = "http://localhost:3000")
@RefreshScope
@RestController
@RequestMapping("/api/attractions")
public class AttractionController {

    private final AttractionService attractionService;
    RestTemplate restTemplate;

    @Autowired
    public AttractionController(AttractionService attractionService, RestTemplate r) {
        this.attractionService = attractionService;
        restTemplate = r;
    }

    @GetMapping
    public List<Attraction> getAttractions() {
        return attractionService.getAttractions();
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    @Produces({ MediaType.APPLICATION_JSON_VALUE })
    public Attraction addAttraction(
            String attraction,
            String location,
            String description,
            @RequestPart("file") MultipartFile file) {

        // String uri =
        // "http://localhost:44326/api/v1/notification/sendNotificationToAll";
        //
        // HttpHeaders headers = new HttpHeaders();
        // // set `content-type` header
        // headers.setContentType(MediaType.APPLICATION_JSON);
        // // set `accept` header
        //// headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)
        // AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        //
        // headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        // NotificationModel n = new NotificationModel("Hello From
        // JAVA","Hello","Hello");
        // HttpEntity<NotificationModel> entity = new HttpEntity<>(n, headers);
        // NotificationModel result =
        // restTemplate.exchange(uri, HttpMethod.POST, entity,
        // NotificationModel.class).getBody();

        Attraction aJson = attractionService.getJson(attraction, location, description, file);
        return aJson;
    }

    @PostMapping(path = "updateAttraction", consumes = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE })
    @Produces({ MediaType.APPLICATION_JSON_VALUE })
    public void updateAttraction(
            Long id,
            String attractionName,
            String description,
            String location,
            String image,
            @RequestPart("file") MultipartFile file) {
        Attraction a = new Attraction(attractionName, location, description, image);
        a.setId(id);
        attractionService.updateAttraction(id, a, file);
    }

    @GetMapping("/attractions/{attractionId}")
    public ResponseEntity<Attraction> getAttractionById(@PathVariable("attractionId") Long attractionId) {
        Optional<Attraction> attractionData = Optional.ofNullable(attractionService.getById(attractionId));
        if (attractionData.isPresent()) {
            return new ResponseEntity<>(attractionData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*
     * @PostMapping("/attractions/{attraction}/{location}/{description}/{image}")
     * public org.apache.http.HttpStatus
     * createAttraction(@PathVariable("attraction") String
     * attraction, @PathVariable("location")String
     * location, @PathVariable("description")String
     * description, @PathVariable("image")String image){
     * 
     * try{
     * attractionRepository
     * .save(new Attraction(attraction, location, description, image));
     * return new org.apache.http.HttpStatus() {
     * 
     * @Override
     * public String toString() {
     * return super.toString();
     * }
     * };
     * }catch(Exception e){
     * return null;
     * }
     * }
     */

    /*
     * @PutMapping("/{id}")
     * public ResponseEntity<Attraction> updateAttraction(@PathVariable("id") Long
     * id, @RequestBody Attraction attraction){
     * Optional<Attraction> attractionData = attractionRepository.findById(id);
     * if(attractionData.isPresent()){
     * Attraction _attraction = attractionData.get();
     * _attraction.setAttractionName(attraction.getAttractionName());
     * _attraction.setLocation(attraction.getLocation());
     * _attraction.setDescription(attraction.getDescription());
     * _attraction.setImage(attraction.getImage());
     * _attraction.setRoutes(attraction.getRoutes());
     * return new ResponseEntity<>(attractionRepository.save(_attraction),
     * HttpStatus.OK);
     * }else {
     * return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     * }
     * }
     */

    @DeleteMapping(path = "{attractionId}")
    public void deleteAttraction(@PathVariable("attractionId") Long attractionId) {
        attractionService.deleteAttraction(attractionId);
    }

}
