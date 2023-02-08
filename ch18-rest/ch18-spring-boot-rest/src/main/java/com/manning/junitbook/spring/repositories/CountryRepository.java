package com.manning.junitbook.spring.repositories;

import com.manning.junitbook.spring.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
