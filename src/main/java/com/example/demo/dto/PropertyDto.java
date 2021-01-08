package com.example.demo.dto;


import com.example.demo.model.Property;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PropertyDto {

    private Integer id;
    private String title;
    private String location;
    private String description;
    private Double price;
    private LocalDate availableStart;
    private LocalDate availableEnd;

    private String propertyImages;

    private String hostFirstName;
    private String hostLastName;
    private String hostEmail;
    private String hostPhone;


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

        if(property.getImages() != null){
            propertyDto.propertyImages = property.getImages();
            //property.getImages().forEach(image -> propertyDto.propertyImages.add(image.getImage()));
        }

        if(property.getHost() != null){
            propertyDto.hostFirstName = property.getHost().getFirstName();
            propertyDto.hostLastName = property.getHost().getLastName();
            propertyDto.hostPhone= property.getHost().getPhone();
            propertyDto.hostEmail = property.getHost().getEmail();

        }

        return propertyDto;
    }

}
