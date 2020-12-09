package com.rentonow.rentonow.dto;

import lombok.Data;

import javax.persistence.Id;
import java.util.UUID;

@Data
public class HostDto {
    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
