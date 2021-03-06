package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.exception.*;
import org.springframework.http.ResponseEntity;

import javax.security.auth.login.AccountNotFoundException;
import java.time.LocalDate;
import java.util.List;

public interface RentoNowServiceI {

    ///////////////////// Guest ///////////////////////////////////////
    GuestDto addNewGuest(GuestDto guestDto)throws ValidationException;
    GuestDto findGuestById(Integer id) throws GuestNotFoundException;
    List<GuestDto> getAllGuests( );
    GuestDto editGuestById(Integer id, GuestDto guestDto)throws ValidationException, GuestNotFoundException;
    boolean removeGuestById(Integer id) throws GuestNotFoundException;


    ///////////////////// Host ///////////////////////////////////////
    HostDto addNewHost(HostDto hostDto)throws ValidationException;
    HostDto findHostById(Integer id)throws HostNotFoundException;
    List<HostDto> getAllHosts();
    HostDto editHostById(Integer id, HostDto hostDto)throws HostNotFoundException, ValidationException;
    boolean removeHostById(Integer id) throws HostNotFoundException;
    List<PropertyDto> HostProperties(Integer hostId) throws HostNotFoundException;


    ///////////////////// Admin ///////////////////////////////////////
    AdministratorDto addAdministrator(AdministratorDto administratorDto)throws ValidationException ;
    AdministratorDto findAdministratorById(Integer id)throws AccountNotFoundException;
    List<AdministratorDto> getAllAdministrators( );
    AdministratorDto editAdministratorById(Integer id, AdministratorDto administratorDto)throws ValidationException, AdministratorNotFoundException;
    boolean removeAdministratorById(Integer id);
    int getNumOfReservationByGuest(Integer guestId);
    int getNumOfReservationByHost(Integer hostId);
    int getNumOfReservationByProperty(Integer propertyId);

    List<String> statistics();


    ///////////////////// Property ///////////////////////////////////////

//    PropertyDto addProperty(PropertyDto propertyDto)throws ValidationException ;
    PropertyDto addPropertyByHostId(Integer hostId, PropertyDto propertyDto) throws ValidationException, HostNotFoundException;
    PropertyDto findPropertyById(Integer id) throws PropertyNotFoundException;

    List<PropertyDto> getAllProperties();
    PropertyDto editPropertyById(Integer id, PropertyDto propertyDto)throws ValidationException, PropertyNotFoundException;
    boolean removePropertyById(Integer id) throws PropertyNotFoundException ;
    List<PropertyDto> getPropertiesByPriceLocation(Double minPrice, Double maxPrice, String location, String start, String end) throws PropertyNotFoundException;


    ///////////////////// PropertyReservation ///////////////////////////////////////
    ResponseEntity addReservation(PropertyReservationDto propertyReservationDto, Integer guestId, Integer propertyId) throws NotFoundException, InvalidDataException ;
    PropertyReservationDto findReservation(Integer id) throws ReservationNotFoundException;
    List<PropertyReservationDto> getAllReservation( );
    ResponseEntity editReservation(Integer id, PropertyReservationDto propertyReservationDto) throws NotFoundException, InvalidDataException;
    boolean removeReservation(Integer id)throws ReservationNotFoundException ;


    ///////////////////// Images ///////////////////////////////////////
    void saveImageToProperty(String path, Integer id);

}
