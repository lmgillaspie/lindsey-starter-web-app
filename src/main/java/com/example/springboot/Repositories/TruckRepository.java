package com.example.springboot.Repositories;

import org.springframework.data.repository.CrudRepository;
import com.example.springboot.Truck;

public interface TruckRepository extends CrudRepository<Truck, Integer> {

}
