package com.example.Events;

import java.time.Instant;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.convert.Jsr310Converters.InstantToDateConverter;

import lombok.Data;

@Data
public class NewTruckEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private long TruckId;

    private Instant createdInstant;
    private Instant publishedInstant;
}
