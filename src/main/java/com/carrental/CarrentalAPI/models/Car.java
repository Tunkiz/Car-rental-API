package com.carrental.CarrentalAPI.models;

import com.carrental.CarrentalAPI.models.dto.CarDto;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;


@Data
@Entity
@Table(name ="Cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private  String Name;
    private String status;
    private int pricePH;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Category category;
    @ManyToOne
    private CarModel carModel;
//    @OneToOne
//    private ReservedCars reservedCars;

    public static Car from(CarDto carDto){
        Car car = new Car();
        car.setName(carDto.getName());
        car.setStatus(carDto.getStatus());
        car.setPricePH(carDto.getPricePH());
        return car;
    }
}
