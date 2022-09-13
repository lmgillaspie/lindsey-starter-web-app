package com.example.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.Repositories.TruckRepository;
import com.example.springboot.Truck.TruckBuilder;

@RestController
public class TruckController {

    @Autowired
    private TruckRepository repository;

    @PostMapping("/")
    public int create() {

        TruckBuilder builder = new TruckBuilder();

        Truck newTruck = builder.build();

        newTruck = repository.save(newTruck);

        return newTruck.getId();
    }

}
