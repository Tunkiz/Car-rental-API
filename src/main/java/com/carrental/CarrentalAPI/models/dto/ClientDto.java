package com.carrental.CarrentalAPI.models.dto;

import com.carrental.CarrentalAPI.models.Client;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ClientDto {
    private Long id;
    private String name;
    private String address;
    private List<CarDto> Cars = new ArrayList<>();
    public static ClientDto fromS(Client client){
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setName(client.getName());
        clientDto.setAddress(client.getAddress());
        clientDto.setCars(client.getCarList().stream().map(CarDto::from).collect(Collectors.toList()));
        //clientDto.setBookedCars(client.getBookedCars().stream().map(CarDto::from).collect(Collectors.toList()));
        return clientDto;
    }
}
