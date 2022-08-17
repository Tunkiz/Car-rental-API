package com.carrental.CarrentalAPI.services;

import com.carrental.CarrentalAPI.models.Car;
//import com.carrental.CarrentalAPI.models.ReservedCars;
import com.carrental.CarrentalAPI.models.CarModel;
import com.carrental.CarrentalAPI.models.Category;
import com.carrental.CarrentalAPI.models.exception.CarNotFoundException;
import com.carrental.CarrentalAPI.repository.CarRepository;
//import com.carrental.CarrentalAPI.repository.ReservedCarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CarServices {
    private final CarRepository carRepository;
    private final ModelServices modelServices;
    private final CategoryServices categoryServices;

    @Autowired
    public CarServices(CarRepository carRepository, ModelServices modelServices, CategoryServices categoryServices) {
        this.carRepository = carRepository;
        this.modelServices = modelServices;
        this.categoryServices = categoryServices;
    }
    @Transactional
    public Car addCar(Car newCar, Long modelId, Long categoryId){
        CarModel carModel = modelServices.getModel(modelId);
        Category carCategory = categoryServices.getCategory(categoryId);
        carModel.addCarToModel(newCar);
        carCategory.addCarToCategory(newCar);
        newCar.setCategory(carCategory);
        newCar.setCarModel(carModel);
        return carRepository.save(newCar);
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

        return carToUpdate;
    }
    public List<Car> listByStatus(String status){
        return carRepository.findByStatus(status);
    }

    public List<Car> listByModel(String name){
        return carRepository.listByModel(name);
    }

    public List<Car> listByCategory(String name){
        return carRepository.listByCategory(name);
    }
}
