package com.example.springboot;

import lombok.Builder;

@Builder
public class Truck {
    private int id;
    private TruckStatus status;
}
