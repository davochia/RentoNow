package com.example.demo.model;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@ApiModel(description="Property information")
@Table(name = "Property")
@Entity(name = "Property")
@Data
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

    @ApiModelProperty(notes="Apartment availability start date")
    private LocalDate availableStart;

    @ApiModelProperty(notes="Apartment availability end date")
    private LocalDate availableEnd;

    @ApiModelProperty(notes="Apartment image")
    private String images;

//    @ApiModelProperty(notes="Apartment image")
//    private Image image;

//    @ApiModelProperty(notes="Images of property")
//    private List<ImageDB> images;

    @ManyToOne
    private Host host;

}
