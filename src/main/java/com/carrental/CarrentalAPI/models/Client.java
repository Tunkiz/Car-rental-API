package com.carrental.CarrentalAPI.models;

import com.carrental.CarrentalAPI.models.dto.ClientDto;
import lombok.Data;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    @OneToMany(cascade = CascadeType.ALL)
    //@JoinColumn(name = "client_id")
    List<ReservedCars> carList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    List<BookedCars> bookedCars = new ArrayList<>();
    public void reserveCar(ReservedCars car){
        carList.add(car);
    }
    public void removeReservedCar(ReservedCars car){
        carList.remove(car);
    }
    //List<Car> bookedCars = new ArrayList<>();
    public void bookCar(BookedCars car){
        bookedCars.add(car);
    }
    public void removeBookedCar(BookedCars car){
        bookedCars.remove(car);
    }
    public static Client from(ClientDto clientDto){
        Client client = new Client();
        client.setId(clientDto.getId());
        client.setName(clientDto.getName());
        client.setAddress(clientDto.getAddress());
        return client;
    }
}
