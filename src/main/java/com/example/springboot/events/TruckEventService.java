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
        streamBridge.send("truck-added", TruckEvent.builder()
                .truckId(truckId)
                .created(Instant.now())
                .build());
    }
}
