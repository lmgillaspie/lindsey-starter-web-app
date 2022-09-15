package com.example.springboot.events;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class TruckEventService {

    private final StreamBridge streamBridge;

    public void notifyTruckAdded(int truckId) {
        createTruckEvent(truckId, "truck-added");
    }

    public void notifyTruckInspectionStarted(Integer truckId) {

        createTruckEvent(truckId, "truck-inspection-started");
    }

    public void notifyTruckInspectionCompleted(Integer truckId) {
        createTruckEvent(truckId, "truck-inspection-completed");

    }

    private void createTruckEvent(Integer truckId, String s) {
        streamBridge.send(s, TruckEvent.builder()
                .truckId(truckId)
                .created(Instant.now())
                .build());
    }
}
