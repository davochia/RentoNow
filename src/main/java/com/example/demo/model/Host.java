package com.example.demo.model;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@ApiModel(description="Owner of the property")
@Data
@Entity
public class Host{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes="Unique auto generated identifier for the system")
    private Integer id;

    @NotNull
    @ApiModelProperty(notes="Host first name ")
    private String firstName;

    @NotNull
    @ApiModelProperty(notes="Host last name ")
    private String lastName;

    @NotNull
    @Column(unique = true)
    @ApiModelProperty(notes="Host phone number ")
    private String phone;

    @NotNull
    @Column(unique = true)
    @ApiModelProperty(notes="Host email ")
    private String email;

    @OneToMany(mappedBy = "host")
    @ApiModelProperty(notes="Host property list")
    private List<Property> properties;

}
