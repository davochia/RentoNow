package com.example.demo.dto;

import com.example.demo.model.PropertyReservation;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PropertyReservationDto {

    private int id;
<<<<<<< HEAD
    private Date bookedStart;
    private Date bookedEnd;
=======
    private LocalDateTime startDate;
    private LocalDateTime endDate;
>>>>>>> 7d01be94d9229d365aa719d10ade119f987f3322


    public static PropertyReservation getPropertyReservation(PropertyReservationDto propertyReservationDto){

        PropertyReservation propertyReservation = new PropertyReservation();
        propertyReservation.setId(propertyReservationDto.getId());
        propertyReservation.setBookedStart(propertyReservationDto.getBookedStart());
        propertyReservation.setBookedEnd(propertyReservationDto.getBookedEnd());

        return propertyReservation;
    }

    public static PropertyReservationDto getPropertyReservationDto(PropertyReservation propertyReservation){
        PropertyReservationDto propertyReservationDto = new PropertyReservationDto();
        propertyReservationDto.setId(propertyReservation.getId());
        propertyReservationDto.setBookedStart(propertyReservation.getBookedStart());
        propertyReservationDto.setBookedEnd(propertyReservation.getBookedEnd());

        return propertyReservationDto;
    }
}
