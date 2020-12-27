package com.example.demo.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@ApiModel(description="Property reservation information")
public class PropertyReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes="Unique auto generated identifier for the system")
    private int id;

    @ApiModelProperty(notes="Apartment availability start date")
    private Date startDate;

    @ApiModelProperty(notes="Apartment availability end date")
    private Date endDate;

    @ManyToOne
    private Guest guest;

    @ManyToOne
    private Property property;
}