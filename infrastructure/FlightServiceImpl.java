package com.ScholaNova.demo.infrastructure;

import com.ScholaNova.demo.domain.Flight;
import com.ScholaNova.demo.domain.FlightNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FlightServiceImpl implements FlightService {
    @Autowired
    FlightRepository flightRepository;

    public List<Flight> getAllFlights(){
        return flightRepository.getAllFlightFromDatabase();
    }

    //return all fligths that are departing from the corresponding location
    public List<Flight> getAllFlightFromDeparture(String departureLocation){
        try {
            return flightRepository.getAllFlightsFromDeparture(departureLocation);
        }
        catch(DataAccessException DaE){
            System.out.println("notFound");
            return null;
        }
    }

    public List<Flight> getAllFlightFromArrival(String arrivalLocation){
        return flightRepository.getAllFlightsFromDeparture(arrivalLocation);
    }

    public void addFlight(Flight flightToAdd){
        flightRepository.addFlightToDatabase(flightToAdd);
    }

    @Override
    public Flight updateFlight(Flight f) {
        return null;
    }

    public Flight getFlightByFlightNumber(int flightNumber){
        try{
            return flightRepository.findFlightByFlightNumber(flightNumber);
        }
        catch(DataAccessException DaE){
            throw new FlightNotFoundException();
        }
    }
}
