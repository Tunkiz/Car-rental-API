package com.carrental.CarrentalAPI.repository;

import com.carrental.CarrentalAPI.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
    List<Car> findByStatus(String name);
    @Query(value = "SELECT id, name, status, get_priceph, from_, _to FROM cars WHERE car_model_id =(SELECT id FROM car_model WHERE name = ?1)", nativeQuery = true)
    List<Car> listByModel(String name);
    @Query(value = "SELECT id, name, status, get_priceph, from_, _to FROM cars WHERE category_id =(SELECT id FROM categories WHERE name = ?1)", nativeQuery = true)
    List<Car> listByCategory(String name);


}
