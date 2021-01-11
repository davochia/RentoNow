package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.exception.*;
import com.example.demo.service.impl.RentoNowServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping
public class AdminController {

    @Autowired
    private RentoNowServiceImpl rentoNowService;


    ////////////////////// Guest //////////////////////////////////


    // Get guest
    @PreAuthorize("hasAuthority('guest:read')")
    @ApiOperation(value="Get guest by guest id from the system", response= GuestDto.class)
    @GetMapping("/getGuest/{guestId}")
    public GuestDto getGuest(@PathVariable Integer guestId) throws GuestNotFoundException {
        return rentoNowService.findGuestById(guestId);
    }


    // Get all guest
    @PreAuthorize("hasRole('Role_ADMIN')")
    @ApiOperation(value="Get all guest from the system", response= GuestDto.class)
    @GetMapping("/getGuests")
    public List<GuestDto> getGuests(){
        return rentoNowService.getAllGuests();
    }


    // Delete guest
    @PreAuthorize("hasAuthority('guest:write')")
    @ApiOperation(value="Delete guest by guest id from the system", response= GuestDto.class)
    @DeleteMapping("/deleteGuest/{guestId}")
    public boolean deleteGuest(@PathVariable Integer guestId) {
        return rentoNowService.removeGuestById(guestId);
    }



    ////////////////////// Host //////////////////////////////////

    // Get host by id
    @PreAuthorize("hasRole('Role_ADMIN', 'Role_HOST')")
    @ApiOperation(value="Get host by host id", response=HostDto.class)
    @GetMapping("/getHost/{hostId}")
    public HostDto getHost(@PathVariable Integer hostId) {
        return rentoNowService.findHostById(hostId);
    }



    // Get host properties
    @PreAuthorize("hasRole('Role_HOST', 'Role_ADMIN')")
    @ApiOperation(value="Get host properties find by host id", response=List.class)
    @GetMapping("/getProperties/{hostId}")
    public List<PropertyDto> getProperties(@PathVariable int hostId) {
        return rentoNowService.HostProperties(hostId);
    }


    // Get list of hosts
    @PreAuthorize("hasRole('Role_ADMIN')")
    @ApiOperation(value="Get all hosts from the system", response=List.class)
    @GetMapping("/getHosts")
    public List<HostDto> getHosts() {
        return rentoNowService.getAllHosts();
    }


    // Delete host
    @PreAuthorize("hasAuthority('host:write')")
    @ApiOperation(value="Delete host by host id from the system", response= GuestDto.class)
    @DeleteMapping("/deleteHost/{hostId}")
    public boolean deleteHost(@PathVariable Integer hostId)throws HostNotFoundException{
        return rentoNowService.removeHostById(hostId);
    }



    ////////////////////// Admin //////////////////////////////////

    // Add new administrator
    @PreAuthorize("hasAuthority('admin:write')")
    @ApiOperation(value="Add a new Administrator to system", response= AdministratorDto.class)
    @PostMapping("/addAdministrator")
    public AdministratorDto addAdministrator(@RequestBody AdministratorDto AdministratorDto) throws ValidationException {
        return rentoNowService.addAdministrator(AdministratorDto);
    }

    //Get administrator by id
    @PreAuthorize("hasAuthority('admin:read')")
    @ApiOperation(value="Get administrator by administrator id", response= AdministratorDto.class)
    @GetMapping("/getAdministrator/{adminId}")
    public AdministratorDto getAdministrator(@PathVariable Integer adminId) throws NotFoundException{
        return rentoNowService.findAdministratorById(adminId);
    }


//    // Get list of administrators
//    @PreAuthorize("hasAuthority('admin:read')")
//    @ApiOperation(value="Get all administrator from the system", response=List.class)
//    @GetMapping("/getAdministrators")
//    public List<AdministratorDto> getAdministrators() {
//        return rentoNowService.getAllAdministrators();
//    }


    // Edit administrator info
    @PreAuthorize("hasAuthority('admin:write')")
    @ApiOperation(value="Edit administrator info in system by administrator id", response= AdministratorDto.class)
    @PutMapping("/editAdministrator/{adminId}")
    public AdministratorDto editAdministrator(@PathVariable Integer adminId, @RequestBody AdministratorDto administratorDto) throws NotFoundException, ValidationException {
        try {
            return rentoNowService.editAdministratorById(adminId, administratorDto);
        } catch (AdministratorNotFoundException e) {
            e.printStackTrace();
        } return null;
    }


