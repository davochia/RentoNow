package com.example.demo.controller;

/*
    link to swagger ui
    http://localhost:8080/swagger-ui/#/

    @Controller
    @CrossOrigin("http://localhost:8081")
 */

import com.example.demo.dto.*;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.service.impl.RentoNowServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping
public class RentonowController {
    
    @Autowired
    private RentoNowServiceImpl rentoNowService;


    ////////////////////// Guest //////////////////////////////////

    // Add new guest
    @ApiOperation(value="Add a new guest to system", response= GuestDto.class)
    @PostMapping("/addGuest")
    public GuestDto addGuest(@RequestBody GuestDto guestDto){
        return rentoNowService.addNewGuest(guestDto);
    }


    // Get guest
    @ApiOperation(value="Get guest by guest id from the system", response= GuestDto.class)
    @GetMapping("/getGuest/{guestId}")
    public GuestDto getGuest(@PathVariable int guestId) throws NotFoundException {
        return rentoNowService.findGuestById(guestId);
    }


    // Get all guest
    @ApiOperation(value="Get all guest from the system", response= GuestDto.class)
    @GetMapping("/getGuests")
    public List<GuestDto> getGuests(){
        return rentoNowService.getAllGuests();
    }


    // Edit guest info
    @ApiOperation(value="Edit guest info from in system by guest id", response= GuestDto.class)
    @PutMapping("/editGuest/{guestId}")
    public GuestDto editGuest(@PathVariable int guestId, @RequestBody GuestDto guestDto)throws NotFoundException {
        return rentoNowService.editGuestById(guestId, guestDto);
    }


    // Delete guest
    @ApiOperation(value="Delete guest by guest id from the system", response= GuestDto.class)
    @DeleteMapping("/deleteGuest/{guestId}")
    public boolean deleteGuest(@PathVariable int guestId)throws NotFoundException{
        return rentoNowService.removeGuestById(guestId);
    }
    


    ////////////////////// Host //////////////////////////////////


    // Add new Host
    @ApiOperation(value="Add a new host to system", response= HostDto.class)
    @PostMapping("/addHost")
    public HostDto addHost(@RequestBody HostDto hostDto){
        return rentoNowService.addNewHost(hostDto);
    }

    // Get host by id
    @ApiOperation(value="Get host by host id", response=HostDto.class)
    @GetMapping("/getHost{hostId}")
    public HostDto getHost(@PathVariable int hostId) throws NotFoundException{
        return rentoNowService.findHostById(hostId);
    }



    // Get host properties
    @ApiOperation(value="Get host properties find by host id", response=List.class)
    @GetMapping("/getProperties{hostId}")
    public List<PropertyDto> getProperties(@PathVariable int hostId)throws NotFoundException{
        return rentoNowService.HostProperties(hostId);
    }


    // Get list of hosts
    @ApiOperation(value="Get all hosts from the system", response=List.class)
    @GetMapping("/getHosts")
    public List<HostDto> getHosts() {
        return rentoNowService.getAllHosts();
    }



    // Edit host info
    @ApiOperation(value="Edit host info from in system by host id", response= HostDto.class)
    @PutMapping("/editHost/{hostId}")
    public HostDto editHost(@PathVariable int hostId, @RequestBody HostDto hostDto)throws NotFoundException{
        return rentoNowService.editHostById(hostId, hostDto);
    }


    // Delete host
    @ApiOperation(value="Delete host by host id from the system", response= GuestDto.class)
    @DeleteMapping("/deleteHost/{hostId}")
    public boolean deleteHost(@PathVariable int hostId)throws NotFoundException{
        return rentoNowService.removeHostById(hostId);
    }



    ////////////////////// Admin //////////////////////////////////

    // Add new administrator
    @ApiOperation(value="Add a new Administrator to system", response= AdministratorDto.class)
    @PostMapping("/addAdministrator")
    public AdministratorDto addAdministrator(@RequestBody AdministratorDto AdministratorDto){
        return rentoNowService.addAdministrator(AdministratorDto);
    }

    // Get of administrator by id
    @ApiOperation(value="Get administrator by administrator id", response= AdministratorDto.class)
    @GetMapping("/getAdministrator{adminId}")
    public AdministratorDto getAdministrator(@PathVariable int adminId) throws NotFoundException{
        return rentoNowService.findAdministratorById(adminId);
    }


    // Get list of administrators
    @ApiOperation(value="Get all administrator from the system", response=List.class)
    @GetMapping("/getAdministrators")
    public List<AdministratorDto> getAdministrators() {
        return rentoNowService.getAllAdministrators();
    }
    

    // Edit administrator info
    @ApiOperation(value="Edit administrator info in system by administrator id", response= AdministratorDto.class)
    @PutMapping("/editAdministrator/{adminId}")
    public AdministratorDto editAdministrator(@PathVariable int adminId, @RequestBody AdministratorDto administratorDto)throws NotFoundException{
        return rentoNowService.editAdministratorById(adminId, administratorDto);
    }


