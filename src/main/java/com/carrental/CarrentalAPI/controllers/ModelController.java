package com.carrental.CarrentalAPI.controllers;

import com.carrental.CarrentalAPI.models.Model;
import com.carrental.CarrentalAPI.models.dto.ModelDto;
import com.carrental.CarrentalAPI.services.ModelServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/model")
public class ModelController {
    private final ModelServices modelServices;
    @Autowired
    public ModelController(ModelServices modelServices) {
        this.modelServices = modelServices;
    }
    @PostMapping
    public ResponseEntity<ModelDto> addModel(@RequestBody ModelDto modelDto){
        Model model = modelServices.addModel(Model.from(modelDto));
        return new ResponseEntity<>(ModelDto.from(model), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<ModelDto>> getModels(){
        List<Model> models = modelServices.getModels();
        List<ModelDto> modelDtos = models.stream().map(ModelDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(modelDtos,HttpStatus.OK);
    }
    @GetMapping(value = "{id}")
    public ResponseEntity<ModelDto> getModel(@PathVariable final Long id){
        Model model = modelServices.getModel(id);
        return new ResponseEntity<>(ModelDto.from(model), HttpStatus.OK);
    }
    @PutMapping(value = "{id}")
    public ResponseEntity<ModelDto> updateModel(@PathVariable final Long id, @RequestBody ModelDto modelDto){
        Model model = modelServices.updateModel(id, Model.from(modelDto));
        return new ResponseEntity<>(ModelDto.from(model), HttpStatus.OK);
    }
    @DeleteMapping(value = "{id}")
    public ResponseEntity<ModelDto> deleteModel(@PathVariable final Long id){
        Model model = modelServices.delete(id);
        return new ResponseEntity<>(ModelDto.from(model), HttpStatus.OK);
    }

}
