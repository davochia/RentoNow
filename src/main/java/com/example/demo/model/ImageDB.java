package com.example.demo.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@ApiModel(description="Images of property")
@Table(name = "Image")
@Entity(name = "Image")
@Data
public class ImageDB {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes="Unique auto generated identifier for the system")
    private Integer id;

    private String name;
    private String type;

    @Lob
    @Column(name = "picByte", length = 1000)
    private byte[] data;
}
