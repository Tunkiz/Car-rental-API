package com.carrental.CarrentalAPI.models.dto;

import com.carrental.CarrentalAPI.models.Model;
import lombok.Data;

@Data
public class ModelDto {
    private Long id;
    private String name;

    public static ModelDto from(Model model){
        ModelDto modelDto = new ModelDto();
        modelDto.setId(model.getId());
        modelDto.setName(model.getName());
        return modelDto;
    }
}
