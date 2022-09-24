package com.travelservice.routemodule.attraction;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelservice.routemodule.NotificationModel;
import com.travelservice.routemodule.route.Route;
import com.travelservice.routemodule.services.NotificationServiceClient;
import com.travelservice.routemodule.station.Station;
import io.swagger.v3.core.util.Json;
import org.apache.tomcat.jni.Directory;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AttractionService {

    private final AttractionRepository attractionRepository;
    private final Path root = Paths.get("uploads");

    @Autowired
    private NotificationServiceClient notificationServiceClient;

    @Autowired
    public AttractionService(AttractionRepository attractionRepository) {
        this.attractionRepository = attractionRepository;
    }

    public List<Attraction> getAttractions() {
        return attractionRepository.findAll();
    }

    /*
     * public void addAttraction(Attraction attraction){
     * attractionRepository.save(attraction);
     * }
     */
    public Attraction getById(long id) {
        return attractionRepository.findById(id);
    }

    public void init() {
        if (!Files.exists(root)) {
            try {
                Files.createDirectory(root);
            } catch (IOException e) {
                throw new RuntimeException("Could not initialize folder for upload!");
            }
        }
    }

    public void save(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file; choose another file. Error: " + e.getMessage());
        }
    }

    public Resource load(String filename) {
        try {
            Path pathFile = root.resolve(filename);
            Resource resource = new UrlResource(pathFile.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    // public Attraction addAttraction(MultipartFile file, String attractionName,
    // String location, String desc){
    // Attraction a = new Attraction();
    //
    //
    // try{
    // a.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
    // }catch(IOException e){
    // e.printStackTrace();
    // }
    // a.setAttractionName(attractionName);
    // a.setLocation(location);
    // a.setDescription(desc);
    // attractionRepository.save(a);
    // return a;
    // }
    public Attraction getJson(String attraction, String location, String description, MultipartFile file) {
        Attraction aJson = new Attraction(attraction, location, description, file.getOriginalFilename());
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            aJson = objectMapper.readValue(attraction, Attraction.class);
        } catch (IOException err) {
            System.out.printf("Error", err.toString());
        }
        String imgName = StringUtils.cleanPath(file.getOriginalFilename());
        aJson.setImage(imgName);
        init();
        save(file);
        load(file.getOriginalFilename());
        attractionRepository.save(aJson);
        notificationServiceClient.sendNotificationToAll(new NotificationModel("HEllo from Spring", "ss", "as"));
        return aJson;
    }

    @Transactional
    public void updateAttraction(Long attractionId, @RequestPart("attraction") Attraction updatedAttraction,
            @RequestPart("file") MultipartFile updatedFile) {
        Attraction attraction = attractionRepository.findById(attractionId)
                .orElseThrow(() -> new IllegalStateException(
                        "attraction with id " + attractionId + " does not exist!"));

        // String attrString = attraction.toString();
        //
        // if(updateAttraction != null && !attrString.equals(updatedAttraction)){
        // attrString = updatedAttraction;
        // }

        if (updatedAttraction.getAttractionName() != null
                && !Objects.equals(attraction.getAttractionName(), updatedAttraction.getAttractionName())) {
            attraction.setAttractionName(updatedAttraction.getAttractionName());
        }

        if (updatedAttraction.getLocation() != null
                && !Objects.equals(attraction.getLocation(), updatedAttraction.getLocation())) {
            attraction.setLocation(updatedAttraction.getLocation());
        }

        if (updatedAttraction.getDescription() != null
                && !Objects.equals(attraction.getDescription(), updatedAttraction.getDescription())) {
            attraction.setDescription(updatedAttraction.getDescription());
        }

        if (!Objects.equals(attraction.getImage(), (StringUtils.cleanPath(updatedFile.getOriginalFilename())))) {
            File fileToDelete = new File("uploads/" + attraction.getImage());
            fileToDelete.delete();
            attraction.setImage(StringUtils.cleanPath(updatedFile.getOriginalFilename()));
            init();
            save(updatedFile);
            load(updatedFile.getOriginalFilename());
        }

    }

    public void deleteAttraction(Long attractionId) {

        attractionRepository.deleteById(attractionId);
    }

}
