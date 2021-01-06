package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.exception.*;
import com.example.demo.model.ImageDB;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

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
    List<PropertyReservationDto> getReservationByGuest(Integer guestId);
    List<PropertyReservationDto> getReservationByHost(Integer hostId);
    List<PropertyReservationDto> getReservationByProperty(Integer propertyId);


    ///////////////////// Property ///////////////////////////////////////

//    PropertyDto addProperty(PropertyDto propertyDto)throws ValidationException ;
    PropertyDto addPropertyByHostId(Integer hostId, PropertyDto propertyDto) throws ValidationException, HostNotFoundException;
    void saveImageToProperty(String path, Integer id);
    PropertyDto findPropertyById(Integer id) throws PropertyNotFoundException;

    List<PropertyDto> getAllProperties();
    PropertyDto editPropertyById(Integer id, PropertyDto propertyDto)throws ValidationException, PropertyNotFoundException;
    boolean removePropertyById(Integer id) throws PropertyNotFoundException ;
    List<PropertyDto> getPropertiesByPriceLocation(Double minPrice, Double maxPrice, String location) throws PropertyNotFoundException;


    ///////////////////// PropertyReservation ///////////////////////////////////////
    ResponseEntity addReservation(PropertyReservationDto propertyReservationDto,
                                  int guestId, int propertyId) throws ValidationException, ReservationNotFoundException, NotFoundException, InvalidDataException;
    PropertyReservationDto findReservation(Integer id) throws ReservationNotFoundException;
    List<PropertyReservationDto> getAllReservation( );
    PropertyReservationDto editReservation(Integer id, PropertyReservationDto propertyReservationDto)
            throws ValidationException, ReservationNotFoundException;
    boolean removeReservation(Integer id)throws ReservationNotFoundException ;


    ///////////////////// Images ///////////////////////////////////////
    ImageDB store(MultipartFile file) throws IOException;
    ImageDB getFile(Integer id);
    Stream<ImageDB> getAllFiles();


}
