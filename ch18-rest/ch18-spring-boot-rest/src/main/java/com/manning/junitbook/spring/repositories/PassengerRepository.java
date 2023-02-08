package com.manning.junitbook.spring.repositories;

import com.manning.junitbook.spring.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
