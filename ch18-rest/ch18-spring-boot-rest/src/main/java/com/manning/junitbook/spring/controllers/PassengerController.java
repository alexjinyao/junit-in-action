package com.manning.junitbook.spring.controllers;

import com.manning.junitbook.spring.exceptions.PassengerNotFoundException;
import com.manning.junitbook.spring.model.Country;
import com.manning.junitbook.spring.model.Passenger;
import com.manning.junitbook.spring.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PassengerController {

    @Autowired
    private PassengerRepository repository;

    @Autowired
    private Map<String, Country> countriesMap;

    @GetMapping("/passengers")
    List<Passenger> findAll() {
        return repository.findAll();
    }

    @PostMapping("/passengers")
    @ResponseStatus(HttpStatus.CREATED)
    Passenger createPassenger(@RequestBody Passenger passenger) {
        return repository.save(passenger);
    }

    @GetMapping("/passengers/{id}")
    Passenger findPassenger(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new PassengerNotFoundException(id));
    }


}
