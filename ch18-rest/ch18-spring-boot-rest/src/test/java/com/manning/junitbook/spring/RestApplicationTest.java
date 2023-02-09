package com.manning.junitbook.spring;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manning.junitbook.spring.beans.FlightBuilder;
import com.manning.junitbook.spring.exceptions.PassengerNotFoundException;
import com.manning.junitbook.spring.model.Country;
import com.manning.junitbook.spring.model.Flight;
import com.manning.junitbook.spring.model.Passenger;
import com.manning.junitbook.spring.repositories.CountryRepository;
import com.manning.junitbook.spring.repositories.PassengerRepository;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.NestedServletException;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


/**
 * @SpringBootTest searches the current package of the test class and its subpackages for bean definitions.
 * @AutoConfigureMockMvc enable all autoconfiguration related to the MockMvc objects used in the test.
 * Imports FlightBuilder, which creates a flight bean and a countries map bean.
 */
@SpringBootTest
@AutoConfigureMockMvc
@Import(FlightBuilder.class)
public class RestApplicationTest {

    /**
     * MockMvc is the main entry point for server side Spring REST application testing.
     */
    @Autowired
    private MockMvc mvc;

    @Autowired
    private Flight flight;

    @Autowired
    private Map<String, Country> countriesMap;

    /**
     * @MockBean is used to add mock objects to the Spring application context;
     * the mock will replace an existing bean of the same type in the application context.
     * We will provide instructions for the behavior of the mock objects during the tests.
     */
    @MockBean
    private PassengerRepository passengerRepository;

    @MockBean
    private CountryRepository countryRepository;

    @Test
    void testGetAllCountries() throws Exception {
        /**
         * Instructs the mock countryRepository bean to return the array of values from countriesMap when the findAll method is executed on it.
         */
        when(countryRepository.findAll()).thenReturn(new ArrayList<>(countriesMap.values()));

        /**
         * Simulates the execution of the GET method on the /countries URL and verifies the
         * returned status,
         * expected content type,
         * and returned JSON size.
         */
        mvc.perform(MockMvcRequestBuilders.get("/countries"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)));

        /**
         * Also verifies that the method findAll has been executed exactly once on the countryRepository bean.
         */
        verify(countryRepository, times(1)).findAll();
    }

    @Test
    void testGetAllPassengers() throws Exception {
        when(passengerRepository.findAll()).thenReturn(new ArrayList<>(flight.getPassengers()));

        mvc.perform(MockMvcRequestBuilders.get("/passengers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(20)));

        verify(passengerRepository, times(1)).findAll();
    }

    @Test
    void testPassengerNotFound() {

        /**
         * Checks that a NestedServletException is thrown and that the returned status is Not Found.
         */
        Throwable throwable = assertThrows(NestedServletException.class, () -> {
            mvc.perform(MockMvcRequestBuilders.get("/passengers/30"))
                    .andExpect(MockMvcResultMatchers.status().isNotFound());
        });

        /**
         * Also checks that the cause of the NestedServletException is PassengerNotFoundException
         */
        assertEquals(PassengerNotFoundException.class, throwable.getCause().getClass());
    }

    @Test
    void testPostPassenger() throws Exception {

        Passenger passenger = new Passenger("Peter Michelsen");
        passenger.setCountry(countriesMap.get("US"));
        passenger.setIsRegistered(false);
        when(passengerRepository.save(passenger)).thenReturn(passenger);

        /**
         * Uses an object of type jackson ObjectMapper to write basic POJOs to json string
         */
        mvc.perform(MockMvcRequestBuilders.post("/passengers")
                .content(new ObjectMapper().writeValueAsString(passenger))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Peter Michelsen")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.country.codeName", Matchers.is("US")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.country.name", Matchers.is("USA")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.registered", Matchers.is(Boolean.FALSE)));

        verify(passengerRepository, times(1)).save(passenger);
    }

    @Test
    void testPatchPassenger() throws Exception {
        Passenger passenger = new Passenger("Sophia Graham");
        passenger.setCountry(countriesMap.get("UK"));
        passenger.setIsRegistered(false);

        when(passengerRepository.findById(1L)).thenReturn(Optional.of(passenger));
        when(passengerRepository.save(passenger)).thenReturn(passenger);

        String updates = "{\"name\":\"Sophia Jones\", \"country\":\"AU\", \"isRegistered\":\"true\"}";

        mvc.perform(MockMvcRequestBuilders.patch("/passengers/1")
                .content(updates)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(passengerRepository, times(1)).findById(1L);
        verify(passengerRepository, times(1)).save(passenger);
    }

    @Test
    void testDeletePassenger() throws Exception {

        mvc.perform(MockMvcRequestBuilders.delete("/passengers/4"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(passengerRepository, times(1)).deleteById(4L);
    }
}
