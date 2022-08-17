package com.carrental.CarrentalAPI.models;

import com.carrental.CarrentalAPI.models.dto.BookedCarsDto;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;


@Data
@Entity
@Table(name = "booked_cars")
public class BookedCars {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookid;
    private String name;
    private Date from_;
    private Date _to;
    private int price;
    @ManyToOne
    private Client client;
    @OneToOne
    private Car car;

    public static BookedCars from(BookedCarsDto bookedCarsDto){
        BookedCars bookedcars = new BookedCars();
        bookedcars.setName(bookedCarsDto.getName());
        bookedcars.setPrice(bookedCarsDto.getPrice());
        bookedcars.setFrom_(bookedCarsDto.getFrom_());
        bookedcars.set_to(bookedCarsDto.get_to());
        return bookedcars;
    }
}
