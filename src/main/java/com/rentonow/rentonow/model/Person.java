package com.rentonow.rentonow.model;

import lombok.Data;

import javax.persistence.Id;
import java.util.UUID;

/*
This class can be use to abstract the host, guest and admin
where similar information is available in all. Though just
created but not extended in any case we want to do some adjustments
 */

@Data
public abstract class Person {

    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

}
