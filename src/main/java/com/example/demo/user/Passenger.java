package com.example.demo.user;

import jakarta.persistence.*;

@Entity
@Table
public class Passenger {
    @Id
    @SequenceGenerator(
            name = "Passenger_sequence",
            sequenceName = "Passenger_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Passenger_sequence"
    )
    private Long id;
    private String name;
    private String surname;
    private String gender;
    private String SSN;
    private String passportno;
    private String phone;
    private String email;
    private String PNR;
    private String birthdate;
    public Passenger(Long id, String name, String surname, String gender, String SSN, String passportno, String phone, String email, String PNR, String birthdate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.SSN = SSN;
        this.passportno = passportno;
        this.phone = phone;
        this.email = email;
        this.PNR = PNR;
        this.birthdate = birthdate;
    }

    public Passenger(String name, String surname, String gender, String SSN, String passportno, String phone, String email, String PNR, String birthdate) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.SSN = SSN;
        this.passportno = passportno;
        this.phone = phone;
        this.email = email;
        this.PNR = PNR;
        this.birthdate = birthdate;
    }

    public Passenger() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public String getPassportno() {
        return passportno;
    }

    public void setPassportno(String passportno) {
        this.passportno = passportno;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPNR() {
        return PNR;
    }

    public void setPNR(String PNR) {
        this.PNR = PNR;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = String.valueOf(birthdate);
    }
}
