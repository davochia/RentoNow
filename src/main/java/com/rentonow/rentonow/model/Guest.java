package com.rentonow.rentonow.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Guest {
    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;



    //Justify where one guest can rent many properties
    @OneToMany
    private List<Property> property;



    /*
    Justify where many guest can rent from one host or
    Can be ManyToMany where many guest can rent from many host

    @ManyToOne
    private List<Host> host;

     */

}
