package com.example.demo.controller;

/*
    link to swagger ui
    http://localhost:8080/swagger-ui/#/
 */


import com.example.demo.dto.*;
import com.example.demo.service.impl.RentoNowServiceImpl;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class RentonowController {
    
    @Autowired
    private RentoNowServiceImpl rentoNowService;


    ////////////////////// Guest //////////////////////////////////

    // Add new guest
    @ApiOperation(value="Register a new guest to system", response= GuestDto.class)
    @PostMapping("/addGuest")
    public GuestDto addNewGuest(@RequestBody GuestDto guestDto){
        return rentoNowService.addGuest(guestDto);
    }


    // Get guest
    @ApiOperation(value="Get guest by id from the system", response= GuestDto.class)
    @GetMapping("/getGuest/{id}")
    public GuestDto getGuestById(@PathVariable int id)throws NotFoundException{
        return rentoNowService.findGuestById(id);
    }


    // Get all guest
    @ApiOperation(value="Get all guest from the system", response= GuestDto.class)
    @GetMapping("/getGuests")
    public List<GuestDto> getGuests(){
        return rentoNowService.getAllGuests();
    }


    // Edit guest info
    @ApiOperation(value="Edit guest info from in system by id", response= GuestDto.class)
    @PutMapping("/editGuest/{id}")
    public GuestDto modifyGuestById(@PathVariable int id, @RequestBody GuestDto guestDto)throws NotFoundException{
        return rentoNowService.editGuestById(id, guestDto);
    }


    // Delete guest
    @ApiOperation(value="Remove guest by id from the system", response= GuestDto.class)
    @DeleteMapping("/deleteGuest/{id}")
    public boolean deleteGuest(@PathVariable int id)throws NotFoundException{
        return rentoNowService.removeGuestById(id);
    }
    


    ////////////////////// Host //////////////////////////////////


    // Add new Host
    @ApiOperation(value="Register a new host to system", response= HostDto.class)
    @PostMapping("/addHost")
    public HostDto addNewHost(@RequestBody HostDto hostDto){
        return rentoNowService.addHost(hostDto);
    }

    // Get of host by id
    @ApiOperation(value="Get host by id", response=HostDto.class)
    @GetMapping("/getHost{id}")
    public HostDto getHostById(@PathVariable int id) throws NotFoundException{
        return rentoNowService.findHostById(id);
    }


    // Get list of hosts
    @ApiOperation(value="Get all registered host", response=List.class)
    @GetMapping("/getHosts")
    public List<HostDto> getAllHosts() {
        return rentoNowService.getAllHosts();
    }



    // Edit host info
    @ApiOperation(value="Edit host info from in system by id", response= HostDto.class)
    @PutMapping("/editHost/{id}")
    public HostDto modifyHostById(@PathVariable int id, @RequestBody HostDto hostDto)throws NotFoundException{
        return rentoNowService.editHostById(id, hostDto);
    }


    // Delete host
    @ApiOperation(value="Remove guest by id from the system", response= GuestDto.class)
    @DeleteMapping("/deleteHost/{id}")
    public boolean deleteHost(@PathVariable int id)throws NotFoundException{
        return rentoNowService.removeHostById(id);
    }



    ////////////////////// Admin //////////////////////////////////

    // Add new administrator
    @ApiOperation(value="Register a new Administrator to system", response= AdministratorDto.class)
    @PostMapping("/addAdministrator")
    public AdministratorDto addAdministrator(@RequestBody AdministratorDto AdministratorDto){
        return rentoNowService.addAdministrator(AdministratorDto);
    }

    // Get of administrator by id
    @ApiOperation(value="Get administrator by id", response= AdministratorDto.class)
    @GetMapping("/getAdministrator{id}")
    public AdministratorDto getAdministratorById(@PathVariable int id) throws NotFoundException{
        return rentoNowService.findAdministratorById(id);
    }


    // Get list of administrators
    @ApiOperation(value="Get all registered administrator", response=List.class)
    @GetMapping("/getAdministrators")
    public List<AdministratorDto> getAllAdministrators() {
        return rentoNowService.getAllAdministrator();
    }
    

    // Edit administrator info
    @ApiOperation(value="Edit administrator info from in system by id", response= AdministratorDto.class)
    @PutMapping("/editAdministrator/{id}")
    public AdministratorDto modifyAdministratorById(@PathVariable int id, @RequestBody AdministratorDto administratorDto)throws NotFoundException{
        return rentoNowService.editAdministratorById(id, administratorDto);
    }


    // Delete administrator
    @ApiOperation(value="Remove guest by id from the system", response= AdministratorDto.class)
    @DeleteMapping("/deleteAdministrator/{id}")
    public boolean deleteAdministrator(@PathVariable int id)throws NotFoundException{
        return rentoNowService.removeAdministratorById(id);
    }



    ////////////////////// Property //////////////////////////////////

    //Add new Property
    @ApiOperation(value="Add property to system", response= PropertyDto.class)
    @PostMapping("addProperty{id}")
    public PropertyDto addNewProperty(@RequestBody PropertyDto propertyDto)  {
        return rentoNowService.addProperty(propertyDto);
    }

    //Add Property to host
    @ApiOperation(value="Add property to host list", response= HostDto.class)
    @PostMapping("/addHost{hostId}/property")
    public HostDto addNewPropertyToHost(@PathVariable int hostId, @RequestBody PropertyDto propertyDto)  {
        return rentoNowService.addPropertyToHost(hostId, propertyDto);
    }


    // Get property by id
    @ApiOperation(value="Get Property by id", response= PropertyDto.class)
    @GetMapping("/getProperty{id}")
    public PropertyDto getPropertyById(@PathVariable int id) throws NotFoundException{
        return rentoNowService.findPropertyById(id);
    }


    // Get list of Properties
    @ApiOperation(value="Get all registered Property", response=List.class)
    @GetMapping("/getProperties")
    public List<PropertyDto> getAllProperties() {
        return rentoNowService.getAllProperty();
    }



    // Edit Property info
    @ApiOperation(value="Edit Property info from in system by id", response= PropertyDto.class)
    @PutMapping("/editProperty{id}")
    public PropertyDto modifyPropertyById(@PathVariable int id, @RequestBody PropertyDto PropertyDto)throws NotFoundException{
        return rentoNowService.editPropertyById(id, PropertyDto);
    }


    // Delete Property
    @ApiOperation(value="Remove guest by id from the system", response= PropertyDto.class)
    @DeleteMapping("/deleteProperty{id}")
    public boolean deleteProperty(@PathVariable int id)throws NotFoundException{
        return rentoNowService.removePropertyById(id);
    }


    ////////////////////// Property Reservation //////////////////////////////////


    //Add new Property Reservation
    @ApiOperation(value="Add Property Reservation to system", response= PropertyReservationDto.class)
    @PostMapping("addReservation")
    public PropertyReservationDto addNewPropertyReservation(@RequestBody PropertyReservationDto reservation)  {
        return rentoNowService.addPropertyReservationDto(reservation);
    }


    // Get Property Reservation by id
    @ApiOperation(value="Get Property Reservation by id", response= PropertyReservationDto.class)
    @GetMapping("/getReservation{id}")
    public PropertyReservationDto getPropertyReservationById(@PathVariable int id) throws NotFoundException{
        return rentoNowService.findPropertyReservationDto(id);
    }


    // Get list of Property reservation
    @ApiOperation(value="Get all registered Property Reservation", response=List.class)
    @GetMapping("/getReservations")
    public List<PropertyReservationDto> getAllPropertiesReservation() {
        return rentoNowService.getAllPropertyReservationDto();
    }



    // Edit Property Reservation info
    @ApiOperation(value="Edit Property Reservation info from in system by id", response= PropertyReservationDto.class)
    @PutMapping("/editReservation{id}")
    public PropertyReservationDto modifyReservationById(
            @PathVariable int id, @RequestBody PropertyReservationDto propertyReservationDto)throws NotFoundException{
        return rentoNowService.editPropertyReservationDto(id, propertyReservationDto);
    }


    // Delete property reservation
    @ApiOperation(value="Remove reservation by id from the system", response= PropertyReservationDto.class)
    @DeleteMapping("/deleteReservation{id}")
    public boolean deletePropertyReservation(@PathVariable int id)throws NotFoundException{
        return rentoNowService.removePropertyReservationDto(id);
    }


}
