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
//@RequestMapping("admin/rentonow")
public class AdminController {

    @Autowired
    private RentoNowServiceImpl rentoNowService;

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
    @GetMapping("/getAdministrator{adminId}")
    public AdministratorDto getAdministrator(@PathVariable int adminId) throws NotFoundException {
        return rentoNowService.findAdministratorById(adminId);
    }


    // Edit administrator info
    @PreAuthorize("hasAuthority('admin:write')")
    @ApiOperation(value="Edit administrator info in system by administrator id", response= AdministratorDto.class)
    @PutMapping("/editAdministrator/{adminId}")
    public AdministratorDto editAdministrator(@PathVariable int adminId, @RequestBody AdministratorDto administratorDto) throws NotFoundException, ValidationException {
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
    public boolean deleteAdministrator(@PathVariable int adminId)throws NotFoundException{
        return rentoNowService.removeAdministratorById(adminId);
    }


    ////////////////////// Guest //////////////////////////////////

    // Get guest
    @PreAuthorize("hasAuthority('guest:read')")
    @ApiOperation(value="Get guest by guest id from the system", response= GuestDto.class)
    @GetMapping("/getGuest/{guestId}")
    public GuestDto getGuest(@PathVariable int guestId) throws GuestNotFoundException {
        return rentoNowService.findGuestById(guestId);
    }


    // Get all guest
    @PreAuthorize("hasAnyRole('Role_ADMIN')")
    @ApiOperation(value="Get all guest from the system", response= GuestDto.class)
    @GetMapping("/getGuests")
    public List<GuestDto> getGuests(){
        return rentoNowService.getAllGuests();
    }


    // Delete guest
    @PreAuthorize("hasAuthority('guest:write')")
    @ApiOperation(value="Delete guest by guest id from the system", response= GuestDto.class)
    @DeleteMapping("/deleteGuest/{guestId}")
    public boolean deleteGuest(@PathVariable int guestId) {
        return rentoNowService.removeGuestById(guestId);
    }



    ////////////////////// Host //////////////////////////////////

    // Get host by id
    @PreAuthorize("hasAnyRole('Role_ADMIN', 'Role_HOST')")
    @ApiOperation(value="Get host by host id", response=HostDto.class)
    @GetMapping("/getHost{hostId}")
    public HostDto getHost(@PathVariable int hostId) {
        return rentoNowService.findHostById(hostId);
    }



    // Get host properties
    @PreAuthorize("hasAnyRole('Role_HOST', 'Role_ADMIN')")
    @ApiOperation(value="Get host properties find by host id", response= List.class)
    @GetMapping("/getProperties{hostId}")
    public List<PropertyDto> getProperties(@PathVariable int hostId) {
        return rentoNowService.HostProperties(hostId);
    }


    // Get list of hosts
    @PreAuthorize("hasAnyRole('Role_ADMIN')")
    @ApiOperation(value="Get all hosts from the system", response=List.class)
    @GetMapping("/getHosts")
    public List<HostDto> getHosts() {
        return rentoNowService.getAllHosts();
    }


    // Delete host
    @PreAuthorize("hasAuthority('host:write')")
    @ApiOperation(value="Delete host by host id from the system", response= GuestDto.class)
    @DeleteMapping("/deleteHost/{hostId}")
    public boolean deleteHost(@PathVariable int hostId)throws HostNotFoundException{
        return rentoNowService.removeHostById(hostId);
    }



    ////////////////////// Property //////////////////////////////////

    // Get property by id
    @PreAuthorize("hasAuthority('property:read')")
    @ApiOperation(value="Get property by property id", response= PropertyDto.class)
    @GetMapping("/getProperty{propertyId}")
    public PropertyDto getProperty(@PathVariable int propertyId) throws PropertyNotFoundException {
        try {
            return rentoNowService.findPropertyById(propertyId);
        } catch (PropertyNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get list of Properties
    @PreAuthorize("hasAnyRole('Role_ADMIN', 'Role_GUEST')")
    @ApiOperation(value="Get all Properties from system", response=List.class)
    @GetMapping("/getProperties")
    public List<PropertyDto> getProperties() {
        return rentoNowService.getAllProperties();
    }

    // Delete Property
    @PreAuthorize("hasAuthority('property:write')")
    @ApiOperation(value="Delete property by property id from the system", response= PropertyDto.class)
    @DeleteMapping("/deleteProperty{propertyId}")
    public boolean deleteProperty(@PathVariable int propertyId)throws PropertyNotFoundException{
        return rentoNowService.removePropertyById(propertyId);
    }


    ////////////////////// Property Reservation //////////////////////////////////


    // Get Property Reservation by id
    @PreAuthorize("hasAuthority('reservation:read')")
    @ApiOperation(value="Get reservation by reservation id", response= PropertyReservationDto.class)
    @GetMapping("/getReservation{reserveId}")
    public PropertyReservationDto getPropertyReservationById(@PathVariable int reserveId) throws ReservationNotFoundException{
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
    @DeleteMapping("/deleteReservation{reserveId}")
    public boolean deleteReservation(@PathVariable int reserveId)throws ReservationNotFoundException{
        return rentoNowService.removeReservation(reserveId);
    }



}
