package com.carrental.CarrentalAPI.models.dto;

import com.carrental.CarrentalAPI.models.Category;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CategoryDto {
    private Long id;
    private String name;
    private List<CarDto> Cars = new ArrayList<>();
    public static CategoryDto from(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setCars(category.getCars().stream().map(CarDto::from).collect(Collectors.toList()));
        return categoryDto;
    }
}
