package com.carrental.CarrentalAPI.services;

import com.carrental.CarrentalAPI.models.Car;
import com.carrental.CarrentalAPI.models.Category;
import com.carrental.CarrentalAPI.models.exception.CategoryNotFoundException;
import com.carrental.CarrentalAPI.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CategoryServices {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServices(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category addCategory(Category category){
       return categoryRepository.save(category);
    }
    public List<Category> getCategories(){
        return StreamSupport.stream(categoryRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }
    public Category getCategory(Long id){
       return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
    }
    @Transactional
    public Category update(Long id, Category category){
        Category catToBeUpdated = getCategory(id);
        catToBeUpdated.setName(category.getName());
        return catToBeUpdated;
    }
    public Category delete(Long id){
        Category category = getCategory(id);
        categoryRepository.delete(category);
        return category;
    }
//    @Transactional
//    public Category addCarToCategory(Long categoryId, Long carId){
//        Category category = getCategory(categoryId);
//        Car car = carServices.getCar(carId);
//        category.addCarToCategory(car);
//        car.setCategory(category);
//        return category;
//    }
//    @Transactional
//    public Category removeCarFromCategory(Long categoryId, Long carId){
//        Category category = getCategory(categoryId);
//        Car car = carServices.getCar(carId);
//        category.removeCarFromCategory(car);
//        return category;
//    }
}
