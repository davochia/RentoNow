package com.rentonow.rentonow.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Host {

    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    // Justify where one host can have many properties for rent
    @OneToMany(mappedBy="host")
    private List<Property> property;



    /*
    // Justify where one host can have many guest renting their property
    @OneToMany
    private List<Guest> guest;

     */



}
