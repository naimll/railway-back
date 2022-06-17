package com.travelservice.Controllers;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/api/train")
public class TrainController {

    @GetMapping("/hello")
    public String sayHello(String message){
        return message;
    }
}
