package com.carrental.CarrentalAPI.repository;

import com.carrental.CarrentalAPI.models.CarModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarModelRepository extends CrudRepository<CarModel, Long> {
}
