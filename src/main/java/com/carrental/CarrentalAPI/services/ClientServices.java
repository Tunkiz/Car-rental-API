package com.carrental.CarrentalAPI.services;

import com.carrental.CarrentalAPI.models.Car;
import com.carrental.CarrentalAPI.models.Client;
import com.carrental.CarrentalAPI.models.exception.ClientNotFoundException;
import com.carrental.CarrentalAPI.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ClientServices {
    private final CarServices carServices;
    private final ClientRepository clientRepository;
    @Autowired
    public ClientServices(CarServices carServices, ClientRepository clientRepository) {
        this.carServices = carServices;
        this.clientRepository = clientRepository;
    }
    public Client addClient(Client client){
        return clientRepository.save(client);
    }
    public List<Client> getClients(){
        return StreamSupport.stream(clientRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }
    public Client getClient(long id){
        return clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
    }
    @Transactional
    public Client updateClient(long id, Client client){
        Client clientToBeUpdated = getClient(id);
        if (Objects.nonNull(client.getAddress())){
            clientToBeUpdated.setAddress(client.getAddress());
        }
        if (Objects.nonNull(client.getName())){
            clientToBeUpdated.setName(client.getName());
        }
        return clientToBeUpdated;
    }
    public Client deleteClient(Long id){
        Client client = getClient(id);
        clientRepository.delete(client);
        return client;
    }

    @Transactional
    public Client reserveCarForClient(Long carId, Long clientId, Date from, Date to){
        Client client = getClient(clientId);
        Car car = carServices.getCar(carId);
        client.reserveCar(car);
        car.setClient(client);
        car.setStatus("Reserved");
        car.setFrom_(from);
        car.set_to(to);
        return client;
    }
    @Transactional
    public Client bookCarForClient(Long carId, Long clientId, Date from, Date to){
        Client client = getClient(clientId);
        Car car = carServices.getCar(carId);
        client.bookCar(car);
        car.setClient(client);
        car.setStatus("Booked");
        car.setFrom_(from);
        car.set_to(to);
        return client;
    }
    @Transactional
    public Client removeCarFromClient(Long carId, Long clientId){
        Client client = getClient(clientId);
        Car car = carServices.getCar(carId);
        client.removeReservedCar(car);
        car.setStatus("Available");
        return client;
    }
    @Transactional
    public Client removeBookedCarFromClient(Long carId, Long clientId){
        Client client = getClient(clientId);
        Car car = carServices.getCar(carId);
        client.removeBookedCar(car);
        car.setStatus("Available");
        return client;
    }
}
