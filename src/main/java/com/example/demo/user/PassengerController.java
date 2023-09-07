package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping(path = "/passenger")
public class PassengerController {
    @Autowired
    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
            this.passengerService = passengerService;
        }



}
