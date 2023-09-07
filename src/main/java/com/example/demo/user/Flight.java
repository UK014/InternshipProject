package com.example.demo.user;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table
public class Flight {
    @Id
    @SequenceGenerator(
            name = "Flight_sequence",
            sequenceName = "Flight_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Flight_sequence"
    )
    private long id;
    private String departure;
    private String arrival;
    private Integer business;
    private Integer economy;
    private Date depdate;
    private String PNR;
    private String flightno;
    private String flighttime;

    public Flight() {

    }
    public Flight(long id, String departure, String arrival, Integer business, Integer economy, Date date,String PNR,String flightno,String flighttime) {
        this.id = id;
        this.departure = departure;
        this.arrival = arrival;
        this.business = business;
        this.economy = economy;
        this.depdate = date;
        this.PNR= PNR;
        this.flightno=flightno;
        this.flighttime = flighttime;
    }

    public Flight(String departure, String arrival, Integer business, Integer economy, Date date,String PNR,String flightno,String flighttime) {
        this.departure = departure;
        this.arrival = arrival;
        this.business = business;
        this.economy = economy;
        this.depdate = date;
        this.PNR= PNR;
        this.flightno=flightno;
        this.flighttime = flighttime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public Integer getBusiness() {
        return business;
    }

    public void setBusiness(Integer business) {
        this.business = business;
    }

    public Integer getEconomy() {
        return economy;
    }

    public void setEconomy(Integer economy) {
        this.economy = economy;
    }

    public Date getDate() {
        return depdate;
    }

    public void setDate(Date date) {
        this.depdate = date;
    }

    public String getFlightno() {
        return flightno;
    }

    public void setFlightno(String flightno) {
        this.flightno = flightno;
    }

    public String getFlighttime() {
        return flighttime;
    }

    public void setFlighttime(String flighttime) {
        this.flighttime = flighttime;
    }

    public String getPNR() {
        return PNR;
    }

    public void setPNR(String PNR) {
        this.PNR = PNR;
    }
}
