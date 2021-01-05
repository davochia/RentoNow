package com.example.demo.dto;

import com.example.demo.model.PropertyReservation;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PropertyReservationDto {

    private Integer id;
    private LocalDate startDate;
    private LocalDate endDate;


    public static PropertyReservation getPropertyReservation(PropertyReservationDto propertyReservationDto){

        PropertyReservation propertyReservation = new PropertyReservation();
        propertyReservation.setId(propertyReservationDto.getId());
        propertyReservation.setStartDate(propertyReservationDto.getStartDate());
        propertyReservation.setEndDate(propertyReservationDto.getEndDate());

        return propertyReservation;
    }

    public static PropertyReservationDto getPropertyReservationDto(PropertyReservation propertyReservation){
        PropertyReservationDto propertyReservationDto = new PropertyReservationDto();
        propertyReservationDto.setId(propertyReservation.getId());
        propertyReservationDto.setStartDate(propertyReservation.getStartDate());
        propertyReservationDto.setEndDate(propertyReservation.getEndDate());

        return propertyReservationDto;
    }
}
