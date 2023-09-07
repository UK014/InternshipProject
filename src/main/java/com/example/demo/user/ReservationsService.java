package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationsService {
    @Autowired
    private final ReservationsRepository reservationsRepository;


    public ReservationsService(ReservationsRepository reservationsRepository) {
        this.reservationsRepository = reservationsRepository;
    }
    public Reservations findRes(String PNR){
        Reservations reservations = reservationsRepository.findReservationsByPNR(PNR);
        return reservations;
    }
}
