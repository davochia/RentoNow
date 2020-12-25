package com.example.demo.controller;

/*
    link to swagger ui
    http://localhost:8080/swagger-ui/#/
 */


import com.example.demo.dto.GuestDto;
import com.example.demo.dto.HostDto;
import com.example.demo.dto.PropertyDto;
import com.example.demo.model.Property;
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
    @PostMapping("/guest")
    public GuestDto addNewGuest(@RequestBody GuestDto guestDto){
        return rentoNowService.addGuest(guestDto);
    }


    // Get guest
    @ApiOperation(value="Get guest by id from the system", response= GuestDto.class)
    @GetMapping("/guest/{id}")
    public GuestDto getGuestById(@PathVariable int id)throws NotFoundException{
        return rentoNowService.findGuestById(id);
    }


    // Get all guest
    @ApiOperation(value="Get all guest from the system", response= GuestDto.class)
    @GetMapping("/guests")
    public List<GuestDto> getGuests(){
        return rentoNowService.getAllGuest();
    }


    // Edit guest info
    @ApiOperation(value="Edit guest info from in system by id", response= GuestDto.class)
    @PutMapping("/guest/{id}")
    public GuestDto modifyGuestById(@PathVariable int id, @RequestBody GuestDto guestDto)throws NotFoundException{
        return rentoNowService.editGuest(id, guestDto);
    }


    // Get guest
    @ApiOperation(value="Remove guest by id from the system", response= GuestDto.class)
    @DeleteMapping("/guest/{id}")
    public boolean deleteGuest(@PathVariable int id)throws NotFoundException{
        return rentoNowService.removeGuestById(id);
    }




    ////////////////////// Host //////////////////////////////////


    // Add new Host
    @ApiOperation(value="Register a new host to system", response= HostDto.class)
    @PostMapping("/host")
    public void addHost(@RequestBody HostDto hostDto){
        rentoNowService.addHost(hostDto);
    }

    // List of hosts
    @ApiOperation(value="Get host by id", response=HostDto.class)
    @GetMapping("/host")
    public HostDto getHostById(int id) throws NotFoundException{
        return rentoNowService.getHostById(id);
    }


    // List of hosts
    @ApiOperation(value="Get all registered host", response=List.class)
    @GetMapping("/hosts")
    public List<HostDto> getAllHosts() {
        return rentoNowService.getAllHosts();
    }

    // Add new Property
    @ApiOperation(value="Add property to system", response= PropertyDto.class)
    @PostMapping("/host/{id}/addProperty")
    public Object addProperty(@PathVariable int host_id, @RequestBody Property property) throws NotFoundException {
        return rentoNowService.addProperty(host_id, property);
    }

    //List of properties
//    @GetMapping("/host/{host_id}/properties/")
//    public List<Property> getHostProperties(@PathVariable int host_id) throws NotFoundException {
//        return hostService.getProperties(host_id);
//    }



    ////////////////////// Property //////////////////////////////////




    ////////////////////// Admin //////////////////////////////////



}
