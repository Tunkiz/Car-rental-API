package com.carrental.CarrentalAPI.models.dto;

import com.carrental.CarrentalAPI.models.CarModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ModelDto {
    private Long id;
    private String name;
    private List<CarDto> Cars = new ArrayList<>();

    public static ModelDto from(CarModel carModel){
        ModelDto modelDto = new ModelDto();
        modelDto.setId(carModel.getId());
        modelDto.setName(carModel.getName());
        modelDto.setCars(carModel.getCars().stream().map(CarDto::from).collect(Collectors.toList()));
        return modelDto;
    }
}
