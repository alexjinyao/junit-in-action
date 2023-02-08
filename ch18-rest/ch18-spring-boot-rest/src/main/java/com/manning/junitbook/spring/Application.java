package com.manning.junitbook.spring;

import com.manning.junitbook.spring.beans.FlightBuilder;
import com.manning.junitbook.spring.model.Country;
import com.manning.junitbook.spring.model.Flight;
import com.manning.junitbook.spring.model.Passenger;
import com.manning.junitbook.spring.repositories.CountryRepository;
import com.manning.junitbook.spring.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.util.Map;

@SpringBootApplication
@Import(FlightBuilder.class)
public class Application {

    @Autowired
    private Flight flight;

    @Autowired
    private Map<String, Country> countriesMap;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner configureRepository(CountryRepository countryRepository, PassengerRepository passengerRepository) {
        return args -> {

            for (Country country : countriesMap.values()) {
                countryRepository.save(country);
            }

            for (Passenger passenger : flight.getPassengers()) {
                passengerRepository.save(passenger);
            }
        };
    }
}