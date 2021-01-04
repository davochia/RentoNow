package com.example.demo.dto;


import com.example.demo.model.Property;
import lombok.Data;

import java.sql.Date;

@Data
public class PropertyDto {

    private int id;
    private String title;
    private String location;
    private String description;
    private Double price;
    private Date availableStart;
    private Date availableEnd;

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

       // propertyDto.

//        if(property.getHost() != null){
//            propertyDto.setHost(property.getHost());
//        }

        return propertyDto;
    }

}
