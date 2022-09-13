package com.example.springboot;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

import org.springframework.data.domain.AbstractAggregateRoot;

@Getter
@Builder
@Entity
public class Truck extends AbstractAggregateRoot<Truck> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private TruckStatus status;
}
