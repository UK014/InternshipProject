package com.example.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger,Long> {
    Passenger findPassengerByPNR(String PNR);

}
