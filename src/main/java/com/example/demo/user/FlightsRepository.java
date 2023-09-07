package com.example.demo.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FlightsRepository extends JpaRepository<Flight,Long>{
    @Query("SELECT f FROM Flight f WHERE f.departure = :departure AND f.arrival = :arrival")
    Flight findFlightsByDepartureAndArrival(String departure, String arrival);
    @Query("SELECT f FROM Flight f WHERE f.arrival = :departure AND f.departure = :arrival")
    List<Flight> findFlightsByArrivalAndDeparture(@Param("arrival") String arrival, @Param("departure") String departure);
    @Transactional
    @Modifying
    @Query("SELECT f FROM Flight f WHERE f.PNR= :pnr")
    Flight findFlightByPNR(@Param("pnr") String pnr);
    Flight getFlightByPNR(String pnr);
    @Transactional
    @Modifying
    @Query("UPDATE Flight f SET f.PNR = :newPNR WHERE f.arrival = :arrival AND f.departure = :departure")
    void updatePNRByArrivalAndDeparture(String arrival, String departure, String newPNR);

}
