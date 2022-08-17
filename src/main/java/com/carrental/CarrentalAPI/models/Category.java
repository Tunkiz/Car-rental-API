package com.carrental.CarrentalAPI.models;

import com.carrental.CarrentalAPI.models.dto.CategoryDto;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    List<Car> cars = new ArrayList<>();
    public void addCarToCategory(Car car){
        cars.add(car);
    }
//    public void removeCarFromCategory(Car car){
//        cars.remove(car);
//    }

    public static Category from(CategoryDto categoryDto){
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        return category;
    }
}
