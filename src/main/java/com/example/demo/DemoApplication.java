package com.example.demo;

import com.example.demo.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@SpringBootApplication
@RestController
public class DemoApplication {

	public DemoApplication(UserRepository userRepository, FlightService flightService, FlightsRepository flightsRepository) {
		this.userRepository = userRepository;
		this.flightService = flightService;
		this.flightsRepository = flightsRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private final FlightService flightService;
	@Autowired
	private final FlightsRepository flightsRepository;
	@GetMapping("/login")
	public RedirectView login(@RequestParam(value = "username") String name , @RequestParam(value = "password") String pass) {
		String storedPassword = userRepository.findByUsername(name).getPassword();
		if (storedPassword != null && storedPassword.equals(pass)){
			return new RedirectView("/SelectPorts");
		}
		else{
			return new RedirectView("/");
		}
	}
	



}
