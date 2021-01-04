package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.exception.*;
import com.example.demo.model.ImageDB;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public interface RentoNowServiceI {

    ///////////////////// Guest ///////////////////////////////////////

    GuestDto addNewGuest(GuestDto guestDto)throws ValidationException;
    GuestDto findGuestById(int id) throws GuestNotFoundException;
    List<GuestDto> getAllGuests( );
    GuestDto editGuestById(int id, GuestDto guestDto)throws ValidationException, GuestNotFoundException;
    boolean removeGuestById(int id) throws GuestNotFoundException;



    ///////////////////// Host ///////////////////////////////////////

    HostDto addNewHost(HostDto hostDto)throws ValidationException;
    HostDto findHostById(int id)throws HostNotFoundException;
    List<HostDto> getAllHosts();
    HostDto editHostById(int id, HostDto hostDto)throws HostNotFoundException, ValidationException;
    boolean removeHostById(int id) throws HostNotFoundException;
    List<PropertyDto> HostProperties(int hostId) throws HostNotFoundException;


    ///////////////////// Admin ///////////////////////////////////////

    AdministratorDto addAdministrator(AdministratorDto administratorDto)throws ValidationException ;
    AdministratorDto findAdministratorById(int id)throws AccountNotFoundException;
    List<AdministratorDto> getAllAdministrators( );
    AdministratorDto editAdministratorById(int id, AdministratorDto administratorDto)throws
            ValidationException, AdministratorNotFoundException;
    boolean removeAdministratorById(int id);




    ///////////////////// Property ///////////////////////////////////////

    PropertyDto addProperty(PropertyDto propertyDto)throws ValidationException ;
    PropertyDto addPropertyByHostId(int hostId, PropertyDto propertyDto) throws ValidationException, HostNotFoundException;
    PropertyDto findPropertyById(int id) throws PropertyNotFoundException;
    List<PropertyDto> getAllProperties();
    PropertyDto editPropertyById(int id, PropertyDto propertyDto)throws ValidationException, PropertyNotFoundException;
    boolean removePropertyById(int id) throws PropertyNotFoundException ;

    List<PropertyDto> getPropertiesByPriceLocation(Double minPrice, Double maxPrice, String location) throws PropertyNotFoundException;







    ///////////////////// PropertyReservation ///////////////////////////////////////


    PropertyReservationDto addReservation(PropertyReservationDto propertyReservationDto, int guestId, int propertyId) throws NotFoundException, com.example.demo.exception.NotFoundException, ValidationException, InvalidDataException;
    PropertyReservationDto findReservation(int id) throws ReservationNotFoundException;
    List<PropertyReservationDto> getAllReservation( );
    PropertyReservationDto editReservation(int id, PropertyReservationDto propertyReservationDto)
            throws ValidationException, ReservationNotFoundException;
    boolean removeReservation(int id)throws ReservationNotFoundException ;



    ///////////////////// Images ///////////////////////////////////////


    ImageDB store(MultipartFile file) throws IOException;
    ImageDB getFile(Integer id);
    Stream<ImageDB> getAllFiles();


}
