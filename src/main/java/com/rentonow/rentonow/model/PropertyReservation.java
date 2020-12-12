package com.rentonow.rentonow.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class PropertyReservation {

    @Id
    private UUID reservationId;
    private Date startDate;
    private Date endDate;

    private List<Guest> guest;

    @ManyToOne
    private List<Property> property;
}
