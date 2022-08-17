package com.carrental.CarrentalAPI.controllers;

import com.carrental.CarrentalAPI.models.Car;
import com.carrental.CarrentalAPI.models.CarModel;
import com.carrental.CarrentalAPI.models.dto.ModelDto;
import com.carrental.CarrentalAPI.services.ModelServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.ResultSet;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/model")
public class ModelController {
    private final ModelServices modelServices;
    @Autowired
    public ModelController(ModelServices modelServices) {
        this.modelServices = modelServices;
    }
    @PostMapping
    public ResponseEntity<ModelDto> addModel(@RequestBody ModelDto modelDto){
        CarModel carModel = modelServices.addModel(CarModel.from(modelDto));
        return new ResponseEntity<>(ModelDto.from(carModel), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<ModelDto>> getModels(){
        List<CarModel> carModels = modelServices.getModels();
        List<ModelDto> modelDtos = carModels.stream().map(ModelDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(modelDtos,HttpStatus.OK);
    }
    @GetMapping(value = "{id}")
    public ResponseEntity<ModelDto> getModel(@PathVariable final Long id){
        CarModel carModel = modelServices.getModel(id);
        return new ResponseEntity<>(ModelDto.from(carModel), HttpStatus.OK);
    }
    @PutMapping(value = "{id}")
    public ResponseEntity<ModelDto> updateModel(@PathVariable final Long id, @RequestBody ModelDto modelDto){
        CarModel carModel = modelServices.updateModel(id, CarModel.from(modelDto));
        return new ResponseEntity<>(ModelDto.from(carModel), HttpStatus.OK);
    }
    @DeleteMapping(value = "{id}")
    public ResponseEntity<ModelDto> deleteModel(@PathVariable final Long id){
        CarModel carModel = modelServices.delete(id);
        return new ResponseEntity<>(ModelDto.from(carModel), HttpStatus.OK);
    }
//    @PostMapping(value = "{categoryId}/cars/{carId}/add")
//    public ResponseEntity<ModelDto> addCarToModel(@PathVariable final Long categoryId, @PathVariable final Long carId){
//        CarModel carModel = modelServices.addCarToModel(categoryId, carId);
//        return new ResponseEntity<>(ModelDto.from(carModel),HttpStatus.OK);
//    }
//    @DeleteMapping(value = "{categoryId}/cars/{carId}/remove")
//    public ResponseEntity<ModelDto> removeCarFromModel(@PathVariable final Long categoryId, @PathVariable final Long carId){
//        CarModel carModel = modelServices.removeCarFrom(categoryId, carId);
//        return new ResponseEntity<>(ModelDto.from(carModel), HttpStatus.OK);
//    }
}
