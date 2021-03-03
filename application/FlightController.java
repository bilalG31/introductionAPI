package com.ScholaNova.demo.application;

import com.ScholaNova.demo.domain.Flight;
import com.ScholaNova.demo.infrastructure.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    FlightService fs;

    @GetMapping
    @ResponseBody
    public List<Flight> getAllFlights(){
        return fs.getAllFlights();
    }

    @GetMapping(params = "departure")
    @ResponseBody
    public List<Flight> getFlightsFromDeparture(@RequestParam() String departure) {
        return fs.getAllFlightFromDeparture(departure);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addFlight(@RequestBody Flight f){
        fs.addFlight(f);
    }

    @GetMapping(params = "flightNumber")
    @ResponseBody
    public Flight getFlightByFlightNumber(@RequestParam() int flightNumber) {
        return fs.getFlightByFlightNumber(flightNumber);
    }


}
