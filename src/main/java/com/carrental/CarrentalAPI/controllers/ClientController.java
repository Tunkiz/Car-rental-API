package com.carrental.CarrentalAPI.controllers;

import com.carrental.CarrentalAPI.models.BookedCars;
import com.carrental.CarrentalAPI.models.Client;
import com.carrental.CarrentalAPI.models.ReservedCars;
import com.carrental.CarrentalAPI.models.dto.BookedCarsDto;
import com.carrental.CarrentalAPI.models.dto.ClientDto;
import com.carrental.CarrentalAPI.models.dto.ReservedCarsDto;
import com.carrental.CarrentalAPI.models.exception.CarNotFoundException;
import com.carrental.CarrentalAPI.services.ClientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientServices clientServices;
    @Autowired
    public ClientController(ClientServices clientServices) {
        this.clientServices = clientServices;
    }
    @PostMapping
    public ResponseEntity<ClientDto> addClient(@RequestBody final ClientDto clientDto){
        Client client = clientServices.addClient(Client.from(clientDto));
        return new ResponseEntity<>(ClientDto.fromS(client), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<ClientDto>> getClients(){
        List<Client> clients = clientServices.getClients();
        List<ClientDto> clientDtos = clients.stream().map(ClientDto::fromS).collect(Collectors.toList());
        return new ResponseEntity<>(clientDtos, HttpStatus.OK);
    }
    @GetMapping(value = "{id}")
    public ResponseEntity<ClientDto> getClient(@PathVariable final Long id){
        Client client = clientServices.getClient(id);
        return new ResponseEntity<>(ClientDto.fromS(client), HttpStatus.OK);
    }
    @PutMapping(value = "{id}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable final long id, @RequestBody final ClientDto clientDto){
        Client client = clientServices.updateClient(id, Client.from(clientDto));
        return new ResponseEntity<>(ClientDto.fromS(client), HttpStatus.OK);
    }
    @DeleteMapping(value = "{id}")
    public ResponseEntity<ClientDto> deleteClient(@PathVariable final long id){
        Client client = clientServices.deleteClient(id);
        return new ResponseEntity<>(ClientDto.fromS(client), HttpStatus.OK);
    }
    @PostMapping(value = "{clientId}/cars/{carId}/reserve/{from}/{to}")
    public ResponseEntity<ClientDto> reserve(@PathVariable final Long carId, @PathVariable final Long clientId, @PathVariable final Date from, @PathVariable final Date to){
        Client client = clientServices.reserveCarForClient(carId, clientId, from, to);
        return new ResponseEntity<>(ClientDto.fromS(client), HttpStatus.OK);
    }
    @PostMapping(value = "{clientId}/cars/{carId}/book/{from}/{to}")
    public ResponseEntity<ClientDto> book(@PathVariable final Long carId, @PathVariable final Long clientId, @PathVariable final Date from, @PathVariable final Date to){
        Client client = clientServices.bookCarForClient(carId, clientId, from, to);
        return new ResponseEntity<>(ClientDto.fromS(client), HttpStatus.OK);
    }
    @DeleteMapping(value = "{clientId}/cars/{carId}/reserved")
    public ResponseEntity<ClientDto> removeReserved(@PathVariable final Long carId, @PathVariable final Long clientId){
        Client client = clientServices.removeCarFromClient(carId, clientId);
        return new ResponseEntity<>(ClientDto.fromS(client), HttpStatus.OK);
    }
    @DeleteMapping(value = "{clientId}/cars/{carId}/booked")
    public ResponseEntity<ClientDto> removeBooked(@PathVariable final Long carId, @PathVariable final Long clientId){
        Client client = clientServices.removeBookedCarFromClient(carId, clientId);
        return new ResponseEntity<>(ClientDto.fromS(client), HttpStatus.OK);
    }

    @GetMapping(value = "/reserved/{client_id}")
    public ResponseEntity<List<ReservedCarsDto>> clientReservedCars(@PathVariable final Long client_id){
        List<ReservedCars> reservedCars = clientServices.getReservedCars(client_id);
        List<ReservedCarsDto> reservedCarsDtos = reservedCars.stream().map(ReservedCarsDto::from).collect(Collectors.toList());
        return  new ResponseEntity<>(reservedCarsDtos, HttpStatus.OK);
    }
    @GetMapping(value = "booked/{client_id}")
    public ResponseEntity<List<BookedCarsDto>> clientBookedCars(@PathVariable final Long client_id){
        List<BookedCars> bookedCars = clientServices.getBookedCars(client_id);
        List<BookedCarsDto> bookedCarsDtos = bookedCars.stream().map(BookedCarsDto::from).collect(Collectors.toList());
        return  new ResponseEntity<>(bookedCarsDtos, HttpStatus.OK);
    }
}
