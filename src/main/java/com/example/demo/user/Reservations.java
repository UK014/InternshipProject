package com.example.demo.user;

import jakarta.persistence.*;

@Entity
@Table
public class Reservations {
    @Id
    @SequenceGenerator(
            name = "Reservations_sequence",
            sequenceName = "Reservations_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Reservations_sequence"
    )
    private Long id;
    private Integer cost;
    private String status;
    private String PNR;
    public Reservations(Long id, Integer cost, String status, String PNR) {
        this.id = id;
        this.cost = cost;
        this.status = status;
        this.PNR = PNR;
    }
    public Reservations(Integer cost, String status,String PNR) {
        this.cost = cost;
        this.status = status;
        this.PNR = PNR;
    }
    public Reservations() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPNR() {
        return PNR;
    }

    public void setPNR(String PNR) {
        this.PNR = PNR;
    }
}
