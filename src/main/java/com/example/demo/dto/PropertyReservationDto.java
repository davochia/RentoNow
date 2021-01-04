package com.example.demo.dto;

import com.example.demo.model.PropertyReservation;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class PropertyReservationDto {
//    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//    String date = simpleDateFormat.format(new Date());

    private int id;
    private Date bookedStart;
    private Date bookedEnd;


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
