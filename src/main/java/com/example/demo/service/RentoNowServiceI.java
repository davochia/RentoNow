package com.example.demo.service;

import com.example.demo.dto.GuestDto;
import com.example.demo.dto.HostDto;
import com.example.demo.model.Property;
import javassist.NotFoundException;

import java.util.List;

public interface RentoNowServiceI {

    ///////////////////// Guest ///////////////////////////////////////

    GuestDto addGuest(GuestDto guestDto) ;
    GuestDto findGuestById(int id) throws NotFoundException;
    List<GuestDto> getAllGuest( );
    GuestDto editGuest(int id, GuestDto guestDto)throws NotFoundException;
    boolean removeGuestById(int id) throws NotFoundException;


    ///////////////////// Host ///////////////////////////////////////


    HostDto addHost(HostDto hostDto);
    HostDto getHostById(int id) throws NotFoundException;
    List<HostDto> getAllHosts();

    //List<Property> getProperties(int host_id) throws NotFoundException;


    ///////////////////// Property ///////////////////////////////////////

    Property addProperty(int host_id, Property property) throws NotFoundException;


    ///////////////////// Admin ///////////////////////////////////////


}
