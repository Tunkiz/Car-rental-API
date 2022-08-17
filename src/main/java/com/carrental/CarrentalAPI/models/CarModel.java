package com.carrental.CarrentalAPI.models;

import com.carrental.CarrentalAPI.models.dto.ModelDto;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "carModel")
public class CarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    //@JoinColumn(name = "model_id")
    List<Car> cars = new ArrayList<>();
    public void addCarToModel(Car car){
        cars.add(car);
    }
    public void removeCarFromModel(Car car){
        cars.remove(car);
    }

    public static CarModel from(ModelDto modelDto){
        CarModel carModel = new CarModel();
        carModel.setId(carModel.getId());
        carModel.setName(modelDto.getName());
        return carModel;
    }

}
