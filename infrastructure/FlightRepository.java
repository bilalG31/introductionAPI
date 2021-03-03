package com.ScholaNova.demo.infrastructure;

import com.ScholaNova.demo.domain.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FlightRepository {

    @Autowired
    JdbcTemplate jdbc;

    public List<Flight> getAllFlightFromDatabase() {
        List<Flight> result = jdbc.query("select * FROM flights", new BeanPropertyRowMapper(Flight.class));
        return result;
    }

    public void addFlightToDatabase(Flight flightToAdd){
        int id = flightToAdd.getId();
        int number = flightToAdd.getFlightNumber();
        String departure = flightToAdd.getDeparture();
        String arrival = flightToAdd.getArrival();
        jdbc.update("INSERT INTO flights (flightNumber, departure, arrival) VALUES(?,?,?)", number, departure, arrival);
    }

    public boolean isFlightNumberInDatabase(int numberToCompare){
        List<Integer>  flightsNumbersFromDatabase = jdbc.query("select flightNumber FROM flights", new BeanPropertyRowMapper(Flight.class));
        for (int nb : flightsNumbersFromDatabase){
            if (nb == numberToCompare)
                return true;
        }
        return false;
    }

    public Flight findFlightByFlightNumber(int flightNumber){
        Flight f;
        f = (Flight)jdbc.queryForObject("SELECT * FROM flights WHERE flightNumber = ?", new BeanPropertyRowMapper(Flight.class), new Object[] {flightNumber});
        return f;

    }

    public void deleteFlightById(int id){
        jdbc.update("DELETE FROM flights WHERE id = ?", id);
    }

    public List<Flight> getAllFlightsFromDeparture(String departureLocation) {
        String sql = String.format("SELECT * FROM flights WHERE departure = '%s'", departureLocation);
        return jdbc.query(sql, new BeanPropertyRowMapper<>(Flight.class));
    }

    public List<Flight> getAllFlightsFromArrival(String arrivalLocation) {
        String sql = String.format("SELECT * FROM flights WHERE arrival = '%s'", arrivalLocation);
        return jdbc.query(sql, new BeanPropertyRowMapper<>(Flight.class));
    }

}
