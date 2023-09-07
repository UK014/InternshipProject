package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class FlightService {
    private final FlightsRepository flightsRepository;
    private final pnrRepository pnrRepository;
    @Autowired
    public FlightService(FlightsRepository flightsRepository, com.example.demo.user.pnrRepository pnrRepository) {
        this.flightsRepository = flightsRepository;
        this.pnrRepository = pnrRepository;
    }
    public List<Flight> getFlights(){
        return flightsRepository.findAll();
    }
    public Flight findFlight(String departure, String arrival) {
        Flight flight = flightsRepository.findFlightsByDepartureAndArrival(departure,arrival);
        return flight;
    }
    public Flight findRes(String PNR){
        Flight flight = flightsRepository.getFlightByPNR(PNR);
        return flight;
    }



}
