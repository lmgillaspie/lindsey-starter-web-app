package com.example.springboot;

import com.example.springboot.exceptions.IllegalTruckStateException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalTruckStateException.class)
    public ResponseEntity<IllegalTruckState> handleIllegalTruckState(IllegalTruckStateException e) {
        return new ResponseEntity<>(IllegalTruckState.builder()
                .truckId(e.getTruckId())
                .message(e.getMessage())
                .build(), HttpStatus.CONFLICT);
    }

    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IllegalTruckState {
        private int truckId;
        private String message;
    }
}