package com.carrental.CarrentalAPI.models.dto;

import com.carrental.CarrentalAPI.models.Car;
import com.carrental.CarrentalAPI.models.Category;
import lombok.Data;

import java.sql.Date;
import java.util.Objects;

@Data
public class CarDto {
    private Long Id;
    private  String Name;
    private String status;
    private int price;
    private Date from_;
    private Date _to;

    public static CarDto from(Car car){
        CarDto carDto = new CarDto();
        carDto.setId(car.getId());
        carDto.setName(car.getName());
        carDto.setStatus(car.getStatus());
        carDto.setPrice(car.getPrice());
        carDto.setFrom_(car.getFrom_());
        carDto.set_to(car.get_to());
        return carDto;
    }
}
