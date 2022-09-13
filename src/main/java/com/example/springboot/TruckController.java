package com.example.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.Repositories.TruckRepository;
import com.example.springboot.Truck.TruckBuilder;

@RestController
@RequestMapping("/trucks")
public class TruckController {

    @Autowired
    private TruckRepository repository;

    @PostMapping("/")
    public Truck create() {

        Truck newTruck = Truck.builder()
                .status(TruckStatus.ELIGIBLE_FOR_INSPECTION)
                .build();

        return repository.save(newTruck);
    }
}
