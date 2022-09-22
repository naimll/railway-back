package com.travelservice.routemodule.station.weather;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;



@RestController
public class WeatherController {
    String apiKey = System.getenv("API_KEY");

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
                getForEntity("https://api.openweathermap.org/data/2.5/forecast?q=" + city + "&appid=90ce174e8abeec3884c95933d6157165&units=imperial", Object.class);
        return response;
    }
}
