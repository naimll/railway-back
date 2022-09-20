package com.travelservice.routemodule.attraction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;


@Service
public class AttractionService {

    private final AttractionRepository attractionRepository;

    @Autowired
    public AttractionService(AttractionRepository attractionRepository){this.attractionRepository = attractionRepository;}

    public List<Attraction> getAttractions() {return attractionRepository.findAll();}


    public void addAttraction(MultipartFile file, Attraction attraction){
        //Attraction a = new Attraction();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains("..")){
            System.out.println("Not a proper file!");
        }
        /*a.setAttractionName(attractionName);
        a.setLocation(location);
        a.setDescription(description);*/
        try{
            attraction.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        }catch(IOException e){
            e.printStackTrace();
        }

        attractionRepository.save(attraction);
    }

    public void deleteAttraction(Long attractionId) {

        attractionRepository.deleteById(attractionId);
    }


}
