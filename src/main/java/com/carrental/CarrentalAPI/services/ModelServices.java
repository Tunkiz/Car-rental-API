package com.carrental.CarrentalAPI.services;

import com.carrental.CarrentalAPI.models.Car;
import com.carrental.CarrentalAPI.models.CarModel;
import com.carrental.CarrentalAPI.models.exception.ModelNotFoundException;
import com.carrental.CarrentalAPI.repository.CarModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ModelServices {
    private final CarModelRepository carModelRepository;

    @Autowired
    public ModelServices(CarModelRepository carModelRepository) {
        this.carModelRepository = carModelRepository;
    }
    public CarModel addModel(CarModel carModel){
        return carModelRepository.save(carModel);
    }
    public List<CarModel> getModels(){
        return StreamSupport.stream(carModelRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }
    public CarModel getModel(Long id){
        CarModel carModel = carModelRepository.findById(id).orElseThrow(() -> new ModelNotFoundException(id));
        return carModel;
    }
    @Transactional
    public CarModel updateModel(Long id, CarModel carModel){
        CarModel carModelToBeUpdated = getModel(id);
        carModelToBeUpdated.setName(carModel.getName());
        return carModelToBeUpdated;
    }
    public CarModel delete(Long id){
        CarModel carModel = getModel(id);
        carModelRepository.delete(carModel);
        return carModel;
    }
//    @Transactional
//    public CarModel addCarToModel(Long modelId, Long carId){
//        CarModel carModel = getModel(modelId);
//        Car car = carServices.getCar(carId);
//        carModel.addCarToModel(car);
//        car.setCarModel(carModel);
//        return carModel;
//    }
//    @Transactional
//    public CarModel removeCarFrom(Long modelId, Long carId){
//        CarModel carModel = getModel(modelId);
//        Car car = carServices.getCar(carId);
//        carModel.removeCarFromModel(car);
//        return carModel;
//    }
}
