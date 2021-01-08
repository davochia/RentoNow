package com.example.demo.controller;

import com.example.demo.dto.GuestDto;
import com.example.demo.dto.PropertyDto;
import com.example.demo.exception.GuestNotFoundException;
import com.example.demo.exception.HostNotFoundException;
import com.example.demo.exception.PropertyNotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.service.FileService;
import com.example.demo.service.impl.RentoNowServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//@RestController
//@RequestMapping
public class HostController {


    @Autowired
    private RentoNowServiceImpl rentoNowService;
    @Autowired
    private FileService fileService;

    ////////////////////// Guest //////////////////////////////////

    // Add new guest
    @PreAuthorize("hasAuthority('guest:write')")
    @ApiOperation(value="Add a new guest to system", response= GuestDto.class)
    @PostMapping("/addGuest")
    public GuestDto addGuest(@RequestBody GuestDto guestDto) throws ValidationException {
        return rentoNowService.addNewGuest(guestDto);
    }


    // Get guest
    @PreAuthorize("hasAuthority('guest:read')")
    @ApiOperation(value="Get guest by guest id from the system", response= GuestDto.class)
    @GetMapping("/getGuest/{guestId}")
    public GuestDto getGuest(@PathVariable Integer guestId) throws GuestNotFoundException {
        return rentoNowService.findGuestById(guestId);
    }


    // Edit guest info
    @PreAuthorize("hasAuthority('guest:write')")
    @ApiOperation(value="Edit guest info from in system by guest id", response= GuestDto.class)
    @PutMapping("/editGuest/{guestId}")
    public GuestDto editGuest(@PathVariable Integer guestId, @RequestBody GuestDto guestDto) throws ValidationException {
        return rentoNowService.editGuestById(guestId, guestDto);
    }


    // Delete guest
    @PreAuthorize("hasAuthority('guest:write')")
    @ApiOperation(value="Delete guest by guest id from the system", response= GuestDto.class)
    @DeleteMapping("/deleteGuest/{guestId}")
    public boolean deleteGuest(@PathVariable Integer guestId) {
        return rentoNowService.removeGuestById(guestId);
    }


    ////////////////////// Property //////////////////////////////////

    // Add Property to host
    @PreAuthorize("hasAuthority('property:write')")
    @ApiOperation(value="Add new property to host list", response= PropertyDto.class)
    @PostMapping(value = "/addProperty/{hostId}")
    public PropertyDto addPropertyToHost(@PathVariable Integer hostId, @RequestBody PropertyDto propertyDto) throws HostNotFoundException, ValidationException {
        return rentoNowService.addPropertyByHostId(hostId, propertyDto);
    }

    @PreAuthorize("hasAuthority('property:write')")
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


}