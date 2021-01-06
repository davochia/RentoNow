package com.example.demo.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@ApiModel(description="Property reservation information")
@Table(name = "Reservation")
@Entity(name = "Reservation")
@Data
public class PropertyReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes="Unique auto generated identifier for the system")
    private int id;

    @ApiModelProperty(notes="Apartment availability start date")
    private LocalDate startDate;

    @ApiModelProperty(notes="Apartment availability end date")
    private LocalDate endDate;

    @ManyToOne
    private Guest guest;

    @ManyToOne
    private Property property;

}
