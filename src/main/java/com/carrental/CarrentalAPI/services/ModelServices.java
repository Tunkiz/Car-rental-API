package com.carrental.CarrentalAPI.services;

import com.carrental.CarrentalAPI.models.Model;
import com.carrental.CarrentalAPI.models.exception.ModelNotFoundException;
import com.carrental.CarrentalAPI.repository.CarModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public Model addModel(Model model){
        return carModelRepository.save(model);
    }
    public List<Model> getModels(){
        return StreamSupport.stream(carModelRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }
    public Model getModel(Long id){
        Model model = carModelRepository.findById(id).orElseThrow(() -> new ModelNotFoundException(id));
        return model;
    }
    @Transactional
    public Model updateModel(Long id, Model model){
        Model modelToBeUpdated = getModel(id);
        modelToBeUpdated.setName(model.getName());
        return modelToBeUpdated;
    }
    public Model delete(Long id){
        Model model = getModel(id);
        carModelRepository.delete(model);
        return model;
    }
}
