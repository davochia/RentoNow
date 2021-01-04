package com.example.demo.model;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@ApiModel(description="Property information")
<<<<<<< HEAD
@Table(name = "Properties")
=======
@Table(name = "Property")
@Entity(name = "Property")
@Data
>>>>>>> 7d01be94d9229d365aa719d10ade119f987f3322
public class Property {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes="Unique auto generated identifier for the system")
    private int id;

    //@Column(unique = true)
    @NotNull
    @ApiModelProperty(notes="Property name or title")
    private String title;

    @NotNull
    @ApiModelProperty(notes="Property location")
    private String location;

    @NotNull
    @ApiModelProperty(notes="Property description")
    private String description;

    @NotNull
    @ApiModelProperty(notes="Property price")
    private Double price;

<<<<<<< HEAD
    @ApiModelProperty(notes="Property availability start date")
    private Date availableStart;

    @ApiModelProperty(notes="Property availability end date")
    private Date availableEnd;
=======
    @ApiModelProperty(notes="Apartment availability start date")
    private LocalDateTime availableStart;

    @ApiModelProperty(notes="Apartment availability end date")
    private LocalDateTime availableEnd;
>>>>>>> 7d01be94d9229d365aa719d10ade119f987f3322

//    @ApiModelProperty(notes="Images of property")
//    private List<ImageDB> images;


    @ManyToOne
    private Host host;

}
