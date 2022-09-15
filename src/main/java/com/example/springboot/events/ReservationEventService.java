package com.example.springboot.events;


import com.example.springboot.services.ReservationHandlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationEventService {

    private final ReservationHandlerService eventHandlerService;

    @Bean
    public Consumer<ReservationEvent> reservationCreatedReceived() {
        return reservationEvent -> log.info("Received reservationEvent and doing important work j/k :) lol: {}", reservationEvent);
    }

    @Bean
    public Consumer<ReservationEvent> reservationStartedReceived() {
        return reservationEvent -> {
            log.info("Reservation Started for truck {}", reservationEvent.getTruckId());
            eventHandlerService.handleReservationStarted(reservationEvent.getTruckId());
        };
    }

    @Bean
    public Consumer<ReservationEvent> reservationEndedReceived() {
        return reservationEvent -> {
            log.info("Truck {} returned", reservationEvent.getTruckId());
            eventHandlerService.handleReservationEnded(reservationEvent.getTruckId());
        };
    }
}
