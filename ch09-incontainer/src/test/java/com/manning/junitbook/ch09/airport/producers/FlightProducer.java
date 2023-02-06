package com.manning.junitbook.ch09.airport.producers;

import com.manning.junitbook.ch09.airport.Flight;
import com.manning.junitbook.ch09.airport.FlightBuilderUtil;

import javax.enterprise.inject.Produces;
import java.io.IOException;

public class FlightProducer {

    @Produces
    public Flight createFlight() throws IOException {
        return FlightBuilderUtil.buildFlightFromCsv();
    }
}
