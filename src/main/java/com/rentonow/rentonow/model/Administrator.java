package com.rentonow.rentonow.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Administrator extends Person {

    //@Id
    //private UUID administrationId;
    //private String firstName;
    //private String lastName;


    // Justify where administrator could CRUD list of host
    //@OneToMany
    //private List<Host> host;

    // Justify where administrator could CRUD list of guest
    //@OneToMany
    //private List<Guest> guest;

    // Justify where administrator could CRUD list of property
    //@OneToMany
    //private List<Property> property;

}
