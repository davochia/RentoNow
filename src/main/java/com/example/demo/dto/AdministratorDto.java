package com.example.demo.dto;

import com.example.demo.model.Administrator;
import lombok.Data;

@Data
public class AdministratorDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;

    public static Administrator getAdministrator(AdministratorDto administratorDto){
        Administrator administrator = new Administrator();
        administrator.setId(administratorDto.getId());
        administrator.setFirstName(administratorDto.getFirstName());
        administrator.setLastName(administratorDto.getLastName());
        administrator.setPhone(administratorDto.getPhone());
        administrator.setEmail(administratorDto.getEmail());

        return administrator;
    }

    public static AdministratorDto getAdministratorDto(Administrator administrator){
        AdministratorDto administratorDto = new AdministratorDto();
        administratorDto.setId(administrator.getId());
        administratorDto.setFirstName(administrator.getFirstName());
        administratorDto.setLastName(administrator.getLastName());
        administratorDto.setPhone(administrator.getPhone());
        administratorDto.setEmail(administrator.getEmail());

        return administratorDto;
    }
}
