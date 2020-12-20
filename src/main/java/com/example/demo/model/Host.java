package com.example.demo.model;

import javax.persistence.*;

import lombok.Data;

import java.util.List;

@Data
@Entity
public class Host {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String phone;
    @Column(unique = true)
    private String email;

//    @OneToMany(mappedBy = "host")
//    private List<Property> properties;

}
