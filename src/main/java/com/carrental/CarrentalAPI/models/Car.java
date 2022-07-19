package com.carrental.CarrentalAPI.models;

import com.carrental.CarrentalAPI.models.dto.CarDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.sql.Date;


@Data
@Entity
@Table(name ="Cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private  String Name;
    private String status;
    private int price;
    private Date from_;
    private Date _to;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Model carModel;

    public static Car from(CarDto carDto){
        Car car = new Car();
        car.setName(carDto.getName());
        car.setStatus(carDto.getStatus());
        car.setPrice(carDto.getPrice());
        car.setFrom_(carDto.getFrom_());
        car.set_to(carDto.get_to());
        return car;
    }
}
