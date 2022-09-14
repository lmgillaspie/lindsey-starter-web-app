package com.example.springboot.services;

import com.example.springboot.Truck;
import com.example.springboot.TruckStatus;
import com.example.springboot.repositories.TruckRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationHandlerService {

    private final TruckService truckService;
    private final TruckRepository truckRepository;

    public void handleReservationStarted(Integer truckId) {
        Truck truck = truckService.findTruck(truckId);
        if (truck.getStatus() != TruckStatus.BEING_INSPECTED) {
            truck.setStatus(TruckStatus.NOT_ELIGIBLE_FOR_INSPECTION);
            truckRepository.save(truck);
        } else {
            log.warn("Unable to set truck status to NOT_ELIGIBLE_FOR_INSPECTION for truck: {}", truck);
        }
    }

    public void handleReservationEnded(Integer truckId) {
        Truck truck = truckService.findTruck(truckId);
        if (truck.getStatus() != TruckStatus.BEING_INSPECTED) {
            truck.setStatus(TruckStatus.ELIGIBLE_FOR_INSPECTION);
            truckRepository.save(truck);
        } else {
            log.warn("Unable to set truck status to ELIGIBLE_FOR_INSPECTION for truck: {}", truck);
        }
    }
}
