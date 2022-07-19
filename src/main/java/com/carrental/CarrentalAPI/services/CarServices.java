package com.carrental.CarrentalAPI.services;

import com.carrental.CarrentalAPI.models.Car;
import com.carrental.CarrentalAPI.models.exception.CarNotFoundException;
import com.carrental.CarrentalAPI.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CarServices {
    private final CarRepository carRepository;
    @Autowired
    public CarServices(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
    public Car addCar(Car car){
        return carRepository.save(car);
    }
    public List<Car> getCars(){
        return StreamSupport.stream(carRepository.findAll()
                .spliterator(), false)
                .collect(Collectors.toList());
    }
    public Car getCar(Long id){
        return carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id));
    }
    public Car deleteCar(Long id){
        Car car = getCar(id);
        carRepository.delete(car);
        return car;
    }
    @Transactional
    public Car updateCar(Long id, Car car){
        Car carToUpdate = getCar(id);
        if (Objects.nonNull(car.getName())){
            carToUpdate.setName(car.getName());
        }
        if (Objects.nonNull(car.getStatus())){
            carToUpdate.setStatus(car.getStatus());
        }
        if (Objects.nonNull(car.getPrice())){
            carToUpdate.setPrice(car.getPrice());
        }
        return carToUpdate;
    }
    public List<Car> listByStatus(@PathVariable final String status){
        return carRepository.findByStatus(status);
    }
}
