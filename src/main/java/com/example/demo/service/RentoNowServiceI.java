package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.ImageDB;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public interface RentoNowServiceI {

    ///////////////////// Guest ///////////////////////////////////////

    GuestDto addNewGuest(GuestDto guestDto) ;
    GuestDto findGuestById(int id) throws NotFoundException;
    List<GuestDto> getAllGuests( );
    GuestDto editGuestById(int id, GuestDto guestDto)throws NotFoundException;
    boolean removeGuestById(int id) throws NotFoundException;



    ///////////////////// Host ///////////////////////////////////////

    HostDto addNewHost(HostDto hostDto);
    HostDto findHostById(int id) throws NotFoundException;
    List<HostDto> getAllHosts();
    HostDto editHostById(int id, HostDto hostDto)throws NotFoundException;
    boolean removeHostById(int id) throws NotFoundException;
    List<PropertyDto> HostProperties(int hostId)throws NotFoundException;


    ///////////////////// Admin ///////////////////////////////////////

    AdministratorDto addAdministrator(AdministratorDto administratorDto) ;
    AdministratorDto findAdministratorById(int id) throws NotFoundException;
    List<AdministratorDto> getAllAdministrators( );
    AdministratorDto editAdministratorById(int id, AdministratorDto administratorDto)throws NotFoundException;
    boolean removeAdministratorById(int id) throws NotFoundException;




    ///////////////////// Property ///////////////////////////////////////

//    PropertyDto addProperty(PropertyDto propertyDto) ;
    PropertyDto addPropertyByHostId(int hostId, PropertyDto propertyDto) throws NotFoundException;
    PropertyDto findPropertyById(int id) throws NotFoundException;
    List<PropertyDto> getAllProperties();
    PropertyDto editPropertyById(int id, PropertyDto propertyDto)throws NotFoundException;
    boolean removePropertyById(int id) throws NotFoundException;

    List<PropertyDto> getPropertiesByPriceLocation(Double minPrice, Double maxPrice, String location) ;







    ///////////////////// PropertyReservation ///////////////////////////////////////

    PropertyReservationDto addReservation(PropertyReservationDto propertyReservationDto, int guestId, int propertyId) throws NotFoundException, com.example.demo.exception.NotFoundException;
    PropertyReservationDto findReservation(int id) throws NotFoundException, com.example.demo.exception.NotFoundException;
    List<PropertyReservationDto> getAllReservation( );
    PropertyReservationDto editReservation(int id, PropertyReservationDto propertyReservationDto)throws NotFoundException, com.example.demo.exception.NotFoundException;
    boolean removeReservation(int id) throws NotFoundException, com.example.demo.exception.NotFoundException;



    ///////////////////// Images ///////////////////////////////////////


    ImageDB store(MultipartFile file) throws IOException;
    ImageDB getFile(Integer id);
    Stream<ImageDB> getAllFiles();


}
