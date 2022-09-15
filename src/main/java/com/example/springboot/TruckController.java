package com.example.springboot;

import com.example.springboot.events.TruckEventService;
import com.example.springboot.services.TruckService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/trucks")
@RequiredArgsConstructor
public class TruckController {

    private final TruckEventService truckEventService;
    private final TruckService truckService;

    @PostMapping("/")
    public ResponseEntity<Truck> create() {

        var newTruck = truckService.createTruck();

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newTruck.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/")
    public Iterable<Truck> getTrucks() {
        return truckService.findAllTrucks();
    }

    @GetMapping("/{truckId}")
    public ResponseEntity<Truck> read(@PathVariable Integer truckId) {
        Truck truck = truckService.findTruck(truckId);

        return ResponseEntity.ok(truck);
    }

    @PutMapping("/{truckId}/inspect")
    public Truck inspect(@PathVariable Integer truckId) {
        return truckService.startInspection(truckId);
    }

    @PutMapping("/{truckId}/completeinspection")
    public Truck completeInspection(@PathVariable Integer truckId) {
        return truckService.completeInspection(truckId);
    }

}
