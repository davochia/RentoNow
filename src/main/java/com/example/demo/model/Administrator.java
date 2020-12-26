package com.example.demo.model;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@ApiModel(description="System administrator")
public class Administrator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes="Unique auto generated identifier for the system")
    private Integer id;

    @NotNull
    @ApiModelProperty(notes="Admin first name ")
    private String firstName;

    @NotNull
    @ApiModelProperty(notes="Admin last name ")
    private String lastName;

    @NotNull
    @Column(unique = true)
    @ApiModelProperty(notes="Admin phone number ")
    private String phone;

    @NotNull
    @Column(unique = true)
    @ApiModelProperty(notes="Admin email ")
    private String email;
}
