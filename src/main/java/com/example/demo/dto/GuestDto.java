package com.example.demo.dto;


import com.example.demo.model.Guest;
import lombok.Data;

@Data
public class GuestDto {

    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;

    public static Guest getGuest(GuestDto guestDto){
        Guest guest = new Guest();
        guest.setId(guestDto.getId());
        guest.setFirstName(guestDto.getFirstName());
        guest.setLastName(guestDto.getLastName());
        guest.setPhone(guestDto.getPhone());
        guest.setEmail(guestDto.getEmail());

        return guest;
    }

    public static GuestDto getGuestDto(Guest guest){
        GuestDto guestDto = new GuestDto();
        guestDto.setId(guest.getId());
        guestDto.setFirstName(guest.getFirstName());
        guestDto.setLastName(guest.getLastName());
        guestDto.setPhone(guest.getPhone());
        guestDto.setEmail(guest.getEmail());

        return guestDto;
    }
}
