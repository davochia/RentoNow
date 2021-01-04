package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.exception.*;
import com.example.demo.model.ImageDB;
import org.springframework.web.multipart.MultipartFile;

<<<<<<< HEAD
import javax.xml.bind.ValidationException;
=======
import javax.security.auth.login.AccountNotFoundException;
>>>>>>> 7d01be94d9229d365aa719d10ade119f987f3322
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

<<<<<<< HEAD
    PropertyReservationDto addReservation(PropertyReservationDto propertyReservationDto, int guestId, int propertyId) throws NotFoundException, com.example.demo.exception.NotFoundException, ValidationException;
    PropertyReservationDto findReservation(int id) throws NotFoundException, com.example.demo.exception.NotFoundException;
=======
    PropertyReservationDto addReservation(PropertyReservationDto propertyReservationDto,
                                          int guestId, int propertyId) throws ValidationException, ReservationNotFoundException;
    PropertyReservationDto findReservation(int id) throws ReservationNotFoundException;
>>>>>>> 7d01be94d9229d365aa719d10ade119f987f3322
    List<PropertyReservationDto> getAllReservation( );
    PropertyReservationDto editReservation(int id, PropertyReservationDto propertyReservationDto)
            throws ValidationException, ReservationNotFoundException;
    boolean removeReservation(int id)throws ReservationNotFoundException ;



    ///////////////////// Images ///////////////////////////////////////


    ImageDB store(MultipartFile file) throws IOException;
    ImageDB getFile(Integer id);
    Stream<ImageDB> getAllFiles();


}
