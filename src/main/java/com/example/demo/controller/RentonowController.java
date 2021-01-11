package com.example.demo.controller;

/*
    link to swagger ui
    http://localhost:8080/swagger-ui/#/
 */

import com.example.demo.dto.*;
import com.example.demo.exception.*;
import com.example.demo.service.FileService;
import com.example.demo.service.impl.RentoNowServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping
public class RentonowController {

    @Autowired
    private RentoNowServiceImpl rentoNowService;
    @Autowired
    private FileService fileService;

    ////////////////////// Guest //////////////////////////////////

    // Add new guest
    @PreAuthorize("hasAuthority('guest:write')")
    @ApiOperation(value="Add a new guest to system", response= GuestDto.class)
    @PostMapping("/guest")
    public GuestDto addGuest(@RequestBody GuestDto guestDto) throws ValidationException {
        return rentoNowService.addNewGuest(guestDto);
    }


    // Get guest
    @PreAuthorize("hasAuthority('guest:read')")
    @ApiOperation(value="Get guest by guest id from the system", response= GuestDto.class)
    @GetMapping("/guest/{guestId}")
    public GuestDto getGuest(@PathVariable Integer guestId) throws GuestNotFoundException {
        return rentoNowService.findGuestById(guestId);
    }


    // Get all guest
    @PreAuthorize("hasAuthority('admin:read')")
    @ApiOperation(value="Get all guest from the system", response= GuestDto.class)
    @GetMapping("/guests")
    public List<GuestDto> getGuests(){
        return rentoNowService.getAllGuests();
    }


    // Edit guest info
    @PreAuthorize("hasAuthority('guest:write')")
    @ApiOperation(value="Edit guest info from in system by guest id", response= GuestDto.class)
    @PutMapping("/guest/{guestId}")
    public GuestDto editGuest(@PathVariable Integer guestId, @RequestBody GuestDto guestDto) throws ValidationException {
        return rentoNowService.editGuestById(guestId, guestDto);
    }


    // Delete guest
    @PreAuthorize("hasAuthority('guest:write')")
    @ApiOperation(value="Delete guest by guest id from the system", response= GuestDto.class)
    @DeleteMapping("/guest/{guestId}")
    public boolean deleteGuest(@PathVariable Integer guestId) {
        return rentoNowService.removeGuestById(guestId);
    }



    ////////////////////// Host //////////////////////////////////

    // Add new Host
    @PreAuthorize("hasAuthority('host:write')")
    @ApiOperation(value="Add a new host to system", response= HostDto.class)
    @PostMapping("/host")
    public HostDto addHost(@RequestBody HostDto hostDto) throws ValidationException {
        return rentoNowService.addNewHost(hostDto);
    }

    // Get host by id
    @PreAuthorize("hasAuthority('host:read')")
    @ApiOperation(value="Get host by host id", response=HostDto.class)
    @GetMapping("/host/{hostId}")
    public HostDto getHost(@PathVariable Integer hostId) {
        return rentoNowService.findHostById(hostId);
    }



    // Get host properties
    @PreAuthorize("hasAuthority('host:read')")
    @ApiOperation(value="Get host properties find by host id", response=List.class)
    @GetMapping("/properties/{hostId}")
    public List<PropertyDto> getProperties(@PathVariable int hostId) {
        return rentoNowService.HostProperties(hostId);
    }


    // Get list of hosts
    @PreAuthorize("hasAuthority('admin:read')")
    @ApiOperation(value="Get all hosts from the system", response=List.class)
    @GetMapping("/hosts")
    public List<HostDto> getHosts() {
        return rentoNowService.getAllHosts();
    }



    // Edit host info
    @PreAuthorize("hasAuthority('host:write')")
    @ApiOperation(value="Edit host info from in system by host id", response= HostDto.class)
    @PutMapping("/host/{hostId}")
    public HostDto editHost(@PathVariable Integer hostId, @RequestBody HostDto hostDto) throws ValidationException {
        return rentoNowService.editHostById(hostId, hostDto);
    }


    // Delete host
    @PreAuthorize("hasAuthority('host:write')")
    @ApiOperation(value="Delete host by host id from the system", response= GuestDto.class)
    @DeleteMapping("/host/{hostId}")
    public boolean deleteHost(@PathVariable Integer hostId)throws HostNotFoundException{
        return rentoNowService.removeHostById(hostId);
    }



    ////////////////////// Admin //////////////////////////////////

