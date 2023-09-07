package com.example.demo.user;

import jakarta.persistence.*;

@Entity
@Table
public class pnr {
    @Id
    @SequenceGenerator(
            name = "pnr_sequence",
            sequenceName = "pnr_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pnr_sequence"
    )
    private Long id;
    private String pnr;
    private String departure;
    private String arrival;
    private String name;
    private String surname;
    private String gender;
    private String birthdate;
    private String depdate;
    private String retdate;

    public pnr() {
    }

    public pnr(Long id, String pnr, String departure, String arrival, String name, String surname, String gender, String birthdate, String depdate, String retdate) {
        this.id = id;
        this.pnr = pnr;
        this.departure = departure;
        this.arrival = arrival;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.birthdate = birthdate;
        this.depdate = depdate;
        this.retdate = retdate;
    }

    public pnr(String pnr, String departure, String arrival, String name, String surname, String gender, String birthdate, String depdate, String retdate) {
        this.pnr = pnr;
        this.departure = departure;
        this.arrival = arrival;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.birthdate = birthdate;
        this.depdate = depdate;
        this.retdate = retdate;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getDepdate() {
        return depdate;
    }

    public void setDepdate(String depdate) {
        this.depdate = depdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRetdate() {
        return retdate;
    }

    public void setRetdate(String retdate) {
        this.retdate = retdate;
    }
}
