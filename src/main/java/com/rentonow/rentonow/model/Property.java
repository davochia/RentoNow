package com.rentonow.rentonow.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.security.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Property {

    @Id
    private UUID uuid;
    private String name;
    private double price;
    private String location;

    private Date createdDate;
    private boolean availableDate;
    private Date startDate;
    private Date endDate;
    private Timestamp timestamp;

    private Payment payment;

    //private BufferedImage image;

    // Justify where many property can be owned by one host
    @ManyToOne
    private List<Host> host;

}
