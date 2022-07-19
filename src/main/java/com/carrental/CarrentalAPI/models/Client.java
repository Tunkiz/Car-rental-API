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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")

    List<Car> carList = new ArrayList<>();
    public void reserveCar(Car car){
        carList.add(car);
    }
    public void removeReservedCar(Car car){
        carList.remove(car);
    }
    //List<Car> bookedCars = new ArrayList<>();
    public void bookCar(Car car){
        carList.add(car);
    }
    public void removeBookedCar(Car car){
        carList.remove(car);
    }
    public static Client from(ClientDto clientDto){
        Client client = new Client();
        client.setId(clientDto.getId());
        client.setName(clientDto.getName());
        client.setAddress(clientDto.getAddress());
        return client;
    }
}