    // Add new administrator
    @PreAuthorize("hasAuthority('admin:write')")
    @ApiOperation(value="Add a new Administrator to system", response= AdministratorDto.class)
    @PostMapping("/administrator")
    public AdministratorDto addAdministrator(@RequestBody AdministratorDto AdministratorDto) throws ValidationException {
        return rentoNowService.addAdministrator(AdministratorDto);
    }

    //Get administrator by id
    @PreAuthorize("hasAuthority('admin:read')")
    @ApiOperation(value="Get administrator by administrator id", response= AdministratorDto.class)
    @GetMapping("/administrator/{adminId}")
    public AdministratorDto getAdministrator(@PathVariable Integer adminId) throws NotFoundException{
        return rentoNowService.findAdministratorById(adminId);
    }


    // Get list of administrators
    @PreAuthorize("hasAuthority('admin:read')")
    @ApiOperation(value="Get all administrator from the system", response=List.class)
    @GetMapping("/administrators")
    public List<AdministratorDto> getAdministrators() {
        return rentoNowService.getAllAdministrators();
    }


    // Edit administrator info
    @PreAuthorize("hasAuthority('admin:write')")
    @ApiOperation(value="Edit administrator info in system by administrator id", response= AdministratorDto.class)
    @PutMapping("/administrator/{adminId}")
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
    @DeleteMapping("/administrator/{adminId}")
    public boolean deleteAdministrator(@PathVariable Integer adminId)throws NotFoundException{
        return rentoNowService.removeAdministratorById(adminId);
    }

    // Get number of bookings per host property
    @PreAuthorize("hasAuthority('admin:read')")
    @ApiOperation(value="Get booking statistics filtered by host property", response=Integer.class)
    @RequestMapping(value = "/numOfReservationByHost/{hostId}", method = RequestMethod.GET)
    public int getReservationsByHost(@PathVariable Integer hostId) {
        return rentoNowService.getNumOfReservationByHost(hostId);
    }

    // Get number of bookings per guest
    @PreAuthorize("hasAuthority('admin:read')")
    @ApiOperation(value="Get bookings statistics filtered by guest", response=Integer.class)
    @RequestMapping(value = "/numOfReservationByGuest/{guestId}", method = RequestMethod.GET)
    public int getReservationsByGuest(@PathVariable Integer guestId) throws GuestNotFoundException {
        return rentoNowService.getNumOfReservationByGuest(guestId);
    }

    // Get number of bookings per property
    @PreAuthorize("hasAuthority('admin:read')")
    @ApiOperation(value="Get reservations bookings filtered by property", response=Integer.class)
    @RequestMapping(value = "/numOfReservationByProperty/{propertyId}", method = RequestMethod.GET)
    public int getReservationsByProperty(@PathVariable Integer propertyId) {
        return rentoNowService.getNumOfReservationByProperty(propertyId);
    }


    // Get number of bookings per property
    @PreAuthorize("hasAuthority('admin:read')")
    @ApiOperation(value="Get statistics of all reservation", response=Integer.class)
    @RequestMapping(value = "/statistics/", method = RequestMethod.GET)
    public List<String> reservationStatistics() {
        return rentoNowService.statistics();
    }



    ////////////////////// Property //////////////////////////////////

    // Add Property to host
    @PreAuthorize("hasAuthority('property:write')")
    @ApiOperation(value="Add new property to host list", response= PropertyDto.class)
    @PostMapping(value = "/property/{hostId}")
    public PropertyDto addPropertyToHost(@PathVariable Integer hostId, @RequestBody PropertyDto propertyDto) throws HostNotFoundException, ValidationException {
        return rentoNowService.addPropertyByHostId(hostId, propertyDto);
    }

