package com.example.demo.model;


import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;


@ApiModel(description="Person to rent the property")
@Table(name = "Guest")
@Entity(name = "Guest")
@Data
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes="Unique auto generated identifier for the system")
    private Integer id;

    @NotNull
    @ApiModelProperty(notes="Guest first name ")
    private String firstName;

    @NotNull
    @ApiModelProperty(notes="Guest last name ")
    private String lastName;

    @NotNull
    @Column(unique = true)
    @ApiModelProperty(notes="Guest phone number ")
    private String phone;

    @NotNull
    @Column(unique = true)
    @ApiModelProperty(notes="Guest email ")
    private String email;


    @NotNull
    @OneToMany
    @ApiModelProperty(notes="Guest reservations ")
    private List<PropertyReservation> propertyReservation;

}
