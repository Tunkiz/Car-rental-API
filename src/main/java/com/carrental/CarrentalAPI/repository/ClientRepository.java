package com.carrental.CarrentalAPI.repository;

import com.carrental.CarrentalAPI.models.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
}
