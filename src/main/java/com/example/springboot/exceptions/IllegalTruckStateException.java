package com.example.springboot.exceptions;

import lombok.Getter;

@Getter
public class IllegalTruckStateException extends RuntimeException {

    private int truckId;
    private String message;


    public IllegalTruckStateException(int truckId, String message) {
        this.truckId = truckId;
        this.message = message;
    }
}
