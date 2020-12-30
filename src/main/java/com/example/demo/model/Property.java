package com.example.demo.model;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@ApiModel(description="Property information")
@Table(name = "Apartments")
public class Property {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes="Unique auto generated identifier for the system")
    private int id;

    //@Column(unique = true)
    @NotNull
    @ApiModelProperty(notes="Apartment name or title")
    private String title;

    @NotNull
    @ApiModelProperty(notes="Apartment location")
    private String location;

    @NotNull
    @ApiModelProperty(notes="Apartment description")
    private String description;

    @NotNull
    @ApiModelProperty(notes="Apartment price")
    private Double price;

    @ApiModelProperty(notes="Apartment availability start date")
    private Date availableStart;

    @ApiModelProperty(notes="Apartment availability end date")
    private Date availableEnd;

//    @ApiModelProperty(notes="Images of property")
//    private List<ImageDB> images;


    @ManyToOne
    private Host host;

}
