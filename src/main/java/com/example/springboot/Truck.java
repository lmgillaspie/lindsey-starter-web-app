package com.example.springboot;

import lombok.*;

import javax.persistence.*;

import org.springframework.data.domain.AbstractAggregateRoot;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Truck extends AbstractAggregateRoot<Truck> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private TruckStatus status;
}
