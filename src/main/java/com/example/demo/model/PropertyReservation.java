package com.example.demo.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


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
<<<<<<< HEAD
    private Date bookedStart;

    @ApiModelProperty(notes="Apartment availability end date")
    private Date bookedEnd;
=======
    private LocalDateTime startDate;

    @ApiModelProperty(notes="Apartment availability end date")
    private LocalDateTime endDate;
>>>>>>> 7d01be94d9229d365aa719d10ade119f987f3322

    @ManyToOne
    private Guest guest;

    @ManyToOne
    private Property property;

}
