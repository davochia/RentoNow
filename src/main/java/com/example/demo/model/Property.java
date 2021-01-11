package com.example.demo.model;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@ApiModel(description="Property information")
@Table(name = "Property")
@Entity(name = "Property")
@Data
public class Property {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes="Unique auto generated identifier for the system")
    private Integer id;

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

    @ApiModelProperty(notes="Set true or false if reserved")
    private Integer numOfBookings = 0;

    private String images;

    @ManyToOne
    private Host host;

//    @OneToMany
//    private PropertyReservation propertyReservation;

    public Boolean isAvailable(Timestamp filterStart, Timestamp filterEnd){

        Timestamp propertyStart = Timestamp.valueOf(this.getAvailableStart().atStartOfDay());
        Timestamp propertyEnd   = Timestamp.valueOf(this.getAvailableEnd().atStartOfDay());

        if ( filterStart.after(propertyStart) && filterEnd.before(propertyEnd) ){
            return true;
        }
        return false;
    }

}
