package com.example.demo.dto;


import com.example.demo.model.Host;
import com.example.demo.model.Property;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class PropertyDto {

    private int id;
    private String title;
    private String location;
    private String description;
    private Double price;
    private LocalDate availableStart;
    private LocalDate availableEnd;

    //private Host host;


    public static Property getProperty(PropertyDto propertyDto){
        Property property = new Property();
        property.setId(propertyDto.getId());
        property.setTitle(propertyDto.getTitle());
        property.setLocation(propertyDto.getLocation());
        property.setDescription(propertyDto.getDescription());
        property.setPrice(propertyDto.getPrice());
        property.setAvailableStart(propertyDto.getAvailableStart());
        property.setAvailableEnd(propertyDto.getAvailableEnd());

        return property;
    }

    public static PropertyDto getPropertyDto(Property property){
        PropertyDto propertyDto = new PropertyDto();
        propertyDto.setId(property.getId());
        propertyDto.setTitle(property.getTitle());
        propertyDto.setLocation(property.getLocation());
        propertyDto.setDescription(property.getDescription());
        propertyDto.setPrice(property.getPrice());
        propertyDto.setAvailableStart(property.getAvailableStart());
        propertyDto.setAvailableEnd(property.getAvailableEnd());
//
//        if(property.getHost() != null){
//            propertyDto.setHost(property.getHost());
//        }

        return propertyDto;
    }

}
