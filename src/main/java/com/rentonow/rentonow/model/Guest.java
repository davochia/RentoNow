package com.rentonow.rentonow.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Guest extends Person{

    
    //Justify where one guest can rent one properties
    @ManyToOne
    private Property property;

    //Can be ManyToMany or ManyToOne where many guest can rent from one or many host
    @ManyToOne
    private Host host;

    //One administrator can manage many guest
    @ManyToOne
    private Administrator administrator;
}
