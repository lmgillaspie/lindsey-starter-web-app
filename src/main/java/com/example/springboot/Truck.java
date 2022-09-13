package com.example.springboot;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Builder
@Entity
public class Truck {

    @Id
    private int id;

    private TruckStatus status;
}
