package com.carrental.CarrentalAPI.models;

import com.carrental.CarrentalAPI.models.dto.ReservedCarsDto;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "reserved_cars")
public class ReservedCars {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reserveid;
    private String name;
    private Date from_;
    private Date _to;
    private int price;
    @ManyToOne
    private Client client;
    @OneToOne
    private Car car;

    public static ReservedCars from(ReservedCarsDto reservedCarsDto){
        ReservedCars reservedCars = new ReservedCars();
        reservedCars.setName(reservedCarsDto.getName());
        reservedCars.setPrice(reservedCarsDto.getPrice());
        reservedCars.setFrom_(reservedCarsDto.getFrom_());
        reservedCars.set_to(reservedCarsDto.get_to());
        return reservedCars;
    }
}
