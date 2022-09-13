package com.example.springboot;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TruckController {
    
    @PostMapping("/")
    public int create() {

        

        return 1;
    }

}
