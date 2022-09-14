package com.example.springboot.repositories;

import org.springframework.data.repository.CrudRepository;
import com.example.springboot.Truck;

public interface TruckRepository extends CrudRepository<Truck, Integer> {

}
