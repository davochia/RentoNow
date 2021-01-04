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
//@RequestMapping("host/rentonow")
public class HostController {


    @Autowired
    private RentoNowServiceImpl rentoNowService;


    ////////////////////// Host //////////////////////////////////


    // Add new Host
    @PreAuthorize("hasAuthority('host:write')")
    @ApiOperation(value="Add a new host to system", response= HostDto.class)
    @PostMapping("/addHost")
    public HostDto addHost(@RequestBody HostDto hostDto) throws ValidationException {
        return rentoNowService.addNewHost(hostDto);
    }

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

    // Edit host info
    @PreAuthorize("hasAuthority('host:write')")
    @ApiOperation(value="Edit host info from in system by host id", response= HostDto.class)
    @PutMapping("/editHost/{hostId}")
    public HostDto editHost(@PathVariable int hostId, @RequestBody HostDto hostDto) throws ValidationException {
        return rentoNowService.editHostById(hostId, hostDto);
    }


    // Delete host
    @PreAuthorize("hasAuthority('host:write')")
    @ApiOperation(value="Delete host by host id from the system", response= GuestDto.class)
    @DeleteMapping("/deleteHost/{hostId}")
    public boolean deleteHost(@PathVariable int hostId)throws HostNotFoundException{
        return rentoNowService.removeHostById(hostId);
    }



    ////////////////////// Property //////////////////////////////////

    // Add Property to host
    @PreAuthorize("hasAuthority('property:write')")
    @ApiOperation(value="Add new property to host list", response= PropertyDto.class)
    @PostMapping("/addProperty{hostId}/property")
    public PropertyDto addPropertyToHost(@PathVariable int hostId, @RequestBody PropertyDto propertyDto) throws GuestNotFoundException, ValidationException {
        return rentoNowService.addPropertyByHostId(hostId, propertyDto);
    }


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


    // Edit Property info
    @PreAuthorize("hasAuthority('property:write')")
    @ApiOperation(value="Edit Property info in system by property id", response= PropertyDto.class)
    @PutMapping("/editProperty{propertyId}")
    public PropertyDto editProperty(@PathVariable int propertyId, @RequestBody PropertyDto PropertyDto) throws PropertyNotFoundException, ValidationException {
        return rentoNowService.editPropertyById(propertyId, PropertyDto);
    }


    // Delete Property
    @PreAuthorize("hasAuthority('property:write')")
    @ApiOperation(value="Delete property by property id from the system", response= PropertyDto.class)
    @DeleteMapping("/deleteProperty{propertyId}")
    public boolean deleteProperty(@PathVariable int propertyId)throws PropertyNotFoundException{
        return rentoNowService.removePropertyById(propertyId);
    }


}
