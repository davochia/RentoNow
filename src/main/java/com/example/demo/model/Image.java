package com.example.demo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes="Unique auto generated identifier for the system")
    private Integer id;

    private String image;

    @ManyToOne
    private Property property;

}
