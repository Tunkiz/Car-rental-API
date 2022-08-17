package com.carrental.CarrentalAPI.services;

import com.carrental.CarrentalAPI.models.BookedCars;
import com.carrental.CarrentalAPI.models.Car;
import com.carrental.CarrentalAPI.models.Client;
import com.carrental.CarrentalAPI.models.ReservedCars;
import com.carrental.CarrentalAPI.models.exception.ClientNotFoundException;
import com.carrental.CarrentalAPI.repository.BookedCarsRepository;
import com.carrental.CarrentalAPI.repository.ClientRepository;
import com.carrental.CarrentalAPI.repository.ReservedCarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ClientServices {
    private final CarServices carServices;
    private final ClientRepository clientRepository;
    private final ReservedCarsRepository reservedCarsRepository;
    private final BookedCarsRepository bookedCarsRepository;
    @Autowired
    public ClientServices(CarServices carServices, ClientRepository clientRepository, ReservedCarsRepository reservedCarsRepository, BookedCarsRepository bookedCarsRepository) {
        this.carServices = carServices;
        this.clientRepository = clientRepository;
        this.reservedCarsRepository = reservedCarsRepository;
        this.bookedCarsRepository = bookedCarsRepository;
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
    public int price(Car car, Date from, Date to){
        int yearsH = (to.getYear() - from.getYear()) * 8760;
        int monthsH = (to.getMonth() - from.getMonth()) * 730;
        int daysH = (to.getDay() - from.getDay() ) * 24;
        int totalH = yearsH + monthsH + daysH;
        int totalAmount = totalH * car.getPricePH();
        return totalAmount;
    }
    @Transactional
    public Client reserveCarForClient(Long carId, Long clientId, Date from, Date to){
        Client client = getClient(clientId);
        Car car = carServices.getCar(carId);
        if (car.getStatus().equals("Available")) {
            ReservedCars reservedcar = new ReservedCars();
            reservedcar.setName(car.getName());
            reservedcar.setPrice(price(car, from, to));
            reservedcar.setFrom_(from);
            reservedcar.set_to(to);
            reservedCarsRepository.save(reservedcar);
            reservedcar.setClient(client);
            client.reserveCar(reservedcar);
            //car.setReservedCars(reservedcar);
            reservedcar.setCar(car);
            car.setClient(client);
            car.setStatus("Reserved");
        }
        return client;
    }
    @Transactional
    public Client bookCarForClient(Long carId, Long clientId, Date from, Date to){
        Client client = getClient(clientId);
        Car car = carServices.getCar(carId);
        if (car.getStatus().equals("Available")) {
            BookedCars bookedCars = new BookedCars();
            bookedCars.setName(car.getName());
            bookedCars.setPrice(price(car, from, to));
            bookedCars.setFrom_(from);
            bookedCars.set_to(to);
            bookedCarsRepository.save(bookedCars);
            bookedCars.setClient(client);
            client.bookCar(bookedCars);
            //car.setReservedCars(reservedcar);
            bookedCars.setCar(car);
            car.setClient(client);
            car.setStatus("Booked");
        }
        return client;
    }

    @Transactional
    public Client removeCarFromClient(Long carId, Long clientId){
        Client client = getClient(clientId);
        Car car = carServices.getCar(carId);
        Long id = reservedCarsRepository.removeReservedCar(clientId, carId);
        reservedCarsRepository.delete(reservedCarsRepository.findById(id).orElseThrow());
        car.setClient(null);
        car.setStatus("Available");
        return client;
    }
    @Transactional
    public Client removeBookedCarFromClient(Long carId, Long clientId){
        Client client = getClient(clientId);
        Car car = carServices.getCar(carId);
        Long id = bookedCarsRepository.removeRemoveCar(clientId, carId);
        bookedCarsRepository.delete(bookedCarsRepository.findById(id).orElseThrow());
        car.setClient(null);
        car.setStatus("Available");
        return client;
    }

    @Transactional
    public List<ReservedCars> getReservedCars(Long client_id){
        Client client = getClient(client_id);
       return reservedCarsRepository.findReservedCars(client_id);
    }
    @Transactional
    public List<BookedCars> getBookedCars(Long client_id){
        Client client = getClient(client_id);
       return bookedCarsRepository.findBookedCars(client_id);
    }
}
