package com.example.demo.controller;

import com.example.demo.dto.GuestDto;
import com.example.demo.dto.HostDto;
import com.example.demo.dto.PropertyDto;
import com.example.demo.dto.PropertyReservationDto;
import com.example.demo.exception.*;
import com.example.demo.service.FileService;
import com.example.demo.service.impl.RentoNowServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

//@RestController
//@RequestMapping("host/rentonow")
public class HostController {


    @Autowired
    private RentoNowServiceImpl rentoNowService;

    @Autowired
    private FileService fileService;


    ////////////////////// Host //////////////////////////////////


    // Add new Host
//    @PreAuthorize("hasAuthority('host:write')")
    @ApiOperation(value="Add a new host to system", response= HostDto.class)
    @PostMapping("/addHost")
    public HostDto addHost(@RequestBody HostDto hostDto) throws ValidationException {
        return rentoNowService.addNewHost(hostDto);
    }

    //@PreAuthorize("hasAuthority('property:write')")
    @ApiOperation(value="Upload images for a specific property")
    @RequestMapping(value = "/addImage/{propertyId}/images", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    public ResponseEntity addImages(@PathVariable int propertyId, @RequestPart("image") MultipartFile image) throws PropertyNotFoundException {
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


    // Edit host info
//    @PreAuthorize("hasAuthority('host:write')")
    @ApiOperation(value="Edit host info from in system by host id", response= HostDto.class)
    @PutMapping("/editHost/{hostId}")
    public HostDto editHost(@PathVariable Integer hostId, @RequestBody HostDto hostDto) throws ValidationException {
        return rentoNowService.editHostById(hostId, hostDto);
    }


    // Delete host
//    @PreAuthorize("hasAuthority('host:write')")
    @ApiOperation(value="Delete host by host id from the system", response= GuestDto.class)
    @DeleteMapping("/deleteHost/{hostId}")
    public boolean deleteHost(@PathVariable Integer hostId)throws HostNotFoundException{
        return rentoNowService.removeHostById(hostId);
    }


    ////////////////////// Property //////////////////////////////////

    // Add Property to host
//    @PreAuthorize("hasAuthority('property:write')")
    @ApiOperation(value="Add new property to host list", response= PropertyDto.class)
    @PostMapping("/addProperty{hostId}/property")
    public PropertyDto addPropertyToHost(@PathVariable Integer hostId, @RequestBody PropertyDto propertyDto) throws GuestNotFoundException, ValidationException {
        return rentoNowService.addPropertyByHostId(hostId, propertyDto);
    }


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

    // Edit Property Reservation info
    //@PreAuthorize("hasAuthority('reservation:write')")
    @ApiOperation(value="Edit Property reservation info in system by reservation id", response= PropertyReservationDto.class)
    @PutMapping("/editReservation/{reserveId}")
    public ResponseEntity editReservation(
            @PathVariable Integer reserveId, @RequestBody PropertyReservationDto propertyReservationDto) throws ReservationNotFoundException, NotFoundException, InvalidDataException {
        return rentoNowService.editReservation(reserveId, propertyReservationDto);
    }


    // Delete Property
//    @PreAuthorize("hasAuthority('property:write')")
    @ApiOperation(value="Delete property by property id from the system", response= PropertyDto.class)
    @DeleteMapping("/deleteProperty{propertyId}")
    public boolean deleteProperty(@PathVariable Integer propertyId)throws PropertyNotFoundException{
        return rentoNowService.removePropertyById(propertyId);
    }
}