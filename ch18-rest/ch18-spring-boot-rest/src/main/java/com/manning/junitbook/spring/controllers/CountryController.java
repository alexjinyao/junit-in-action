package com.manning.junitbook.spring.controllers;

import com.manning.junitbook.spring.model.Country;
import com.manning.junitbook.spring.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController {

    @Autowired
    private CountryRepository repository;

    @GetMapping("/countries")
    List<Country> findAll() {
        return repository.findAll();
    }
}
