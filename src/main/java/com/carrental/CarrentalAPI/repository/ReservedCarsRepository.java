package com.carrental.CarrentalAPI.repository;

import com.carrental.CarrentalAPI.models.ReservedCars;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReservedCarsRepository extends CrudRepository<ReservedCars, Long> {
    @Query(value = "SELECT * FROM reserved_cars WHERE client_id = ?1", nativeQuery = true)
    List<ReservedCars> findReservedCars(Long client_id);

    @Query(value = "SELECT reserveid FROM `reserved_cars` WHERE car_id = ?1 AND client_id = ?2", nativeQuery = true)
    Long removeReservedCar(Long client_id, Long car_id);



}
