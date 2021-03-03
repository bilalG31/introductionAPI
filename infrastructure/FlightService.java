package com.ScholaNova.demo.infrastructure;

import com.ScholaNova.demo.domain.Flight;

import java.util.List;

public interface FlightService {
    List<Flight> getAllFlights();
    List<Flight> getAllFlightFromDeparture(String departureLocation);
    void addFlight(Flight f);
    Flight updateFlight(Flight f);
    Flight getFlightByFlightNumber(int i);
}
