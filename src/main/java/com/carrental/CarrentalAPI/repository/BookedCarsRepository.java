package com.carrental.CarrentalAPI.repository;

import com.carrental.CarrentalAPI.models.BookedCars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookedCarsRepository extends JpaRepository<BookedCars, Long> {
    @Query(value = "SELECT * FROM booked_cars WHERE client_id = ?1", nativeQuery = true)
    List<BookedCars> findBookedCars(Long client_id);

    @Query(value = "SELECT bookid FROM `booked_cars` WHERE car_id = ?1 AND client_id = ?2", nativeQuery = true)
    Long removeRemoveCar(Long client_id, Long car_id);
}