    // Delete administrator
    @PreAuthorize("hasAuthority('admin:write')")
    @ApiOperation(value="Delete administrator by administrator id from the system", response= AdministratorDto.class)
    @DeleteMapping("/deleteAdministrator/{adminId}")
    public boolean deleteAdministrator(@PathVariable Integer adminId)throws NotFoundException{
        return rentoNowService.removeAdministratorById(adminId);
    }

    // Get number of bookings per host property
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @ApiOperation(value="Get booking statistics filtered by host property", response=Integer.class)
    @RequestMapping(value = "/getNumOfReservationByHost/{hostId}", method = RequestMethod.GET)
    public int getReservationsByHost(@PathVariable Integer hostId) {
        return rentoNowService.getNumOfReservationByHost(hostId);
    }

    // Get number of bookings per guest
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @ApiOperation(value="Get bookings statistics filtered by guest", response=Integer.class)
    @RequestMapping(value = "/getNumOfReservationByGuest/{guestId}", method = RequestMethod.GET)
    public int getReservationsByGuest(@PathVariable Integer guestId) throws GuestNotFoundException {
        return rentoNowService.getNumOfReservationByGuest(guestId);
    }

    // Get number of bookings per property
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @ApiOperation(value="Get reservations bookings filtered by property", response=Integer.class)
    @RequestMapping(value = "/getNumOfReservationByProperty/{propertyId}", method = RequestMethod.GET)
    public int getReservationsByProperty(@PathVariable Integer propertyId) {
        return rentoNowService.getNumOfReservationByProperty(propertyId);
    }



    ////////////////////// Property //////////////////////////////////

    // Get property by id
    @PreAuthorize("hasAuthority('property:read')")
    @ApiOperation(value="Get property by property id", response= PropertyDto.class)
    @GetMapping("/getProperty/{propertyId}")
    public PropertyDto getProperty(@PathVariable Integer propertyId) throws PropertyNotFoundException {
        try {
            return rentoNowService.findPropertyById(propertyId);
        } catch (PropertyNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    // Get list of Properties
    @PreAuthorize("hasRole('Role_ADMIN', 'Role_GUEST')")
    @ApiOperation(value="Get all Properties from system", response=List.class)
    @GetMapping("/getProperties")
    public List<PropertyDto> getProperties() {
        return rentoNowService.getAllProperties();
    }

    // Edit Property info
    @PreAuthorize("hasAuthority('property:write')")
    @ApiOperation(value="Edit Property info in system by property id", response= PropertyDto.class)
    @PutMapping("/editProperty/{propertyId}")
    public PropertyDto editProperty(@PathVariable Integer propertyId, @RequestBody PropertyDto PropertyDto) throws PropertyNotFoundException, ValidationException {
        return rentoNowService.editPropertyById(propertyId, PropertyDto);
    }


    // Delete Property
    @PreAuthorize("hasAuthority('property:write')")
    @ApiOperation(value="Delete property by property id from the system", response= PropertyDto.class)
    @DeleteMapping("/deleteProperty/{propertyId}")
    public boolean deleteProperty(@PathVariable Integer propertyId)throws PropertyNotFoundException{
        return rentoNowService.removePropertyById(propertyId);
    }


    ////////////////////// Property Reservation //////////////////////////////////

    // Get Property Reservation by id
    @PreAuthorize("hasAuthority('reservation:read')")
    @ApiOperation(value="Get reservation by reservation id", response= PropertyReservationDto.class)
    @GetMapping("/getReservation/{reserveId}")
    public PropertyReservationDto getPropertyReservationById(@PathVariable Integer reserveId) throws ReservationNotFoundException{
        return rentoNowService.findReservation(reserveId);
    }


    // Get list of Property reservation
    @PreAuthorize("hasAnyRole('Role_ADMIN')")
    @ApiOperation(value="Get all property reservations", response=List.class)
    @GetMapping("/getReservations")
    public List<PropertyReservationDto> getReservations() {
        return rentoNowService.getAllReservation();
    }


    // Delete property reservation
    @PreAuthorize("hasAuthority('reservation:write')")
    @ApiOperation(value="Delete reservation by id from the system", response= PropertyReservationDto.class)
    @DeleteMapping("/deleteReservation/{reserveId}")
    public boolean deleteReservation(@PathVariable Integer reserveId)throws ReservationNotFoundException{
        return rentoNowService.removeReservation(reserveId);
    }


}