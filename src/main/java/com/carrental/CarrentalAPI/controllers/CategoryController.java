package com.carrental.CarrentalAPI.controllers;

import com.carrental.CarrentalAPI.models.Category;
import com.carrental.CarrentalAPI.models.dto.CategoryDto;
import com.carrental.CarrentalAPI.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/category")
public class CategoryController {
    private final CategoryServices categoryServices;
    @Autowired
    public CategoryController(CategoryServices categoryServices) {
        this.categoryServices = categoryServices;
    }
    @PostMapping
    public ResponseEntity<CategoryDto> addCategory(@RequestBody final CategoryDto categoryDto){
        Category category = categoryServices.addCategory(Category.from(categoryDto));
        return new ResponseEntity<>(CategoryDto.from(category), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategories(){
        List<Category> categories = categoryServices.getCategories();
        List<CategoryDto> categoryDtos = categories.stream().map(CategoryDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(categoryDtos, HttpStatus.OK);
    }
    @GetMapping(value = "{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable final long id){
        Category category = categoryServices.getCategory(id);
        return new ResponseEntity<>(CategoryDto.from(category), HttpStatus.OK);
    }
    @PutMapping(value = "{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable final Long id, @RequestBody CategoryDto categoryDto){
        Category category = categoryServices.update(id, Category.from(categoryDto));
        return new ResponseEntity<>(CategoryDto.from(category), HttpStatus.OK);
    }
    @DeleteMapping(value = "{id}")
    public ResponseEntity<CategoryDto> delete(@PathVariable final long id){
        Category category = categoryServices.delete(id);
        return new ResponseEntity<>(CategoryDto.from(category), HttpStatus.OK);
    }
}
