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
    private Date startDate;
    private Date endDate;


    public static PropertyReservation getPropertyReservation(PropertyReservationDto propertyReservationDto){
        String pattern = "MM-dd-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());

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
