package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.exception.*;
import com.example.demo.service.impl.RentoNowServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("admin/rentonow")
public class AdminController {

    @Autowired
    private RentoNowServiceImpl rentoNowService;

    ////////////////////// Guest //////////////////////////////////

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


    // Delete guest
//    @PreAuthorize("hasAuthority('guest:write')")
    @ApiOperation(value="Delete guest by guest id from the system", response= GuestDto.class)
    @DeleteMapping("/deleteGuest/{guestId}")
    public boolean deleteGuest(@PathVariable Integer guestId) {
        return rentoNowService.removeGuestById(guestId);
    }



    ////////////////////// Host //////////////////////////////////

    // Get host by id
//    @PreAuthorize("hasAuthority('host:read')")
    @ApiOperation(value="Get host by host id", response=HostDto.class)
    @GetMapping("/getHost{hostId}")
    public HostDto getHost(@PathVariable Integer hostId) {
        return rentoNowService.findHostById(hostId);
    }

    // Get host properties
//    @PreAuthorize("hasAnyRole('ROLE_HOST', 'ROLE_ADMIN')")
    @ApiOperation(value="Get host properties find by host id", response=List.class)
    @GetMapping("/getProperties{hostId}")
    public List<PropertyDto> getProperties(@PathVariable Integer hostId) {
        return rentoNowService.HostProperties(hostId);
    }


    // Get list of hosts
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @ApiOperation(value="Get all hosts from the system", response=List.class)
    @GetMapping("/getHosts")
    public List<HostDto> getHosts() {
        return rentoNowService.getAllHosts();
    }


    // Delete host
//    @PreAuthorize("hasAuthority('host:write')")
    @ApiOperation(value="Delete host by host id from the system", response= GuestDto.class)
    @DeleteMapping("/deleteHost/{hostId}")
    public boolean deleteHost(@PathVariable Integer hostId)throws HostNotFoundException{
        return rentoNowService.removeHostById(hostId);
    }



    ////////////////////// Admin //////////////////////////////////

    // Add new administrator
//    @PreAuthorize("hasAuthority('admin:write')")
    @ApiOperation(value="Add a new Administrator to system", response= AdministratorDto.class)
    @PostMapping("/addAdministrator")
    public AdministratorDto addAdministrator(@RequestBody AdministratorDto AdministratorDto) throws ValidationException {
        return rentoNowService.addAdministrator(AdministratorDto);
    }

    //Get administrator by id
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @ApiOperation(value="Get administrator by administrator id", response= AdministratorDto.class)
    @GetMapping("/getAdministrator{adminId}")
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
//    @PreAuthorize("hasAuthority('admin:write')")
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
//    @PreAuthorize("hasAuthority('admin:write')")
    @ApiOperation(value="Delete administrator by administrator id from the system", response= AdministratorDto.class)
    @DeleteMapping("/deleteAdministrator/{adminId}")
    public boolean deleteAdministrator(@PathVariable Integer adminId)throws NotFoundException{
        return rentoNowService.removeAdministratorById(adminId);
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

    // Delete Property
//    @PreAuthorize("hasAuthority('property:write')")
    @ApiOperation(value="Delete property by property id from the system", response= PropertyDto.class)
    @DeleteMapping("/deleteProperty{propertyId}")
    public boolean deleteProperty(@PathVariable Integer propertyId)throws PropertyNotFoundException{
        return rentoNowService.removePropertyById(propertyId);
    }


    ////////////////////// Property Reservation //////////////////////////////////

    // Get Property Reservation by id
//    @PreAuthorize("hasAuthority('reservation:read')")
    @ApiOperation(value="Get reservation by reservation id", response= PropertyReservationDto.class)
    @GetMapping("/getReservation{reserveId}")
    public PropertyReservationDto getPropertyReservationById(@PathVariable Integer reserveId) throws ReservationNotFoundException{
        return rentoNowService.findReservation(reserveId);
    }


    // Get list of Property reservation
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @ApiOperation(value="Get all property reservations", response=List.class)
    @GetMapping("/getReservations")
    public List<PropertyReservationDto> getReservations() {
        return rentoNowService.getAllReservation();
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


    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @ApiOperation(value="Get reservations statistics filtered by host id", response=Integer.class)
    @RequestMapping(value = "/getHostReservedProperties{hostId}", method = RequestMethod.GET)
    public int getNumOfReservationsByHost(@PathVariable Integer hostId) {
        return rentoNowService.getReservationByHost(hostId);
    }

    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @ApiOperation(value="Get reservations statistics filtered by guest id", response=Integer.class)
    @RequestMapping(value = "/getNumOfReservationByGuestId{guestId}", method = RequestMethod.GET)
    public int getReservationsByGuest(@PathVariable Integer guestId) throws GuestNotFoundException {
        return rentoNowService.getReservationByGuest(guestId);
    }

    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @ApiOperation(value="Get reservations statistics filtered by property id", response=Integer.class)
    @RequestMapping(value = "/getNumOfReservationByPropertyId{propertyId}", method = RequestMethod.GET)
    public int getReservationsByProperty(@PathVariable Integer propertyId) {
        return rentoNowService.getReservationByProperty(propertyId);
    }

}