    @PreAuthorize("hasAuthority('image:write')")
    @ApiOperation(value="Upload images for a specific property")
    @RequestMapping(value = "/addImage/{propertyId}/images", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    public ResponseEntity images(@PathVariable int propertyId, @RequestPart("image") MultipartFile image) throws PropertyNotFoundException {
        PropertyDto propertyDto = rentoNowService.findPropertyById(propertyId);
        if ( propertyDto != null ){
            String path = fileService.uploadFile(image);
            if (!path.isEmpty()){
                rentoNowService.saveImageToProperty(path, propertyId);
                return new ResponseEntity("Image was saved", HttpStatus.OK);
            }
            return new ResponseEntity("Image was not saved", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity("Property not found", HttpStatus.NOT_FOUND);
    }

    // Get property by id
    @PreAuthorize("hasAuthority('property:read')")
    @ApiOperation(value="Get property by property id", response= PropertyDto.class)
    @GetMapping("/property/{propertyId}")
    public PropertyDto getProperty(@PathVariable Integer propertyId) throws PropertyNotFoundException {
        try {
            return rentoNowService.findPropertyById(propertyId);
        } catch (PropertyNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    // Get list of Properties
    @PreAuthorize("hasAuthority('admin:read')")
    @ApiOperation(value="Get all Properties from system", response=List.class)
    @GetMapping("/properties")
    public List<PropertyDto> getProperties() {
        return rentoNowService.getAllProperties();
    }

    // Get list of filtered Properties
    @PreAuthorize("hasAuthority('property:read')")
    @ApiOperation(value="Get all Properties from system filtered by price and location", response=List.class)
    @GetMapping("/filterProperties")
    public List<PropertyDto> filterProperties(@RequestParam Double maxPrice,
                                              @RequestParam Double minPrice,
                                              @RequestParam String location,
                                              @RequestParam @ApiParam(example="YYYY-MM-DD") String availableStart,
                                              @RequestParam @ApiParam(example="YYYY-MM-DD") String availableEnd) throws PropertyNotFoundException {
        return rentoNowService.getPropertiesByPriceLocation(minPrice, maxPrice, location, availableStart, availableEnd);
    }



    // Edit Property info
    @PreAuthorize("hasAuthority('property:write')")
    @ApiOperation(value="Edit Property info in system by property id", response= PropertyDto.class)
    @PutMapping("/property/{propertyId}")
    public PropertyDto editProperty(@PathVariable Integer propertyId, @RequestBody PropertyDto PropertyDto) throws PropertyNotFoundException, ValidationException {
        return rentoNowService.editPropertyById(propertyId, PropertyDto);
    }


    // Delete Property
    @PreAuthorize("hasAuthority('property:write')")
    @ApiOperation(value="Delete property by property id from the system", response= PropertyDto.class)
    @DeleteMapping("/property/{propertyId}")
    public boolean deleteProperty(@PathVariable Integer propertyId)throws PropertyNotFoundException{
        return rentoNowService.removePropertyById(propertyId);
    }


    ////////////////////// Property Reservation //////////////////////////////////


    //Add new Property Reservation
    @PreAuthorize("hasAuthority('reservation:write')")
    @ApiOperation(value="Add reservation by property id and host id to system", response= PropertyReservationDto.class)
    @PostMapping("reservation/{guestId}/{propertyId}")
    public ResponseEntity addReservation(@RequestBody PropertyReservationDto propertyReservationDto,
                                         @PathVariable Integer guestId,
                                         @PathVariable Integer propertyId) throws NotFoundException, ValidationException, javax.xml.bind.ValidationException, InvalidDataException {
        return rentoNowService.addReservation(propertyReservationDto, guestId, propertyId);
    }


    // Get Property Reservation by id
    @PreAuthorize("hasAuthority('reservation:read')")
    @ApiOperation(value="Get reservation by reservation id", response= PropertyReservationDto.class)
    @GetMapping("/reservation/{reserveId}")
    public PropertyReservationDto getPropertyReservationById(@PathVariable Integer reserveId) throws ReservationNotFoundException{
        return rentoNowService.findReservation(reserveId);
    }


    // Get list of Property reservation
    @PreAuthorize("hasAuthority('reservation:read')")
    @ApiOperation(value="Get all property reservations", response=List.class)
    @GetMapping("/reservations")
    public List<PropertyReservationDto> getReservations() {
        return rentoNowService.getAllReservation();
    }


    // Edit Property Reservation info
    @PreAuthorize("hasAuthority('reservation:write')")
    @ApiOperation(value="Edit Property reservation info in system by reservation id", response= PropertyReservationDto.class)
    @PutMapping("/reservation/{reserveId}")
    public ResponseEntity editReservation(
            @PathVariable Integer reserveId, @RequestBody PropertyReservationDto propertyReservationDto) throws ReservationNotFoundException, NotFoundException, InvalidDataException {
        return rentoNowService.editReservation(reserveId, propertyReservationDto);
    }


    // Delete property reservation
    @PreAuthorize("hasAuthority('reservation:write')")
    @ApiOperation(value="Delete reservation by id from the system", response= PropertyReservationDto.class)
    @DeleteMapping("/reservation/{reserveId}")
    public boolean deleteReservation(@PathVariable Integer reserveId)throws ReservationNotFoundException{
        return rentoNowService.removeReservation(reserveId);
    }

}
