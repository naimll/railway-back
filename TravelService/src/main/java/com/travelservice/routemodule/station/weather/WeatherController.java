package com.travelservice.routemodule.station.weather;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;



@RestController
public class WeatherController {

    @Value("${openweathermap_apikey}")
    String apiKey;

    @GetMapping( value = "/{city}")
    public @ResponseBody Object getWeatherByCity(@PathVariable String city ){
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Object> response = restTemplate.
                getForEntity("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=imperial", Object.class);

        return response;
    }

    @GetMapping( value = "{city}/forecast")
    public @ResponseBody Object getWeatherForecastByCity(@PathVariable String city ){
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Object> response = restTemplate.
                getForEntity("https://api.openweathermap.org/data/2.5/forecast?q=" + city + "&appid=" + apiKey, Object.class);
        return response;
    }
}
