package com.example.demo.model;

import javax.persistence.*;

import java.sql.Date;
import java.util.List;
import lombok.Data;

@Entity
@Data
public class Property {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String title;
    private String details;
    private Double price;
    private Date available_from;
    private Date available_to;
    
    @ManyToOne
    //@JoinColumn(foreignKey = @ForeignKey(name = "fk_property_host_id"), name="host_id", referencedColumnName = "id", columnDefinition = "int")
    private Host host;

}
