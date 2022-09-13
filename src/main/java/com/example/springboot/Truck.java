package com.example.springboot;

import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.Id;

@Builder
@Entity
public class Truck {

    @Id
    private int id;

    private TruckStatus status;
}
