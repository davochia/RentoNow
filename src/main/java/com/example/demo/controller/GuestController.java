package com.example.demo.controller;

import com.example.demo.dto.GuestDto;
import com.example.demo.dto.PropertyDto;
import com.example.demo.dto.PropertyReservationDto;
import com.example.demo.exception.*;
import com.example.demo.service.impl.RentoNowServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("guest/rentonow")
public class GuestController {


    @Autowired
    private RentoNowServiceImpl rentoNowService;

    ////////////////////// Guest //////////////////////////////////

    // Add new guest
//    @PreAuthorize("hasAuthority('guest:write')")
    @ApiOperation(value="Add a new guest to system", response= GuestDto.class)
    @PostMapping("/addGuest")
    public GuestDto addGuest(@RequestBody GuestDto guestDto) throws ValidationException {
        return rentoNowService.addNewGuest(guestDto);
    }


    // Get guest
//    @PreAuthorize("hasAuthority('guest:read')")
    @ApiOperation(value="Get guest by guest id from the system", response= GuestDto.class)
    @GetMapping("/getGuest/{guestId}")
    public GuestDto getGuest(@PathVariable Integer guestId) throws GuestNotFoundException {
        return rentoNowService.findGuestById(guestId);
    }


    // Get all guest
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @ApiOperation(value="Get all guest from the system", response= GuestDto.class)
    @GetMapping("/getGuests")
    public List<GuestDto> getGuests(){
        return rentoNowService.getAllGuests();
    }


    // Edit guest info
//    @PreAuthorize("hasAnyRole('ROLE_GUEST')")
    @ApiOperation(value="Edit guest info from in system by guest id", response= GuestDto.class)
    @PutMapping("/editGuest/{guestId}")
    public GuestDto editGuest(@PathVariable Integer guestId, @RequestBody GuestDto guestDto) throws ValidationException {
        return rentoNowService.editGuestById(guestId, guestDto);
    }


    // Delete guest
//    @PreAuthorize("hasAuthority('guest:write')")
    @ApiOperation(value="Delete guest by guest id from the system", response= GuestDto.class)
    @DeleteMapping("/deleteGuest/{guestId}")
    public boolean deleteGuest(@PathVariable Integer guestId) {
        return rentoNowService.removeGuestById(guestId);
    }


    ////////////////////// Property //////////////////////////////////

    // Get property by id
//    @PreAuthorize("hasAuthority('property:read')")
    @ApiOperation(value="Get property by property id", response= PropertyDto.class)
    @GetMapping("/getProperty{propertyId}")
    public PropertyDto getProperty(@PathVariable Integer propertyId) throws PropertyNotFoundException {
        try {
            return rentoNowService.findPropertyById(propertyId);
        } catch (PropertyNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    // Get list of Properties
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_GUEST')")
    @ApiOperation(value="Get all Properties from system", response=List.class)
    @GetMapping("/getProperties")
    public List<PropertyDto> getProperties() {
        return rentoNowService.getAllProperties();
    }

    // Get list of filtered Properties
//    @PreAuthorize("hasAnyRole('ROLE_GUEST')")
    @ApiOperation(value="Get all Properties from system filtered by price and location", response=List.class)
    @GetMapping("/filterProperties")
    public List<PropertyDto> filterProperties(@RequestParam Double maxPrice, @RequestParam Double minPrice,
                                              @RequestParam String location) throws PropertyNotFoundException {
        return rentoNowService.getPropertiesByPriceLocation(minPrice, maxPrice, location);
    }


    ////////////////////// Property Reservation //////////////////////////////////


    //Add new Property Reservation
//    @PreAuthorize("hasAuthority('reservation:write')")
    @ApiOperation(value="Add reservation by property id and host id to system", response= PropertyReservationDto.class)
    @PostMapping("addReservation{date}/{guestId}/{propertyId}")
    public ResponseEntity addReservation(@RequestBody PropertyReservationDto propertyReservationDto,
                                         @PathVariable Integer guestId,
                                         @PathVariable Integer propertyId) throws NotFoundException, InvalidDataException {
        return rentoNowService.addReservation(propertyReservationDto, guestId, propertyId);
    }


    // Get Property Reservation by id
//    @PreAuthorize("hasAuthority('reservation:read')")
    @ApiOperation(value="Get reservation by reservation id", response= PropertyReservationDto.class)
    @GetMapping("/getReservation{reserveId}")
    public PropertyReservationDto getPropertyReservationById(@PathVariable Integer reserveId) throws ReservationNotFoundException{
        return rentoNowService.findReservation(reserveId);
    }



    // Edit Property Reservation info
//    @PreAuthorize("hasAuthority('reservation:write')")
    @ApiOperation(value="Edit Property reservation info in system by reservation id", response= PropertyReservationDto.class)
    @PutMapping("/editReservation{reserveId}")
    public ResponseEntity editReservation(
            @PathVariable Integer reserveId, @RequestBody PropertyReservationDto propertyReservationDto) throws ReservationNotFoundException, NotFoundException, InvalidDataException {
        return rentoNowService.editReservation(reserveId, propertyReservationDto);
    }


    // Delete property reservation
//    @PreAuthorize("hasAuthority('reservation:write')")
    @ApiOperation(value="Delete reservation by id from the system", response= PropertyReservationDto.class)
    @DeleteMapping("/deleteReservation{reserveId}")
    public boolean deleteReservation(@PathVariable Integer reserveId)throws ReservationNotFoundException{
        return rentoNowService.removeReservation(reserveId);
    }

}