package com.example.springboot.services;

import com.example.springboot.Truck;
import com.example.springboot.TruckStatus;
import com.example.springboot.events.TruckEventService;
import com.example.springboot.exceptions.IllegalTruckStateException;
import com.example.springboot.repositories.TruckRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TruckService {

    private final TruckRepository truckRepository;
    private final TruckEventService truckEventService;

    public Truck findTruck(Integer truckId) {
        return truckRepository.findById(truckId).orElseThrow();
    }

    public Iterable<Truck> findAllTrucks() { return truckRepository.findAll(); }

    public Truck createTruck() {

        Truck newTruck = Truck.builder()
                .status(TruckStatus.ELIGIBLE_FOR_INSPECTION)
                .build();

        newTruck = truckRepository.save(newTruck);
        truckEventService.notifyTruckAdded(newTruck.getId());

        return newTruck;
    }

    public Truck startInspection(Integer truckId) {
        Truck truck = this.findTruck(truckId);

        if (truck.getStatus() != TruckStatus.NOT_ELIGIBLE_FOR_INSPECTION) {
            truck.setStatus(TruckStatus.BEING_INSPECTED);
            truck = truckRepository.save(truck);

            truckEventService.notifyTruckInspectionStarted(truckId);

            return truck;
        }

        throw new IllegalTruckStateException();
    }

    public Truck completeInspection(Integer truckId) {
        Truck truck = this.findTruck(truckId);

        if (truck.getStatus() == TruckStatus.BEING_INSPECTED) {
            truck.setStatus(TruckStatus.ELIGIBLE_FOR_INSPECTION);
            truck = truckRepository.save(truck);

            truckEventService.notifyTruckInspectionCompleted(truckId);

            return truck;
        }

        throw new IllegalTruckStateException();
    }
}
