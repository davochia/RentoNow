package com.example.demo.service;

import com.example.demo.dto.*;
import javassist.NotFoundException;

import java.util.List;

public interface RentoNowServiceI {

    ///////////////////// Guest ///////////////////////////////////////

    GuestDto addGuest(GuestDto guestDto) ;
    GuestDto findGuestById(int id) throws NotFoundException;
    List<GuestDto> getAllGuests( );
    GuestDto editGuestById(int id, GuestDto guestDto)throws NotFoundException;
    boolean removeGuestById(int id) throws NotFoundException;



    ///////////////////// Host ///////////////////////////////////////

    HostDto addHost(HostDto hostDto);
    HostDto findHostById(int id) throws NotFoundException;
    List<HostDto> getAllHosts();
    HostDto editHostById(int id, HostDto hostDto)throws NotFoundException;
    boolean removeHostById(int id) throws NotFoundException;


    ///////////////////// Admin ///////////////////////////////////////

    AdministratorDto addAdministrator(AdministratorDto administratorDto) ;
    AdministratorDto findAdministratorById(int id) throws NotFoundException;
    List<AdministratorDto> getAllAdministrator( );
    AdministratorDto editAdministratorById(int id, AdministratorDto administratorDto)throws NotFoundException;
    boolean removeAdministratorById(int id) throws NotFoundException;




    ///////////////////// Property ///////////////////////////////////////

    PropertyDto addProperty(PropertyDto propertyDto) ;
    PropertyDto findPropertyById(int id) throws NotFoundException;
    List<PropertyDto> getAllProperty();
    PropertyDto editPropertyById(int id, PropertyDto propertyDto)throws NotFoundException;
    boolean removePropertyById(int id) throws NotFoundException;

    HostDto addPropertyToHost(int hostId, int propertyId);



    ///////////////////// PropertyReservation ///////////////////////////////////////

    PropertyReservationDto addPropertyReservationDto(PropertyReservationDto propertyReservationDto) ;
    PropertyReservationDto findPropertyReservationDto(int id) throws NotFoundException;
    List<PropertyReservationDto> getAllPropertyReservationDto( );
    PropertyReservationDto editPropertyReservationDto(int id, PropertyReservationDto propertyReservationDto)throws NotFoundException;
    boolean removePropertyReservationDto(int id) throws NotFoundException;

}
