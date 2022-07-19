package com.carrental.CarrentalAPI.repository;

import com.carrental.CarrentalAPI.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
    List<Car> findByStatus(String name);
}
