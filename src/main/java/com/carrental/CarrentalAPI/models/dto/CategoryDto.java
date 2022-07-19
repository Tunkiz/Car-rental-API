package com.carrental.CarrentalAPI.models.dto;

import com.carrental.CarrentalAPI.models.Category;
import lombok.Data;

@Data
public class CategoryDto {
    private Long id;
    private String name;
    public static CategoryDto from(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }
}
