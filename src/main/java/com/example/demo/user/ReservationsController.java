package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/reservations")
public class ReservationsController {
    @Autowired
    private final ReservationsService reservationsService;
    public ReservationsController(ReservationsService reservationsService) {
        this.reservationsService = reservationsService;
    }
}
