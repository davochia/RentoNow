package com.rentonow.rentonow.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Host extends Person{

    // Justify where one host can have many properties for rent
    @OneToMany(mappedBy="host")
    private List<Property> property;

    // Justify where one host can have many guest renting their property
    @OneToMany
    private List<Guest> guest;

    //One administrator can manage many host
    @ManyToOne
    private Administrator administrator;
}
