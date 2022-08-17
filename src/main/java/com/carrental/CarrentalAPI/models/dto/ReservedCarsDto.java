package com.carrental.CarrentalAPI.models.dto;

import com.carrental.CarrentalAPI.models.ReservedCars;
import lombok.Data;

import java.sql.Date;
@Data
public class ReservedCarsDto {
    private Long reserveid;
    private  String name;
    private int price;
    private Date from_;
    private Date _to;
    public static ReservedCarsDto from(ReservedCars reservedCars){
        ReservedCarsDto reservedCarsDto = new ReservedCarsDto();
        reservedCarsDto.setReserveid(reservedCars.getReserveid());
        reservedCarsDto.setName(reservedCars.getName());
        reservedCarsDto.setPrice(reservedCars.getPrice());
        reservedCarsDto.setFrom_(reservedCars.getFrom_());
        reservedCarsDto.set_to(reservedCars.get_to());
        return reservedCarsDto;
    }
}
