package com.carrental.CarrentalAPI.models.dto;

import com.carrental.CarrentalAPI.models.Car;
import lombok.Data;

import java.sql.Date;

@Data
public class CarDto {
    private Long Id;
    private  String Name;
    private String status;
    private int pricePH;

    public static CarDto from(Car car){
        CarDto carDto = new CarDto();
        carDto.setId(car.getId());
        carDto.setName(car.getName());
        carDto.setStatus(car.getStatus());
        carDto.setPricePH(car.getPricePH());
        return carDto;
    }
}
