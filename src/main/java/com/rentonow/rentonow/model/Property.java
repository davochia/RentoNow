package com.rentonow.rentonow.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.security.Timestamp;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Property {

    @Id
    private UUID uuid;
    private String propertyAddress;
    private boolean price;

    //private BufferedImage image;

    private Date createdDate;
    private Date availableDate;
    private Timestamp timestamp;



    // Justify where many property can be owned by one host
    @ManyToOne
    private Host host;

}
