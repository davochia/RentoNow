package com.rentonow.rentonow.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.security.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Property {

    @Id
    private UUID propertyId;
    private String name;
    private String description;
    private double price;
    private String location;
    private Date createdDate;
    private Date updatedDate;

    //private boolean availableDate;
    //private Date startDate;
    //private Date endDate;
    //private Timestamp timestamp;
    //private Payment payment;
    //private BufferedImage image;

    //One property may have mutiple reservations
    @OneToMany(mappedBy = "property_id")
    private List<PropertyReservation> propertyReservation;

    // Justify where one property can be rented by many guest
    @OneToMany
    private List<Guest> guest;

    // Justify where many property can be owned by one host
    @ManyToOne
    private Host host;

    //I think there is not relationship between Guest and Host
    //========================================================
    //@ManyToOne
    //private Administrator administrator;

}
