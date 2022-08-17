package com.carrental.CarrentalAPI.repository;

import com.carrental.CarrentalAPI.models.Client;
import com.carrental.CarrentalAPI.models.ReservedCars;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

}
