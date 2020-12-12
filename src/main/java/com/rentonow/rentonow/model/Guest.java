package com.rentonow.rentonow.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Guest extends Person{
    
    //One quest can have many property Reservations
    @OneToMany(mappedBy="guest_id")
    private <List>PropertyReservation propertyReservation;



    //I think there is not relationship between Host and Guest
    //========================================================
    //Can be ManyToMany or ManyToOne where many guest can rent from one or many host
    //@ManyToOne
    //private Host host;



    //I think there is not relationship between Administrator and Guest
    //========================================================
    //One administrator can manage many guest
    //@ManyToOne
    //private Administrator administrator;
}
