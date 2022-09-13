package com.example.springboot;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.springboot.Repositories.TruckRepository;
import com.example.springboot.Truck.TruckBuilder;
import com.rabbitmq.client.RpcClient.Response;

@RestController
@RequestMapping("/trucks")
public class TruckController {

    @Autowired
    private TruckRepository repository;

    @PostMapping("/")
    public ResponseEntity<Truck> create() {

        Truck newTruck = Truck.builder()
                .status(TruckStatus.ELIGIBLE_FOR_INSPECTION)
                .build();

        newTruck = repository.save(newTruck);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newTruck.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Truck> read(@PathVariable Integer id) {
        Optional<Truck> optionalTruck = repository.findById(id);

        if (optionalTruck.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(optionalTruck.get());
    }
}
