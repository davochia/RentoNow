package com.example.demo.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PropertyReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String startDate;
    private String endDate;

    @ManyToOne
    private Guest guest;

    @ManyToOne
    private Property property;
}
