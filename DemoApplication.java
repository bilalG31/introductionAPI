package com.ScholaNova.demo;

import ch.qos.logback.core.CoreConstants;
import com.ScholaNova.demo.Hotel.Hotel;
import com.ScholaNova.demo.Hotel.HotelList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}


	@Autowired
	JdbcTemplate jdbc;

	@Override
	public void run(String... args) throws Exception {
		//initialisation base de donnee de vols
		jdbc.execute("DROP TABLE IF EXISTS flights");
		jdbc.execute("CREATE TABLE flights(id INTEGER PRIMARY KEY AUTO_INCREMENT, flightNumber INTEGER, departure VARCHAR, arrival VARCHAR)");
		jdbc.batchUpdate("INSERT INTO flights (flightNumber, departure, arrival) VALUES (123, 'CDG', 'TLS')");
		jdbc.batchUpdate("INSERT INTO flights (flightNumber, departure, arrival) VALUES (124, 'TLS', 'TUN')");
		jdbc.batchUpdate("INSERT INTO flights (flightNumber, departure, arrival) VALUES (125, 'ORY', 'ALG')");
		jdbc.batchUpdate("INSERT INTO flights (flightNumber, departure, arrival) VALUES (126, 'MRS', 'MRK')");
	}
}
