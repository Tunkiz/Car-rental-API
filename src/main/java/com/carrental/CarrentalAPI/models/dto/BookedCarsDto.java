package com.carrental.CarrentalAPI.models.dto;

import com.carrental.CarrentalAPI.models.BookedCars;
import lombok.Data;

import java.sql.Date;

@Data
public class BookedCarsDto {
    private Long BookId;
    private  String Name;
    private int price;
    private Date from_;
    private Date _to;
    public static BookedCarsDto from(BookedCars bookedCars){
        BookedCarsDto bookedCarsDto = new BookedCarsDto();
        bookedCarsDto.setBookId(bookedCars.getBookid());
        bookedCarsDto.setName(bookedCars.getName());
        bookedCarsDto.setPrice(bookedCars.getPrice());
        bookedCarsDto.setFrom_(bookedCars.getFrom_());
        bookedCarsDto.set_to(bookedCars.get_to());
        return bookedCarsDto;
    }
}
