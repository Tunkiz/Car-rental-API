package com.carrental.CarrentalAPI.controllers;

import com.carrental.CarrentalAPI.models.Car;
import com.carrental.CarrentalAPI.models.dto.CarDto;
import com.carrental.CarrentalAPI.services.CarServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarServices carServices;

    @Autowired
    public CarController(CarServices carServices) {
        this.carServices = carServices;
    }
    @PostMapping
    public ResponseEntity<CarDto> addCar(@RequestBody final CarDto carDto){
        Car car = carServices.addCar(Car.from(carDto));
        return new ResponseEntity<>(CarDto.from(car), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<CarDto>> getCars(){
        List<Car> cars = carServices.getCars();
        List<CarDto> carDtos = cars.stream().map(CarDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(carDtos, HttpStatus.OK);
    }
    @GetMapping(value = "{id}")
    public ResponseEntity<CarDto> getCar(@PathVariable final Long id){
        Car car = carServices.getCar(id);
        return new ResponseEntity<>(CarDto.from(car), HttpStatus.OK);
    }
    @PutMapping(value = "{id}")
    public ResponseEntity<CarDto> updateCar(@PathVariable final Long id, @RequestBody final CarDto carDto){
        Car car = carServices.updateCar(id, Car.from(carDto));
        return new ResponseEntity<>(CarDto.from(car), HttpStatus.OK);
    }
    @DeleteMapping(value = "{id}")
    public ResponseEntity<CarDto> deleteCar(@PathVariable final Long id){
        Car car = carServices.deleteCar(id);
        return new ResponseEntity<>(CarDto.from(car), HttpStatus.OK);
    }
    @GetMapping(value = "status/{status}")
    public ResponseEntity<List<CarDto>> listByStatus(@PathVariable final String status){
        List<Car> cars = carServices.listByStatus(status);
        List<CarDto> carDtos = cars.stream().map(CarDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(carDtos, HttpStatus.OK);
    }
    @GetMapping(value = "model/{name}")
    public ResponseEntity<List<CarDto>> listByModel(@PathVariable final  String name){

    }
}