    // Delete administrator
    @ApiOperation(value="Delete administrator by administrator id from the system", response= AdministratorDto.class)
    @DeleteMapping("/deleteAdministrator/{adminId}")
    public boolean deleteAdministrator(@PathVariable int adminId)throws NotFoundException{
        return rentoNowService.removeAdministratorById(adminId);
    }



    ////////////////////// Property //////////////////////////////////

//    //Add new Property
//    @ApiOperation(value="Add property to system", response= PropertyDto.class)
//    @PostMapping("addProperty{id}")
//    public PropertyDto addNewProperty(@RequestBody PropertyDto propertyDto)  {
//        return rentoNowService.addProperty(propertyDto);
//    }

    // Add Property to host
    @ApiOperation(value="Add new property to host list", response= PropertyDto.class)
    @PostMapping("/addProperty{hostId}/property")
    public PropertyDto addPropertyToHost(@PathVariable int hostId, @RequestBody PropertyDto propertyDto)  throws NotFoundException{
        return rentoNowService.addPropertyByHostId(hostId, propertyDto);
    }


    // Get property by id
    @ApiOperation(value="Get property by property id", response= PropertyDto.class)
    @GetMapping("/getProperty{propertyId}")
    public PropertyDto getProperty(@PathVariable int propertyId) throws NotFoundException{
        return rentoNowService.findPropertyById(propertyId);
    }


    // Get list of Properties
    @ApiOperation(value="Get all Properties from system", response=List.class)
    @GetMapping("/getProperties")
    public List<PropertyDto> getProperties() {
        return rentoNowService.getAllProperties();
    }

    // Get list of Properties
    @ApiOperation(value="Get all Properties from system filtered by price and location", response=List.class)
    @GetMapping("/filterProperties")
    public List<PropertyDto> filterProperties(@RequestParam Double maxPrice,
                                           @RequestParam Double minPrice,
                                           @RequestParam String location){
        return rentoNowService.getPropertiesByPriceLocation(minPrice, maxPrice, location);
    }



    // Edit Property info
    @ApiOperation(value="Edit Property info in system by property id", response= PropertyDto.class)
    @PutMapping("/editProperty{propertyId}")
    public PropertyDto editProperty(@PathVariable int propertyId, @RequestBody PropertyDto PropertyDto)throws NotFoundException{
        return rentoNowService.editPropertyById(propertyId, PropertyDto);
    }


    // Delete Property
    @ApiOperation(value="Delete property by property id from the system", response= PropertyDto.class)
    @DeleteMapping("/deleteProperty{propertyId}")
    public boolean deleteProperty(@PathVariable int propertyId)throws NotFoundException{
        return rentoNowService.removePropertyById(propertyId);
    }


    ////////////////////// Property Reservation //////////////////////////////////


    //Add new Property Reservation
    @ApiOperation(value="Add reservation by property id and host id to system", response= PropertyReservationDto.class)
    @PostMapping("addReservation/{guestId}/{propertyId}")
    public PropertyReservationDto addReservation(@RequestBody PropertyReservationDto propertyReservationDto,
                                                 @PathVariable int guestId, @PathVariable int propertyId) throws NotFoundException, ValidationException, javax.xml.bind.ValidationException {
        return rentoNowService.addReservation(propertyReservationDto, guestId, propertyId);
    }


    // Get Property Reservation by id
    @ApiOperation(value="Get reservation by reservation id", response= PropertyReservationDto.class)
    @GetMapping("/getReservation{reserveId}")
    public PropertyReservationDto getPropertyReservationById(@PathVariable int reserveId) throws NotFoundException{
        return rentoNowService.findReservation(reserveId);
    }


    // Get list of Property reservation
    @ApiOperation(value="Get all property reservations", response=List.class)
    @GetMapping("/getReservations")
    public List<PropertyReservationDto> getReservations() {
        return rentoNowService.getAllReservation();
    }



    // Edit Property Reservation info
    @ApiOperation(value="Edit Property reservation info in system by reservation id", response= PropertyReservationDto.class)
    @PutMapping("/editReservation{reserveId}")
    public PropertyReservationDto editReservation(
            @PathVariable int reserveId, @RequestBody PropertyReservationDto propertyReservationDto)throws NotFoundException{
        return rentoNowService.editReservation(reserveId, propertyReservationDto);
    }


    // Delete property reservation
    @ApiOperation(value="Delete reservation by id from the system", response= PropertyReservationDto.class)
    @DeleteMapping("/deleteReservation{reserveId}")
    public boolean deleteReservation(@PathVariable int reserveId)throws NotFoundException{
        return rentoNowService.removeReservation(reserveId);
    }



    ///////////////////////////// Images /////////////////////////

//    @PostMapping("/upload")
//    public ResponseEntity.BodyBuilder uplaodImage(@RequestParam MultipartFile file) throws IOException {
//        System.out.println("Original Image Byte Size - " + file.getBytes().length);
//        rentoNowService.store(file);
//        return ResponseEntity.status(HttpStatus.OK);
//   }


}
